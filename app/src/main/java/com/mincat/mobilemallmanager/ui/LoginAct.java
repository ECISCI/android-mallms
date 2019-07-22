package com.mincat.mobilemallmanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MainHomeAct;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.bean.LoginBean;
import com.mincat.mobilemallmanager.entity.login.request.LoginRequestData;
import com.mincat.mobilemallmanager.entity.login.response.LoginResponseData;
import com.mincat.mobilemallmanager.utils.Constants;
import com.mincat.mobilemallmanager.utils.SpUtils;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gin
 * @描述 管理系统用户登录
 */
public class LoginAct extends MinCatBaseRequest {

    public static final String TAG = LoginAct.class.getSimpleName();

    private AppCompatImageButton mIconClose;

    private EditText mEtUserName, mEtPassword;

    private String mStrUserName, mStrPassWord;

    private Button mBtnLogin;

    private List<LoginResponseData> mListResponseData = new ArrayList<>();

    private LoginResponseData mLoginResponseData;

    private LoginBean mLoginBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        initView();
    }

    @Override
    public void initView() {

        mIconClose = getId(R.id.icon_close_login);
        mIconClose.setOnClickListener(this);

        mEtUserName = getId(R.id.et_username);
        mEtPassword = getId(R.id.et_password);

        mBtnLogin = getId(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);

        // 限制手机号输入为11位
        mEtUserName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        // 限制密码输入为12位
        mEtPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

    }

    @Override
    public void onNetRequest() {


        if (checkEditTextInput()) {

            String params = assembleRequestParam();

            executeVolleyPostRequest(this, Constants.LOGIN, params, Constants.SIGN_LOGIN, true);
        }
    }

    private boolean checkEditTextInput() {

        mStrUserName = etString(mEtUserName);
        mStrPassWord = etString(mEtPassword);

        if (TextUtils.isEmpty(mStrUserName)) {

            T.showShort(this, "请输入用户名");
            return false;

        } else if (TextUtils.isEmpty(mStrPassWord)) {

            T.showShort(this, "请输入密码");
            return false;
        }

        return true;
    }

    @Override // 点击事件
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.icon_close_login:

                this.finish();

                break;

            case R.id.btn_login:

                onNetRequest();

                break;
        }
    }

    @Override // 组装Json
    public String assembleRequestParam() {

        LoginRequestData requestEntity = new LoginRequestData();
        requestEntity.setAccount(mStrUserName);
        requestEntity.setPassword(mStrPassWord);

        return JSONObject.toJSONString(requestEntity);

    }

    @Override // 请求成功返回参数
    public void onHandleResponsePost(String response, String sign) {

        if (sign.equals(Constants.SIGN_LOGIN)) {

            mLoginBean = JSONObject.parseObject(response, LoginBean.class);

            if (mLoginBean.getCode() == 200) {

                mListResponseData = mLoginBean.getData();
                mLoginResponseData = mListResponseData.get(0);

                if (mLoginResponseData != null) { // 如果解析结果不为null值

                    saveUserLoginInfo(mLoginResponseData);
                    inMainHome();
                }

            } else {

                T.showShort(this, "登录失败,请稍后再试");
            }
        }

    }

    private void inMainHome() {

        intentUtils.launchActFromRight(this, MainHomeAct.class);
        this.finish();
    }

    private void saveUserLoginInfo(LoginResponseData data) {

        String resData = JSONObject.toJSONString(data);

        // 保存用户登录时的token值
        SpUtils.setUserLoginToken(data.getUserToken(), this);

        // 判断用户是否已登录
        SpUtils.setIsLogin("isLogin", this);

        // 保存用户的基本信息
        SpUtils.setUserInfo(resData, this);
    }

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {


        if (sign.equals(Constants.SIGN_LOGIN)) {

            LogUtils.i(TAG, "error:" + error.getMessage());
        }
    }
}
