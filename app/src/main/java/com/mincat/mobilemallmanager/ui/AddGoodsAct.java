package com.mincat.mobilemallmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.sample.utils.LogUtils;

import java.util.List;

/**
 * @author Ming
 * @描述 添加商品
 */
public class AddGoodsAct extends MinCatBaseRequest {


    public static final String TAG = AddGoodsAct.class.getSimpleName();

    private static final int REQUEST_CODE_PRICE = 0X0010;
    private static final int REQUEST_CODE_NUM = 0X0011;
    private static final int REQUEST_CODE_EXPRESS = 0X0012;
    private static final int REQUEST_CODE_DISCOUNT = 0X0013;

    private static final int REQUEST_CODE_MULT_PIC_MAIN = 0X0014;
    private static final int REQUEST_CODE_MULT_PIC_DESC = 0X0015;
    public static final String REQUEST_CODE_MULT_PIC_KEY = "extraKey";
    public static final String REQUEST_CODE_MULT_PIC_VALUE_MAIN = "main";
    public static final String REQUEST_CODE_MULT_PIC_VALUE_DESC = "desc";


    public static final String PARAM = "param";

    public static final String PARAM_PRICE = "商品价格";
    public static final String PARAM_NUM = "商品库存";


    private EditText mEtGoodsTitle, mEtGoodsDesc;


    // add_title_image

    /**
     * 控制标题和详情输入字数
     */
    private TextView mTitleCountNumber, mDescCountNumber;

    private AppCompatImageButton mAddMainImage, mAddDescImage, mIconCloseAct;

    private RelativeLayout mRlCategory;

    private RelativeLayout mRlGoodsPrice, mRlGoodsNum, mRlGoodsCarriage, mRlGoodsDiscount;

    private Button mBtnCreate;

    // tv_goods_carriage
    private TextView mTvGoodsPrice, mTvGoodsNum, mTvGoodsCarriage, mTvGoodsDiscount;

    private String strCarriageType, strCarriagePrice, strGoodsDiscount;

    private TextView mTvMainPic, mTvDescPic;

    private List<String> pics;

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

        mTitleCountNumber = getId(R.id.tv_count_number);
        mDescCountNumber = getId(R.id.desc_count_number);

        mAddMainImage = getId(R.id.add_title_image);
        mAddDescImage = getId(R.id.add_desc_image);

        mRlCategory = getId(R.id.rl_category);
        mRlCategory.setOnClickListener(this);

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
        mTvGoodsCarriage = getId(R.id.tv_goods_carriage);
        mTvGoodsDiscount = getId(R.id.tv_discount);


        mIconCloseAct = getId(R.id.icon_close_act);
        mIconCloseAct.setOnClickListener(this);


        mAddMainImage = getId(R.id.add_title_image);
        mAddMainImage.setOnClickListener(this);
        mAddDescImage.setOnClickListener(this);

        mTvMainPic = getId(R.id.tv_main_pic_num);
        mTvDescPic = getId(R.id.tv_desc_pic_num);

        mEtGoodsTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        mEtGoodsDesc.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        etListener();
    }

    private void etListener() {

        mEtGoodsTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mTitleCountNumber.setText(mEtGoodsTitle.getText().toString().length() + "");
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

                mDescCountNumber.setText(mEtGoodsDesc.getText().toString().length() + "");
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
                        REQUEST_CODE_NUM);
                break;

            case R.id.rl_goods_carriage: // 运费模板

                intentUtils.launchActSlideRightForResult(this, CarriageTemplateAct.class, REQUEST_CODE_EXPRESS);


                break;

            case R.id.rl_goods_discount: // 折扣

                intentUtils.launchActSlideRightForResult(this, DiscountAct.class, REQUEST_CODE_DISCOUNT);

                break;


            case R.id.add_title_image:

                Intent intent = new Intent(this, AddPicAct.class);

                requestAddPic(intent, REQUEST_CODE_MULT_PIC_VALUE_MAIN, REQUEST_CODE_MULT_PIC_MAIN);


                break;
            case R.id.add_desc_image:

                Intent intentX = new Intent(this, AddPicAct.class);

                requestAddPic(intentX, REQUEST_CODE_MULT_PIC_VALUE_DESC, REQUEST_CODE_MULT_PIC_DESC);


                break;

            case R.id.rl_category:

                intentUtils.launchActFromRight(this, GoodsCategoryAct.class);

                break;
        }
    }

    private void requestAddPic(Intent intent, String requestExtraValue, int requestCode) {

        intent.putExtra(REQUEST_CODE_MULT_PIC_KEY, requestExtraValue);
        startActivityForResult(intent, requestCode);
        activitySlideRight();
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

            LogUtils.i(TAG, "data为 null");

            return;
        }

        if (resultCode == ModifyGoodsInfoAct.RESPONSE_CODE_PRICE) { // 价格

            mTvGoodsPrice.setText(data.getStringExtra(ModifyGoodsInfoAct.ET_COUNT));

        } else if (resultCode == ModifyGoodsInfoAct.RESPONSE_CODE_NUM) {// 库存

            mTvGoodsNum.setText(data.getStringExtra(ModifyGoodsInfoAct.ET_COUNT));

        } else if (resultCode == CarriageTemplateAct.RESPONSE_CODE_CARRIAGE) { // 快递

            strCarriageType = data.getStringExtra(CarriageTemplateAct.EXPRESS_TYPE);
            strCarriagePrice = data.getStringExtra(CarriageTemplateAct.EXPRESS_PRICE);
            mTvGoodsCarriage.setText(strCarriageType + "/" + strCarriagePrice);

        } else if (resultCode == DiscountAct.RESPONSE_CODE_DISCOUNT) {

            strGoodsDiscount = data.getStringExtra(DiscountAct.RESPONSE_DISCOUNT_INFO);

            mTvGoodsDiscount.setText(strGoodsDiscount + " off");

        } else if (resultCode == AddPicAct.RESPONSE_CODE_MAIN_PIC) {


            pics = (List<String>) data.getSerializableExtra(AddPicAct.RESPONSE_MAIN_PIC);

            mTvMainPic.setText("已添加" + pics.size() + "图片");

        } else if (resultCode == AddPicAct.RESPONSE_CODE_DESC_PIC) {

            pics = (List<String>) data.getSerializableExtra(AddPicAct.RESPONSE_DESC_PIC);

            mTvDescPic.setText("已添加" + pics.size() + "图片");
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
