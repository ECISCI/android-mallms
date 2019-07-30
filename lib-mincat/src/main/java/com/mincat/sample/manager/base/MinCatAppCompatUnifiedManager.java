package com.mincat.sample.manager.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mincat.sample.R;
import com.mincat.sample.manager.inter.MinCatInitUi;
import com.mincat.sample.utils.IntentUtils;

/**
 * @author Gin
 */

public abstract class MinCatAppCompatUnifiedManager extends AppCompatActivity implements MinCatInitUi {

    public static final String TAG = MinCatAppCompatUnifiedManager.class.getName();

    // 导航栏
    protected Toolbar toolbar;

    // 3DButton
    protected FloatingActionButton mFabButton;

    // 页面跳转
    protected IntentUtils intentUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        intentUtils = IntentUtils.getInstance();
        super.onCreate(savedInstanceState);

    }

    // 获View 上的文本
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

    // 启动Activity 右→左滑入
    protected void activitySlideRight() {

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }


    // 启动Activity 底→顶滑入
    protected void activitySlideBottom() {
        overridePendingTransition(R.anim.slide_bottom_in, R.anim.slide_bottom_out);
    }

    //关闭软键盘
    protected void closeInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(MinCatAppCompatUnifiedManager.this.getCurrentFocus().getWindowToken()
                , InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // 加载图片
    protected void loadImage(String imageUrl, ImageView imageView) {

//        Glide
//                .with(this)
//                .load(imageUrl)
//                .placeholder(R.drawable.bg_transparent)
//                .error(R.drawable.bg_transparent)
//                .into(imageView);

    }


    //弹出软键盘
    protected void showInput(EditText edit) {
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) edit.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    // 当页面销毁时调用
    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        System.gc();
    }

    // 当点击返回键按钮时调用
    @Override
    public void onBackPressed() {
        System.gc();
        super.onBackPressed();
        activitySlideRight();

    }

}
