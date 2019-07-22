package com.mincat.sample.utils.statusBar;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * @Modifier Gin
 */
public class AndroidWorkaround {

    public static void assistActivity(View content, ScrollView scrollView) {
        new AndroidWorkaround(content, scrollView);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private ViewGroup.LayoutParams frameLayoutParams;

    private AndroidWorkaround(View content, final ScrollView scrollView) {
        if (content != null) {
            mChildOfContent = content;
            mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    possiblyResizeChildOfContent(scrollView);
                }
            });
        }
    }

    private void possiblyResizeChildOfContent(ScrollView scrollView) {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        int screenHeight = mChildOfContent.getRootView().getHeight();
        int heightDifference = screenHeight - r.bottom;
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) scrollView.getLayoutParams();
        params.setMargins(0, 0, 0, heightDifference);
        scrollView.requestLayout();
    }

    private void possiblyResizeChildOfContent() {
        frameLayoutParams = mChildOfContent.getLayoutParams();
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            //如果两次高度不一致
            //将计算的可视高度设置成视图的高度
            frameLayoutParams.height = usableHeightNow;
            mChildOfContent.requestLayout();//请求重新布局
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        //计算视图可视高度
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom);
    }

}
