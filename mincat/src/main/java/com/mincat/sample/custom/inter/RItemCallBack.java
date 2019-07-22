package com.mincat.sample.custom.inter;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author Gin
 */

public interface RItemCallBack {

    void removeItemSuccess(final int position, final List<?> lists, final RecyclerView.Adapter adapter, AlertDialog dialog);

    void removeItemCancel(AlertDialog dialog);
}
