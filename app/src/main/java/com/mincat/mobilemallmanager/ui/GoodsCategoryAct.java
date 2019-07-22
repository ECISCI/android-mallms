package com.mincat.mobilemallmanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.adapter.CategoryAdapter;
import com.mincat.mobilemallmanager.fra.CategoryFra;

/**
 * @author Ming
 * @描述 商品分类
 */
public class GoodsCategoryAct extends MinCatBaseRequest implements
        AdapterView.OnItemClickListener {


    private String[] items = {"手机", "电脑", "家电", "图书", "面膜", "口红", "零食",};

    private ListView mGoodsItemList;

    private CategoryAdapter adapter;

    private CategoryFra mCategoryFra;

    public static int mPosition;

    private FragmentTransaction fragmentTransaction;

    private Bundle bundle;

    private AppCompatImageButton mCloseAct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_goods_category);
        initView();
    }

    @Override
    public void initView() {

        mGoodsItemList = getId(R.id.goods_item_list);

        adapter = new CategoryAdapter(this, items);

        mGoodsItemList.setAdapter(adapter);
        mGoodsItemList.setOnItemClickListener(this);

        //创建MyFragment对象
        mCategoryFra = new CategoryFra();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mCategoryFra);

        //通过bundle传值给MyFragment
        bundle = new Bundle();
        bundle.putString(CategoryFra.TAG, items[mPosition]);
        mCategoryFra.setArguments(bundle);
        fragmentTransaction.commit();


        mCloseAct = getId(R.id.icon_close_act);
        mCloseAct.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position;
        // 即使刷新adapter
        adapter.notifyDataSetChanged();

        for (int i = 0; i < items.length; i++) {
            mCategoryFra = new CategoryFra();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, mCategoryFra);

            Bundle bundle = new Bundle();
            bundle.putString(CategoryFra.TAG, items[position]);

            mCategoryFra.setArguments(bundle);
            fragmentTransaction.commit();
        }
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
        }

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
