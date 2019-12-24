package com.mincat.mobilemallmanager.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.sample.manager.file.MinCatDownloadFile;
import com.mincat.sample.manager.inter.MinCatDownLoadListener;
import com.mincat.sample.utils.LogUtils;

import java.io.File;

public class TestGetVerifyCodeAct extends MinCatBaseRequest {

    private static final String TAG = TestGetVerifyCodeAct.class.getSimpleName();

    private AppCompatImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_test_get_code);
        initView();
    }

    @Override
    public void initView() {

        image = getId(R.id.image);

        onNetRequest();

    }

    @Override
    public void onNetRequest() {


        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (TestGetVerifyCodeAct.this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    TestGetVerifyCodeAct.this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                } else {
                    //这里就是权限打开之后自己要操作的逻辑

                    download();
                }
            }
        }




    }

    private void download(){

        MinCatDownloadFile.downloadFile(this, "http://10.0.2.2:8085/panda/getVerifyCode", "/temp", false, new MinCatDownLoadListener() {


            @Override
            public void onSuccessListener(File result) {


            }

            @Override
            public void onErrorListener(Throwable ex, boolean isOnCallback) {
                LogUtils.i(TAG, "Error:" + ex);
            }

            @Override
            public void onLoadingListener(long total, long current, boolean isDownloading) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public String assembleRequestParam() {
        return null;
    }

    @Override
    public void onHandleResponsePost(String response, String sign) {

    }

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {

    }
}
