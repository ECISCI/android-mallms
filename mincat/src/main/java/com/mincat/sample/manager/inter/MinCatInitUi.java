package com.mincat.sample.manager.inter;

import android.view.View;

/**
 * @author Gin
 * @描述 基础接口
 */

public interface MinCatInitUi extends View.OnClickListener {

    /**
     * 初始化参数
     */
    void initView();

    /**
     * 网络请求
     */
    void onNetRequest();

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    void onClick(View view);


    /**
     * 组装Json参数
     *
     * @return
     */
    String assembleRequestParam();
}
