package com.mincat.mobilemallmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.sample.custom.CustomWarnDialog;


/**
 * @author Gin
 * @描述 折扣
 */
public class DiscountAct extends MinCatBaseRequest implements RadioGroup.OnCheckedChangeListener {


    public static final String TAG = DiscountAct.class.getSimpleName();

    public static final int RESPONSE_CODE_DISCOUNT = 0X3001;


    public static final String RESPONSE_DISCOUNT_INFO = "discount_info";


    private RadioGroup mRgDiscount;

    private RadioButton mRbNone, mRb10, mRb15, mRb20, mRb25, mRb30, mRb35, mRb40, mRb45, mRb50, mRb55, mRb60, mRb65, mRb70, mRb75, mRb80, mRb85, mRb90;


    private String strDisCount = "无折扣";

    private AppCompatImageButton mCloseAct;

    private Button mBtnSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_goods_discount);

        initView();
    }

    @Override
    public void initView() {

        mRgDiscount = getId(R.id.rg_discount);
        mRgDiscount.setOnCheckedChangeListener(this);

        mRbNone = getId(R.id.rb_discount_none);
        mRb10 = getId(R.id.rb_discount_10);
        mRb15 = getId(R.id.rb_discount_15);
        mRb20 = getId(R.id.rb_discount_20);
        mRb25 = getId(R.id.rb_discount_25);
        mRb30 = getId(R.id.rb_discount_30);
        mRb35 = getId(R.id.rb_discount_35);
        mRb40 = getId(R.id.rb_discount_40);
        mRb45 = getId(R.id.rb_discount_45);
        mRb50 = getId(R.id.rb_discount_50);
        mRb55 = getId(R.id.rb_discount_55);
        mRb60 = getId(R.id.rb_discount_60);
        mRb65 = getId(R.id.rb_discount_65);
        mRb70 = getId(R.id.rb_discount_70);
        mRb75 = getId(R.id.rb_discount_75);
        mRb80 = getId(R.id.rb_discount_80);
        mRb85 = getId(R.id.rb_discount_85);
        mRb90 = getId(R.id.rb_discount_90);


        mCloseAct = getId(R.id.icon_close_act);
        mCloseAct.setOnClickListener(this);


        mBtnSave = getId(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onNetRequest() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.icon_close_act:

                finishThisPage();

                break;

            case R.id.btn_save:


                forIntentResult(RESPONSE_CODE_DISCOUNT, RESPONSE_DISCOUNT_INFO, strDisCount);


                break;
        }

    }


    private void forIntentResult(int responseCode, String responseKey, String responseValue) {

        Intent intent = new Intent();
        intent.putExtra(responseKey, responseValue);

        setResult(responseCode, intent);

        DiscountAct.this.finish();

    }

    private void finishThisPage() {

        final CustomWarnDialog dialog = new CustomWarnDialog(this, "温馨提示", "是否保存当前已选折扣信息？", "保存", "取消");

        dialog.show();
        dialog.setOnDialogClickListener(new CustomWarnDialog.OnDialogClickListener() {

            @Override
            public void dialogLeft() {

                forIntentResult(RESPONSE_CODE_DISCOUNT, RESPONSE_DISCOUNT_INFO, strDisCount);
            }

            @Override
            public void dialogRight() {

                DiscountAct.this.finish();

            }
        });
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.rb_discount_10) {

            strDisCount = "10%";
        } else if (checkedId == R.id.rb_discount_15) {

            strDisCount = "15%";
        } else if (checkedId == R.id.rb_discount_20) {

            strDisCount = "20%";
        } else if (checkedId == R.id.rb_discount_25) {

            strDisCount = "25%";
        } else if (checkedId == R.id.rb_discount_30) {

            strDisCount = "30%";
        } else if (checkedId == R.id.rb_discount_35) {

            strDisCount = "35%";
        } else if (checkedId == R.id.rb_discount_40) {

            strDisCount = "40%";
        } else if (checkedId == R.id.rb_discount_45) {

            strDisCount = "45%";
        } else if (checkedId == R.id.rb_discount_50) {

            strDisCount = "50%";
        } else if (checkedId == R.id.rb_discount_55) {

            strDisCount = "55%";
        } else if (checkedId == R.id.rb_discount_60) {

            strDisCount = "60%";
        } else if (checkedId == R.id.rb_discount_65) {

            strDisCount = "65%";
        } else if (checkedId == R.id.rb_discount_70) {

            strDisCount = "70%";
        } else if (checkedId == R.id.rb_discount_75) {

            strDisCount = "75%";
        } else if (checkedId == R.id.rb_discount_80) {

            strDisCount = "80%";
        } else if (checkedId == R.id.rb_discount_85) {

            strDisCount = "85%";
        } else if (checkedId == R.id.rb_discount_90) {

            strDisCount = "90%";
        }

    }
}
