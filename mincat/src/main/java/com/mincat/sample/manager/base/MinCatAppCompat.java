package com.mincat.sample.manager.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mincat.sample.R;
import com.mincat.sample.utils.T;


/**
 * @author Gin
 */

public abstract class MinCatAppCompat extends MinCatAppCompatUnifiedManager {

    public static final String TAG = MinCatAppCompat.class.getName();


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {


        super.onCreate(savedInstanceState, persistentState);
    }


    //封装 toolbar 到基类中
    protected void initToolBar(int id) {
        toolbar = (Toolbar) findViewById(id);
        //toolbar.setClickable(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //toolbar返回键按钮
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            activitySlideRight();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //初始化3D效果按钮
    protected void initFabButton(int id) {
        mFabButton = (FloatingActionButton) findViewById(id);
        mFabButton.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_file_upload).color(Color.WHITE).actionBar());
        mFabButton.setOnClickListener(this);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }


    protected Object parseResult(String result, Class<?> cls) {

        return JSONObject.parseObject(result, cls);


    }

    protected void showNetConnectionErrorToast(Context context) {
        T.showLong(context, getString(R.string.http_is_null));
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //显示软键盘
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //如果上面的代码没有弹出软键盘 可以使用下面另一种方式
        //InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.showSoftInput(editText, 0);
    }

}
