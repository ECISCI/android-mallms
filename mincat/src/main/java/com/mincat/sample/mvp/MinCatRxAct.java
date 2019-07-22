package com.mincat.sample.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.mincat.sample.mvp.inter.IPresent;
import com.mincat.sample.mvp.inter.IView;
import com.mincat.sample.mvp.inter.VDelegate;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
/**
 * @author Gin
 */
public abstract class MinCatRxAct<P extends IPresent> extends RxAppCompatActivity implements IView<P> {

    private VDelegate vDelegate;

    private P p;

    protected Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        context = this;
        if (setLayout() > 0) {
            setContentView(setLayout());
            bindUI(null);
            bindEvent();
        }
        initData(savedInstanceState);
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
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }
    @Override
    public void bindEvent() {

    }


}
