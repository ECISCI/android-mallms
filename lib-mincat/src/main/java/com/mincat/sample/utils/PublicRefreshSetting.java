package com.mincat.sample.utils;

import android.content.Context;

import com.mincat.sample.custom.ClassicsHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

public class PublicRefreshSetting {


    public static void setRefreshAttribute(Context context, RefreshLayout refreshLayout, boolean loadMore, int headerHeight) {

        refreshLayout.setEnableAutoLoadMore(loadMore);
        refreshLayout.setRefreshHeader(new ClassicsHeader(context));
        refreshLayout.setHeaderHeight(headerHeight);

    }
}
