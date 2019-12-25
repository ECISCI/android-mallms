package com.mincat.mobilemallmanager.fra;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.entity.pics.PicsJsonBean;
import com.mincat.mobilemallmanager.entity.pics.PicsReq;
import com.mincat.mobilemallmanager.entity.pics.PicsRes;
import com.mincat.mobilemallmanager.utils.Constants;
import com.mincat.mobilemallmanager.utils.GlideImageLoader;
import com.mincat.mobilemallmanager.utils.HandleUtils;
import com.mincat.sample.imagecache.utils.Constant;
import com.mincat.sample.manager.fra.MinCatFragment;
import com.mincat.sample.utils.LogUtils;
import com.mincat.sample.utils.PublicRefreshSetting;
import com.mincat.sample.utils.T;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gin
 * @描述 首页
 */
public class HomeFra extends MinCatFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = HomeFra.class.getSimpleName();

    private static final int INIT_LUNBO_PICS = 0X0010;

    private static final int REQUEST_DATA_NULL = 0X0012;

    private static final int AUTH_ERROR = 0X0013;

    private Banner banner;

    private List<PicsRes> picsResList = new ArrayList<>();

    private List<String> images = new ArrayList<>();
    private List<String> bannersTitle = new ArrayList<>();

    private RefreshLayout refreshLayout;

    private AppCompatImageView mImageFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_home, null);

        initView(view);

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void initView(final View view) {
        banner = getId(R.id.banner, view);

        refreshLayout = getId(R.id.refreshLayout, view);
        mImageFlag = getId(R.id.image_flag, view);

        initRefresh();

        onNetRequest();
    }

    private void onNetRequest() {


        executeVolleyPostRequest(getActivity(), Constants.MAIN_LUNBO_PICS, assembleRequestParam(), Constants.MAIN_LUNBO_PICS_SIGN, false);

    }

    private void initRefresh() {

        PublicRefreshSetting.setRefreshAttribute(getActivity(), refreshLayout, false, 80);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        refreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });
    }


    /**
     * @描述.初始化轮播图
     */
    private void initLunBoPics() {


        //增加点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(5000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        banner.setBannerTitles(bannersTitle);
        //设置图片集合
        banner.setImages(images);

        banner.isAutoPlay(true);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String assembleRequestParam() {

        PicsReq req = new PicsReq();
        req.setType("01");

        return JSONObject.toJSONString(req);
    }

    @Override
    public void onHandleResponsePost(String response, String sign) {


        LogUtils.i(TAG, "response:" + response);

        if (sign.equals(Constants.MAIN_LUNBO_PICS_SIGN)) {


            PicsJsonBean bean = JSONObject.parseObject(response, PicsJsonBean.class);

            picsResList = bean.getData();

            if (bean.getCode() == Constants.REQUEST_SUCCESS_CODE
                    && picsResList != null
                    && picsResList.size() > 0) {

                for (PicsRes data : picsResList) {

                    images.add(data.getImageUrl());
                    bannersTitle.add(data.getImageDesc());
                }

                if (images.size() > 0 && bannersTitle.size() > 0 && images.size() == bannersTitle.size()) {

                    HandleUtils.sendHandle(handler, INIT_LUNBO_PICS, Constants.NULL_STRING);
                }

            } else if (bean.getCode() == Constants.AUTH_CODE) {

                HandleUtils.sendHandle(handler, AUTH_ERROR, Constants.NULL_STRING);

            } else {

                HandleUtils.sendHandle(handler, REQUEST_DATA_NULL, Constants.NULL_STRING);
            }


        }
    }

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case INIT_LUNBO_PICS: // 初始化呢轮播图

                    initLunBoPics();
                    break;


                case AUTH_ERROR: // 获取轮播图参数为null

                    T.showShort(getActivity(), "认证失败");

                    break;
                case REQUEST_DATA_NULL:

                    T.showShort(getActivity(), "暂无相关数据");

                    break;
            }

            super.handleMessage(msg);
        }
    };
}
