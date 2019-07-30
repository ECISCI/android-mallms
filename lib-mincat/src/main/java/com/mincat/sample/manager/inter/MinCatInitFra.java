package com.mincat.sample.manager.inter;

import android.view.View;

/**
 * @author Gin
 */
public interface MinCatInitFra extends View.OnClickListener {

    /**
     * 初始化方法
     *
     * @param view
     */
    void initView(View view);

    /**
     * 点击事件
     *
     * @param v view对象
     */
    @Override
    void onClick(View v);

    /**
     * 组装Json参数
     *
     * @return
     */
    String assembleRequestParam();
}
