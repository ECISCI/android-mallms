package com.mincat.mobilemallmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;

public class AddGoodsAct extends MinCatBaseRequest {

    private static final int REQUEST_CODE_PRICE = 0X0010;

    public static final String PARAM = "param";

    public static final String PARAM_PRICE = "商品价格";
    public static final String PARAM_NUM = "商品库存";
    public static final String PARAM_FREIGHT = "运费模板";
    public static final String PARAM_DISCOUNT = "商品折扣";


    private EditText mEtGoodsTitle, mEtGoodsDesc;

    /**
     * 控制标题和详情输入字数
     */
    private TextView mTitleCountNumber, mDescCountNumber;

    private AppCompatImageButton mAddMainImage, mAddDescImage, mIconCloseAct;

    private RelativeLayout mRlCategory;

    private RelativeLayout mRlGoodsPrice, mRlGoodsNum, mRlGoodsCarriage, mRlGoodsDiscount;

    private Button mBtnCreate;

    private TextView mTvGoodsPrice, mTvGoodsNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_goods);
        initView();
    }

    @Override
    public void initView() {

        mEtGoodsTitle = getId(R.id.et_goods_title);
        mEtGoodsDesc = getId(R.id.et_goods_desc);

        mTitleCountNumber = getId(R.id.title_count_number);
        mDescCountNumber = getId(R.id.desc_count_number);

        mAddMainImage = getId(R.id.add_title_image);
        mAddDescImage = getId(R.id.add_desc_image);

        mRlCategory = getId(R.id.rl_category);

        mRlGoodsPrice = getId(R.id.linear_goods_price);
        mRlGoodsPrice.setOnClickListener(this);

        mRlGoodsNum = getId(R.id.rl_goods_num);
        mRlGoodsNum.setOnClickListener(this);

        mRlGoodsCarriage = getId(R.id.rl_goods_carriage);
        mRlGoodsCarriage.setOnClickListener(this);


        mRlGoodsDiscount = getId(R.id.rl_goods_discount);
        mRlGoodsDiscount.setOnClickListener(this);

        mBtnCreate = getId(R.id.btn_submit);

        mTvGoodsPrice = getId(R.id.tv_goods_price);
        mTvGoodsNum = getId(R.id.tv_goods_num);


        mIconCloseAct = getId(R.id.icon_close_act);
        mIconCloseAct.setOnClickListener(this);

        etListener();
    }

    private void etListener() {

        mEtGoodsTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEtGoodsDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onNetRequest() {

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {


            case R.id.icon_close_act:

                this.finish();

                break;


            case R.id.linear_goods_price: // 价格


                intentUtils.launchActSlideBottomCarryParametersForResult(this,
                        ModifyGoodsInfoAct.class,
                        assembleIntentParamKey(PARAM), assembleIntentParamValue(PARAM_PRICE),
                        REQUEST_CODE_PRICE);

                break;

            case R.id.rl_goods_num: // 库存

                intentUtils.launchActSlideBottomCarryParametersForResult(this,
                        ModifyGoodsInfoAct.class,
                        assembleIntentParamKey(PARAM), assembleIntentParamValue(PARAM_NUM),
                        REQUEST_CODE_PRICE);


                break;

            case R.id.rl_goods_carriage: // 运费模板


                break;

            case R.id.rl_goods_discount: // 折扣


                break;

        }

    }


    // 组装请求参数Key数组
    private String[] assembleIntentParamKey(String paramKey) {

        return new String[]{paramKey};
    }

    // 组装请求参数Value数组
    private String[] assembleIntentParamValue(String paramValue) {

        return new String[]{paramValue};
    }

    @Override
    public String assembleRequestParam() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (data == null) {

            return;
        }

        if (resultCode == ModifyGoodsInfoAct.RESPONSE_CODE_PRICE) { // 价格

            mTvGoodsPrice.setText(data.getStringExtra(ModifyGoodsInfoAct.ET_COUNT));

        } else if (resultCode == ModifyGoodsInfoAct.RESPONSE_CODE_NUM) {// 库存

            mTvGoodsNum.setText(data.getStringExtra(ModifyGoodsInfoAct.ET_COUNT));
        }

        super.onActivityResult(requestCode, resultCode, data);
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
