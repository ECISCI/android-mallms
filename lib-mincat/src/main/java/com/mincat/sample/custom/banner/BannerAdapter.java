package com.mincat.sample.custom.banner;

import android.view.View;


public abstract class BannerAdapter implements View.OnClickListener {
    /**
     * 获取View
     */
    public abstract View getView(int position, View convertView);

    /**
     * 获取ViewPager 轮播图的数量
     */
    public abstract int getCount();

    /**
     * 获取广告位描述
     */
    public String getBannerTitle(int position) {
        return "";
    }
    /**
     * 获取广告位描述
     */
    public String getBannerDes(int position) {
        return "";
    }
}
