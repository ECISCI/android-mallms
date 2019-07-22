package com.mincat.sample.utils.rxJava.inter;


import com.mincat.sample.utils.rxJava.Permission;

public interface OnPermissionListener {

    void onCheckPermission(boolean isTrue);

    void onCheckPermission(Permission permission);
}
