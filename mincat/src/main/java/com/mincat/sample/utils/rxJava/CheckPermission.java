package com.mincat.sample.utils.rxJava;

import android.Manifest;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.mincat.sample.utils.T;
import com.mincat.sample.utils.rxJava.inter.OnPermissionListener;

import rx.functions.Action1;

/**
 * @Modifier Gin
 */
public class CheckPermission {
    private static CheckPermission rxPermissions;

    public static CheckPermission getInstance() {
        if (rxPermissions == null) {
            rxPermissions = new CheckPermission();
        }
        return rxPermissions;
    }

    /**
     * 申请单个权限
     *
     * @param activity
     * @param permissions
     */
    public void requestSingle(final Activity activity, final String... permissions) {
        RxPermissions.getInstance(activity)
                .request(permissions)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            Toast.makeText(activity, "您已经授权该权限", Toast.LENGTH_SHORT).show();
                        } else {
                            // 未获取权限
                            Toast.makeText(activity, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void requestSingle(final Activity activity, final OnPermissionListener onPermissionListener, final String... permissions) {
        RxPermissions.getInstance(activity)
                .request(permissions)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (onPermissionListener != null) {
                            onPermissionListener.onCheckPermission(granted);
                        }
                    }
                });
    }

    /**
     * 点击控件出发权限
     *
     * @param activity
     * @param permissions
     */
    public void requestClick(View view, final Activity activity, final String... permissions) {
        RxView.clicks(view)
                .compose(RxPermissions.getInstance(activity).ensure(permissions))
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {

                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            Toast.makeText(activity, "您已经授权该权限", Toast.LENGTH_SHORT).show();
                        } else {
                            // 未获取权限
                            Toast.makeText(activity, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 同时请求多个权限合并返回结果
     *
     * @param activity
     * @param permissions
     */
    public void requestMerge(final Activity activity, final String... permissions) {
        RxPermissions.getInstance(activity).request(permissions)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            Toast.makeText(activity, "您已经授权该权限", Toast.LENGTH_SHORT).show();
                        } else {
                            // 未获取权限
                            Toast.makeText(activity, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void requestMerge(final Activity activity, final OnPermissionListener onPermissionListener, final String... permissions) {
        RxPermissions.getInstance(activity).request(permissions)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (onPermissionListener != null) {
                            onPermissionListener.onCheckPermission(granted);
                        }
                    }
                });
    }

    /**
     * 同时请求多个权限分别返回结果
     *
     * @param activity
     * @param permissions
     */
    public void requestEach(final Activity activity, final String... permissions) {
        RxPermissions.getInstance(activity).requestEach(permissions)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.name.equals(Manifest.permission.CAMERA)) {
                            if (permission.granted) {


                            } else {

                                T.showLong(activity, "您没有授权该权限，请在设置中打开授权");
                            }

                        } else if (permission.name.equals(Manifest.permission.RECORD_AUDIO)) {

                        }
                    }
                });
    }

    public void requestEach(final Activity activity, final OnPermissionListener onPermissionListener, final String... permissions) {
        RxPermissions.getInstance(activity).requestEach(permissions)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (onPermissionListener != null) {
                            onPermissionListener.onCheckPermission(permission);
                        }
                    }
                });
    }

}
