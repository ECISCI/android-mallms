package com.mincat.mobilemallmanager.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.mincat.mobilemallmanager.R;
import com.mincat.sample.manager.web.MinCatWebAct;
import com.mincat.sample.utils.LogUtils;

import static android.view.KeyEvent.KEYCODE_BACK;

public class PublicWeb extends MinCatWebAct {


    /**
     * WebSettings
     */
    protected WebSettings settings;

    /**
     * WebView对象,必须对此对象进行初始化
     */
    protected WebView webView;
    /**
     * 进度条
     */
    private ProgressBar progressbar;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_public_web);

        initView();
    }

    @Override
    public void initView() {
        progressbar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.FILL_PARENT, 25, 0, 0));
        progressbar.setProgress(0);
        webView = getId(R.id.web);
        webView.addView(progressbar);

    }

    @Override
    public void onNetRequest() {

    }

    /**
     * WebSettings 设置
     *
     * @param webUrl webView加载的url
     */
    protected void setWebSettings(String webUrl) {

        if (webView == null) {
            webViewIsNull();
            return;
        }

        settings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webView必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        //支持放大缩小
        settings.setBuiltInZoomControls(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setDisplayZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        //支持方法缩小
        settings.setSupportZoom(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);


        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                }

                return false;
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    if (progressbar.getVisibility() == View.GONE)
                        progressbar.setVisibility(View.VISIBLE);
                    progressbar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.loadUrl(webUrl);
    }

    /**
     * @描述 WebView后退操作
     */
    protected void goBack() {

        if (webView == null) {
            webViewIsNull();
            return;
        } else {

            if (webView.canGoBack())
                webView.goBack();
            else
                LogUtils.i(TAG, "当前页面已经回退到最后一页了");

        }
    }

    /**
     * @描述 WebView前进操作
     */
    protected void goForward() {

        if (webView == null) {
            webViewIsNull();

            return;
        } else {

            if (webView.canGoForward())
                webView.goForward();
            else
                LogUtils.i(TAG, "当前页面已经前进到最前一页了");
        }
    }


    /**
     * @描述 清除本地缓存
     */
    protected void clear() {

        if (webView == null) {
            webViewIsNull();
            return;
        } else {

            webView.clearCache(true);
            webView.clearHistory();
        }
    }

    /**
     * @描述 WEB对象还没有初始化, 打印此日志
     */
    private void webViewIsNull() {
        LogUtils.i(TAG, "WebView还没有初始化");
    }

    /**
     * 当点击回退键的时候对WebView进行页面后退操作
     *
     * @param keyCode 不知道
     * @param event   事件
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (webView == null) {

            webViewIsNull();
            return true;
        }
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public String assembleRequestParam() {
        return null;
    }
}
