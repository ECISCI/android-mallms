package com.mincat.sample.mvp.inter;

import android.os.Bundle;
import android.view.View;

/**
 * @author Gin
 */

public interface IView<P> {

    // 获取界面布局
    int setLayout();

    // 绑定界面控件
    void bindUI(View rootView);

    // 初始化监听事件
    void bindEvent();

    // 设置界面公共信息
    void initData(Bundle savedInstanceState);

    // 显示界面信息
    void showUI();

    // 初始化MVP对象
    P newP();
}
