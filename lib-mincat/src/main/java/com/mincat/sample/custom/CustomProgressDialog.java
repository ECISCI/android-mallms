package com.mincat.sample.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mincat.sample.R;
import com.mincat.sample.utils.MResource;


/**
 * Created by Google on 2016/10/14.
 *
 * @author Mings
 * @描述 加载进度条
 */
public class CustomProgressDialog {

    // 进度条
    private Dialog pd;

    private static CustomProgressDialog utils = new CustomProgressDialog();

    private TextView tv_desc;
    private ImageView imageView;

    public static CustomProgressDialog getInstance() {
        return utils;

    }

    /**
     * @param context      此处必须是 Activity.this 不可用getApplicationContext
     * @param isCancelable 点击周边是否可以销毁dialog
     */
    public void showProgressDialog(Context context,
                                   boolean isCancelable, String desc) {

        View view = View.inflate(context, R.layout.custom_dialog, null);

        pd = new Dialog(context, MResource.getIdByName(context, "style",
                "progress_dialog"));

        pd.setContentView(view);

        pd.setCancelable(isCancelable);

        tv_desc = view.findViewById(MResource.getIdByName(context, "id", "tv_desc"));

        tv_desc.setText(desc);

        pd.getWindow().setBackgroundDrawableResource(MResource.getIdByName(context, "color", "transparent"));

        pd.show();
    }

    // 关闭进度条
    public void closeProgressDialog() {

        if (pd != null && pd.isShowing()) {

            pd.dismiss();
            System.gc();
            pd = null;
        }
    }
}
