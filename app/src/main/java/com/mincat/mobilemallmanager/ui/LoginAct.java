package com.mincat.mobilemallmanager.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MainHomeAct;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.bean.BaseBean;
import com.mincat.mobilemallmanager.bean.login.LoginReqBean;
import com.mincat.mobilemallmanager.entity.login.LoginReqData;
import com.mincat.mobilemallmanager.entity.login.LoginResBean;
import com.mincat.mobilemallmanager.entity.login.LoginResData;
import com.mincat.mobilemallmanager.utils.Constants;
import com.mincat.mobilemallmanager.utils.HandleUtils;
import com.mincat.mobilemallmanager.utils.SpUtils;
import com.mincat.sample.custom.Code;
import com.mincat.sample.imagecache.utils.Constant;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gin
 * @描述 管理系统用户登录
 */
public class LoginAct extends MinCatBaseRequest {

    /**
     * 日志标记
     */
    public static final String TAG = LoginAct.class.getSimpleName();

    private static final int INTENT_REGISTER_CODE = 10;

    private static final int LOGIN_SUCCESS = 20;

    private static final int INTENT_LOGIN_SUCCESS = 21;

    /**
     * 关闭当前页面
     */
    private AppCompatImageButton mIconClose;

    /**
     * 文本输入框 用户名,密码,验证码
     */
    private EditText mEtUserName, mEtPassword, mEtVerifyCode;

    /**
     * 文本框中文字内容  用户名 密码 验证码
     */
    private String mStrUserName, mStrPassWord, mStrVerifyCode;

    /**
     * 登录按钮
     */
    private Button mBtnLogin;

    /**
     * 登录后返回的个人信息数据集合
     */
    private List<LoginResData> mListResponseData = new ArrayList<>();


    /**
     * 验证码展示
     */
    private AppCompatImageView mImageVCode;


    private TextView mTvRegister;

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


        mEtVerifyCode = getId(R.id.et_verify_code);

        mImageVCode = getId(R.id.image_v_code);
        mImageVCode.setOnClickListener(this);


        mTvRegister = getId(R.id.tv_register);
        mTvRegister.setOnClickListener(this);


        // 限制手机号输入为11位
        mEtUserName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        // 限制密码输入为12位
        mEtPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
        // 限制验证码输入长度为4位
        mEtVerifyCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

        mImageVCode.setImageBitmap(Code.getInstance().createBitmap());

    }

    @Override // 执行网络请求
    public void onNetRequest() {


        if (checkEditTextInput()) {

            String params = assembleRequestParam();

            executeVolleyPostRequest(this, Constants.LOGIN, params, Constants.LOGIN_SIGN, true);
        }
    }


    /**
     * 检查输入框中输入内容是否符合规定
     *
     * @return true false
     */
    private boolean checkEditTextInput() {

        mStrUserName = etString(mEtUserName);
        mStrPassWord = etString(mEtPassword);
        mStrVerifyCode = etString(mEtVerifyCode);

        if (TextUtils.isEmpty(mStrUserName)) {

            T.showShort(this, "请输入用户名");
            return false;

        } else if (TextUtils.isEmpty(mStrPassWord)) {

            T.showShort(this, "请输入密码");
            return false;
        } else if (TextUtils.isEmpty(mStrPassWord)) {

            T.showShort(this, "请输入验证码");
            return false;
        }

        return true;
    }

    @Override // 点击事件
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.icon_close_login: // 关闭当前页面

                this.finish();

                break;

            case R.id.btn_login: // 登录按钮

                onNetRequest();
                break;
            case R.id.image_v_code: // 刷新验证码
                mImageVCode.setImageBitmap(Code.getInstance().createBitmap());
                String realCode = Code.getInstance().getCode().toLowerCase();
                LogUtils.i(TAG, "realCode:" + realCode);

                break;


            case R.id.tv_register: // 注册用户


                intentUtils.launchActSlideRightForResult(LoginAct.this, RegisterAct.class, INTENT_REGISTER_CODE);


                break;
        }
    }

    // 下载获取验证码
    private void getVCode() {


    }

    @Override // 组装Json参数
    public String assembleRequestParam() {

        LoginReqData requestEntity = new LoginReqData();
        requestEntity.setUsername(mStrUserName);
        requestEntity.setPassword(mStrPassWord);

        return JSONObject.toJSONString(requestEntity);

    }


    private LoginResBean responseData;

    @Override // 当网络请求方式为POST时回调此方法
    public void onHandleResponsePost(String response, String sign) {

        LogUtils.i(TAG, "返回参数:" + response);

        if (sign.equals(Constants.LOGIN_SIGN)) {

            responseData = JSONObject.parseObject(response, LoginResBean.class);

            if (responseData.getCode() == Constants.REQUEST_SUCCESS_CODE
                    && responseData.getMsg().equals(Constants.OPERATION_SUCCESS)) {

                HandleUtils.sendHandle(handler, LOGIN_SUCCESS, Constants.NULL_STRING);
            }
        }
    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case LOGIN_SUCCESS: // 登录成功
                    Intent intent = new Intent();
                    intent.putExtra("loginFlag", "success");

                    setResult(INTENT_LOGIN_SUCCESS, intent);

                    T.showShort(LoginAct.this, "登录成功");

                    saveUserLoginInfo(responseData);
                    break;
            }

            super.handleMessage(msg);
        }
    };

    /**
     * 进入主页面
     */
    private void inMainHome() {

        intentUtils.launchActFromRight(this, MainHomeAct.class);
        this.finish();
    }

    /**
     * 保存用户信息
     *
     * @param data 服务端返回的用户信息
     */
    private void saveUserLoginInfo(LoginResBean data) {

        LoginResData resData = data.getData();


        // 保存用户登录时的token值
        SpUtils.setUserLoginToken(resData.getToken(), this);

        // 判断用户是否已登录
        SpUtils.setIsLogin("isLogin", this);

        // 保存用户id用于查询 更改用户信息
        SpUtils.setUserId(resData.getUser_id(), this);

        LoginAct.this.finish();
    }

    @Override // 当网络请求方式为GET时,回调此方法
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override // 当网络请求产生异常时 回调此方法
    public void errorListener(VolleyError error, String sign) {


        if (sign.equals(Constants.SIGN_LOGIN)) {

            LogUtils.i(TAG, "error:" + error.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RegisterAct.REGISTER_SUCCESS_RESULT_CODE) {

            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");

            mEtUserName.setText(username);
            mEtPassword.setText(password);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
