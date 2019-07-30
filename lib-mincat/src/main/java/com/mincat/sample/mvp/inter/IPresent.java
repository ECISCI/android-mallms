package com.mincat.sample.mvp.inter;

/**
 * @author Gin
 */
public interface IPresent<V> {

    void attachV(V view);

    void detachV();
}
