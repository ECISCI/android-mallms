package com.mincat.mobilemallmanager.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.entity.register.RegisterReq;
import com.mincat.mobilemallmanager.entity.register.RegisterRes;
import com.mincat.mobilemallmanager.utils.Constants;
import com.mincat.mobilemallmanager.utils.HandleUtils;
import com.mincat.sample.imagecache.utils.Constant;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.T;

/**
 * @author 玛丽莲梦明
 * @描述 用户注册页
 */
public class RegisterAct extends MinCatBaseRequest {

    private static final String TAG = RegisterAct.class.getSimpleName();


    // 注册成功
    private static final int REGISTER_SUCCESS = 0X0010;

    // 注册请求失败
    private static final int REGISTER_REQUEST_FAILED = 0X0011;

    // 注册失败
    private static final int REGISTER_FAILED = 0X0012;

    // 注册用户已存在
    private static final int REGISTER_USER_EXIST = 0X0013;

    public static final int REGISTER_SUCCESS_RESULT_CODE = 12;

    /**
     * 输入框
     */
    private EditText mEtPhone, mEtVerifyCode, mEtPassword;

    /**
     * 注册按钮
     */
    private Button mBtnRegister;

    /**
     * 关闭当前页面
     */
    private AppCompatImageButton mClose;

    private String mStrPhone, mStrPassword, mStrVerifyCode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_register);
        initView();

    }

    @Override
    public void initView() {

        mEtPhone = getId(R.id.et_username);
        mEtVerifyCode = getId(R.id.et_verify_code);
        mEtPassword = getId(R.id.et_password);

        mBtnRegister = getId(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);

        mClose = getId(R.id.icon_close_register);
        mClose.setOnClickListener(this);


        // 限制手机号输入为11位
        mEtPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        // 限制验证码输入为4位
        mEtVerifyCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        // 限制密码输入为12位
        mEtPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});

    }

    @Override
    public void onNetRequest() {

        if (checkEtInput()) {
            mBtnRegister.setText("注册中...");
            executeVolleyPostRequest(this, Constants.REGISTER_USER, assembleRequestParam(), Constants.REGISTER_USER_SIGN, true);
        }
    }


    private boolean checkEtInput() {

        mStrPhone = etString(mEtPhone);
        mStrPassword = etString(mEtPassword);
        mStrVerifyCode = etString(mEtVerifyCode);

        if (TextUtils.isEmpty(mStrPhone)) {
            T.showShort(RegisterAct.this, "请输入手机号码");
            return false;
        } else if (TextUtils.isEmpty(mStrPassword)) {
            T.showShort(RegisterAct.this, "请输入密码");
            return false;
        } else if (!mStrVerifyCode.equals("6666")) {
            T.showShort(RegisterAct.this, "验证码输入有误,请重新输入");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.icon_close_register: // 关闭当前页面

                RegisterAct.this.finish();

                break;


            case R.id.btn_register: // 注册用户

                onNetRequest();

                break;
        }

    }

    @Override
    public String assembleRequestParam() {

        RegisterReq requestData = new RegisterReq();

        requestData.setUsername(mStrPhone);
        requestData.setPassword(mStrPassword);

        return JSONObject.toJSONString(requestData);
    }

    @Override
    public void onHandleResponsePost(String response, String sign) {


        if (sign.equals(Constants.REGISTER_USER_SIGN)) { // 如果请求来自于用户注册

            mBtnRegister.setText("立即注册");

            RegisterRes responseData = JSONObject.parseObject(response, RegisterRes.class);

            if (responseData != null
                    && responseData.getCode() == Constants.REQUEST_SUCCESS_CODE
                    && responseData.getMsg().equals(Constants.OPERATION_SUCCESS)) { // 注册成功

                HandleUtils.sendHandle(handler, REGISTER_SUCCESS, Constants.NULL_STRING);


            } else if (responseData != null && responseData.getMsg().equals(Constants.R_EXIST)) {// 注册用户已存在

                HandleUtils.sendHandle(handler, REGISTER_USER_EXIST, Constants.NULL_STRING);

            } else { // 注册用户失败
                HandleUtils.sendHandle(handler, REGISTER_FAILED, Constants.NULL_STRING);
            }

        }

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case REGISTER_SUCCESS: // 注册成功

                    T.showShort(RegisterAct.this, "注册成功");

                    Intent intent = new Intent();
                    intent.putExtra("username", mStrPhone);
                    intent.putExtra("password", mStrPassword);

                    setResult(REGISTER_SUCCESS_RESULT_CODE, intent);

                    RegisterAct.this.finish();

                    break;

                case REGISTER_USER_EXIST: // 注册用户已存在
                    T.showShort(RegisterAct.this, "注册用户已存在");

                    break;

                case REGISTER_FAILED: // 注册失败

                    T.showShort(RegisterAct.this, "注册失败");
                    break;

                case REGISTER_REQUEST_FAILED: // 注册请求网络失败

                    T.showShort(RegisterAct.this, Constants.REQUEST_T_FAILED);


                    break;
            }


            super.handleMessage(msg);
        }
    };

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {

        if (sign.equals(Constants.REGISTER_USER_SIGN)) { // 请求来自于注册用户
            mBtnRegister.setText("立即注册");
            HandleUtils.sendHandle(handler, REGISTER_REQUEST_FAILED, Constants.NULL_STRING);
        }

    }
}
