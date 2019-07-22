package com.mincat.sample.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mincat.sample.R;
import com.mincat.sample.custom.MCUIState;
import com.mincat.sample.mvp.inter.IView;
import com.mincat.sample.mvp.inter.IPresent;
import com.mincat.sample.mvp.inter.VDelegate;
import com.mincat.sample.utils.ContextManager;
import com.mincat.sample.utils.SystemUtils;
import com.mincat.sample.utils.router.Router;
import com.mincat.sample.utils.rxJava.RxPermissions;
import com.mincat.sample.utils.statusBar.ImmersionBar;
import com.mincat.sample.utils.statusBar.StatusBarCompat;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 * @param <P>
 * @author Gin
 * @描述 基于RXJava基类
 */
public abstract class XBaseAct<P extends IPresent> extends RxFragmentActivity implements IView<P>, View.OnClickListener {


    protected String TAG = getClass().getSimpleName();
    private VDelegate vDelegate;
    private P p;
    protected Activity context;

    // 权限
    private RxPermissions mRxPermissions;

    protected ViewGroup mMainBody;

    protected boolean isTemplate = true;

    // titleBar
    protected View mTitleBar;

    // titleBar 标题描述
    protected TextView mTitleDesc;

    // titleBar 左侧按钮,右侧按钮
    protected LinearLayout mLeftButton, mRightButton;

    // titleBar左侧文字,右侧文字
    protected TextView mTitleLft, mTitleRight;

    public View abstractView;

    // 占用布局的FrameLayout
    private MCUIState mUiStateController;

    private boolean isShowContent = true;

    private Router mRouter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.act_rx_base);

        initView();
        initEvent();
        if (setLayout() > 0) {
            setContentView(setLayout());
            bindUI(null);
            bindEvent();
        }
        initData(savedInstanceState);
        showUI();
    }

    @Override
    public void bindUI(View rootView) {

    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = MinCatVDelegate.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }


    protected RxPermissions getRxPermissions() {
        mRxPermissions = new RxPermissions(this);
        return mRxPermissions;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.act_rx_base) {
            abstractView = LayoutInflater.from(this).inflate(layoutResID, null);
            super.setContentView(abstractView);
        } else {
            mMainBody.removeAllViews();
            View inflate = this.getLayoutInflater().inflate(layoutResID, null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mMainBody.addView(inflate);

        }
    }

    public int getMainBodyHeight() {
        return mMainBody.getHeight();
    }

    @Override
    public void setContentView(View view) {
        mMainBody.removeAllViews();
        mMainBody.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mMainBody.removeAllViews();
        mMainBody.addView(view, params);
    }

    private void initView() {
        mMainBody = getId(R.id.view_mainBody);

        mTitleBar = getId(R.id.titleBar);

        mTitleLft = getId(R.id.title_tv_left);

        mTitleRight = getId(R.id.title_tv_right);

        mLeftButton = getId(R.id.title_ll_left);

        mRightButton = getId(R.id.title_ll_right);

        mUiStateController = getId(R.id.base_ui_controller);

        mTitleDesc = getId(R.id.title_text);

    }

    private void initEvent() {
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackView();

            }
        });
    }

    protected void clickBackView() {
        if (isShowContent) {
            SystemUtils.closeKeyBroad(context);
        }
        ContextManager.getInstance().popActivity();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ContextManager.getInstance().pushActivity(this);
        mRouter = Router.newIntent(this);
        StatusBarCompat.getInstance().setStatusBarWhiteBg(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        setIntent(intent);
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();

        getvDelegate().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;

        ImmersionBar.with(this).destroy();
    }

    // 获EditText 上的文本
    protected String etString(EditText et) {

        String str = et.getText().toString().trim();

        return str;
    }

    // 获取控件ID
    protected <T extends View> T getId(int id) {
        try {
            return (T) findViewById(id);
        } catch (ClassCastException e) {
            throw e;
        }
    }
}
