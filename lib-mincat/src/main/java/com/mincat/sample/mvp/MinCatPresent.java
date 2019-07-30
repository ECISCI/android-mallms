package com.mincat.sample.mvp;

import com.mincat.sample.mvp.inter.IPresent;
import com.mincat.sample.mvp.inter.IView;

import java.lang.ref.WeakReference;
/**
 * @author Gin
 */
public class MinCatPresent<V extends IView> implements IPresent<V> {

    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<V>(view);
    }

    @Override
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }
    protected V getV() {
        if (v == null) {
            throw new NullPointerException("v can not be null");
        }
        return v.get();
    }
}
