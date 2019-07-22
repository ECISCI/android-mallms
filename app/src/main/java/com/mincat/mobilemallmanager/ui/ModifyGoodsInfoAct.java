package com.mincat.mobilemallmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.sample.utils.T;

public class ModifyGoodsInfoAct extends MinCatBaseRequest {

    public static final int RESPONSE_CODE_PRICE = 0x9001;
    public static final int RESPONSE_CODE_NUM = 0x9002;

    public static final String ET_COUNT = "et_count";

    /**
     * title标题
     */
    private TextView mTitle;
    /**
     * 携带上级页面参数
     */
    private Intent intent;
    /**
     * 输入框
     */
    private EditText mEtInput;
    /**
     * 保存按钮
     */
    private Button mBtnSaveInfo;
    /**
     * 关闭页面按钮
     */
    private AppCompatImageButton mClosetAct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_content_modify);
        initView();
    }

    @Override
    public void initView() {

        mTitle = getId(R.id.title);
        mEtInput = getId(R.id.et_input);
        mEtInput.setFocusable(true);
        mEtInput.setFocusableInTouchMode(true);
        mEtInput.requestFocus();

        mBtnSaveInfo = getId(R.id.btn_save);
        mBtnSaveInfo.setOnClickListener(this);

        mClosetAct = getId(R.id.icon_close_act);
        mClosetAct.setOnClickListener(this);
        intentParams();

    }

    private void intentParams() {

        Intent intent = getIntent();

        String param = intent.getStringExtra(AddGoodsAct.PARAM);

        mTitle.setText(param);

        showSoftInputFromWindow(this, mEtInput);

        if (param.equals(AddGoodsAct.PARAM_PRICE)) {

            modifyEditTextStatus(mEtInput, "最高999999.00", 9, InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        } else if (param.equals(AddGoodsAct.PARAM_NUM)) {

            modifyEditTextStatus(mEtInput, "最高999999", 6, InputType.TYPE_CLASS_NUMBER);

        }

    }


    // 封装
    private void modifyEditTextStatus(EditText et, String hint, int maxInput, int type) {

        et.setHint(hint);
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxInput)});
        et.setInputType(type);
    }

    @Override
    public void onNetRequest() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        String extraText = etString(mEtInput);

        String titleName = mTitle.getText().toString().trim();

        switch (view.getId()) {

            case R.id.btn_save:

                judgeInput(extraText);

                saveModifyInfo(titleName, intent, extraText);

                break;


            case R.id.icon_close_act:

                closeInput();
                this.finish();

                break;
        }

    }

    private void judgeInput(String extraText) {
        if (TextUtils.isEmpty(extraText)) {

            T.showShort(ModifyGoodsInfoAct.this, "请输入要保存的内容");
            return;
        }
    }

    private void saveModifyInfo(String titleName, Intent intent, String extraText) {


        if (titleName.equals(AddGoodsAct.PARAM_PRICE)) {

            forResultAndFinishAct(intent, ET_COUNT, extraText, RESPONSE_CODE_PRICE);

        } else if (titleName.equals(AddGoodsAct.PARAM_NUM)) {

            forResultAndFinishAct(intent, ET_COUNT, extraText, RESPONSE_CODE_NUM);

        }
    }

    private void forResultAndFinishAct(Intent intent, String et_count, String extraText, int resultCode) {

        intent.putExtra(et_count, extraText);
        setResult(resultCode, intent);

        closeInput();
        this.finish();
    }

    @Override
    public String assembleRequestParam() {
        return null;
    }

    @Override
    public void onHandleResponsePost(String response, String sign) {

    }

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {

    }
}
