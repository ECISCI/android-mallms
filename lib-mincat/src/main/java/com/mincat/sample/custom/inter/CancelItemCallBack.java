package com.mincat.sample.custom.inter;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author Gin
 */

public interface CancelItemCallBack {


    void cancelItemSuccess(final Activity activity, String titleName, final int position, final List<?> lists, final RecyclerView.Adapter adapter, AlertDialog dialog);

    void cancelItemCancel(AlertDialog dialog);
}
