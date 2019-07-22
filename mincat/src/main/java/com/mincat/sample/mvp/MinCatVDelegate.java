package com.mincat.sample.mvp;

import android.content.Context;

import com.mincat.sample.mvp.inter.VDelegate;
/**
 * @author Gin
 */
public class MinCatVDelegate implements VDelegate {

    private Context context;

    private MinCatVDelegate(Context context) {
        this.context = context;
    }

    public static VDelegate create(Context context) {

        return new MinCatVDelegate(context);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }
}
