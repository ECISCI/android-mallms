package com.mincat.sample.adapter.inter;

/**
 * @author Gin
 */

public abstract class MinCatAdapterItemClickListener<T, F> {
    /**
     * @param position 点击位置
     * @param model    实体
     * @param tag      标签
     * @param holder   控件
     * @描述 单击事件
     */
    public void onItemClick(int position, T model, int tag, F holder) {
    }

    /**
     * 长按事件
     *
     * @param position 位置
     * @param model    实体
     * @param tag      标签
     * @param holder   控件
     */
    public void onItemLongClick(int position, T model, int tag, F holder) {
    }
}