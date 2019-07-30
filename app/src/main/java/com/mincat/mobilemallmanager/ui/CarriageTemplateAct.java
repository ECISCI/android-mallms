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
 * @描述 运费模板选择
 */
public class CarriageTemplateAct extends MinCatBaseRequest implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = CarriageTemplateAct.class.getSimpleName();


    public static final String EXPRESS_TYPE = "type";

    public static final String EXPRESS_PRICE = "price";

    public static int RESPONSE_CODE_CARRIAGE = 0x2001;


    private AppCompatImageButton mCloseAct;

    private RadioGroup mRgExpressChoice, mRgPrice;

    private RadioButton mRbSf, mRbEms, mRbSt, mRbBy, mRb10, mRb12, mRb22;

    private String strExpress = "顺丰速运";

    private String strPrice = "包邮";

    private Button mBtnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_carriage_template);
        initView();

    }

    @Override
    public void initView() {

        mCloseAct = getId(R.id.icon_close_act);
        mCloseAct.setOnClickListener(this);

        mRbSf = getId(R.id.rb_sf);
        mRbEms = getId(R.id.rb_ems);
        mRbSt = getId(R.id.rb_st);

        mRbBy = getId(R.id.rb_by);
        mRb10 = getId(R.id.rb_10);
        mRb12 = getId(R.id.rb_12);
        mRb22 = getId(R.id.rb_22);

        mRgExpressChoice = getId(R.id.rg_express_choice);
        mRgExpressChoice.setOnCheckedChangeListener(this);

        mRgPrice = getId(R.id.rg_express_price);
        mRgPrice.setOnCheckedChangeListener(this);

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

                Intent intent = new Intent();
                intent.putExtra(EXPRESS_TYPE, strExpress);
                intent.putExtra(EXPRESS_PRICE, strPrice);
                setResult(RESPONSE_CODE_CARRIAGE, intent);

                this.finish();
                break;
        }

    }

    private void finishThisPage() {

        final CustomWarnDialog dialog = new CustomWarnDialog(this, "温馨提示", "是否保存当前已选物流信息？", "保存", "取消");

        dialog.show();
        dialog.setOnDialogClickListener(new CustomWarnDialog.OnDialogClickListener() {

            @Override
            public void dialogLeft() {

                Intent intent = new Intent();
                intent.putExtra(EXPRESS_TYPE, strExpress);
                intent.putExtra(EXPRESS_PRICE, strPrice);
                setResult(RESPONSE_CODE_CARRIAGE, intent);

                CarriageTemplateAct.this.finish();
            }

            @Override
            public void dialogRight() {

                CarriageTemplateAct.this.finish();
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

        if (checkedId == R.id.rb_sf) { // 顺丰
            mRbEms.setChecked(false);
            mRbSt.setChecked(false);
            strExpress = "顺丰速运";

        } else if (checkedId == R.id.rb_ems) { // ems

            mRbSf.setChecked(false);
            mRbSt.setChecked(false);
            strExpress = "EMS";

        } else if (checkedId == R.id.rb_st) {// 申通

            mRbSf.setChecked(false);
            mRbEms.setChecked(false);
            strExpress = "中通速运";
        }


        if (checkedId == R.id.rb_by) {
            mRb10.setChecked(false);
            mRb12.setChecked(false);
            mRb22.setChecked(false);

            strPrice = "包邮";
        } else if (checkedId == R.id.rb_10) {

            mRbBy.setChecked(false);
            mRb12.setChecked(false);
            mRb22.setChecked(false);

            strPrice = "10.00";
        } else if (checkedId == R.id.rb_12) {

            mRbBy.setChecked(false);
            mRb10.setChecked(false);
            mRb22.setChecked(false);

            strPrice = "12.00";
        } else if (checkedId == R.id.rb_22) {

            mRbBy.setChecked(false);
            mRb10.setChecked(false);
            mRb12.setChecked(false);
            strPrice = "22.00";
        }


    }
}
