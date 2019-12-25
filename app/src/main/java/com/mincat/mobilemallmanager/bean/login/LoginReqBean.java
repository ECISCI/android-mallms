package com.mincat.mobilemallmanager.bean.login;

import com.mincat.mobilemallmanager.bean.BaseBean;
import com.mincat.mobilemallmanager.entity.login.LoginResData;

import java.util.ArrayList;

public class LoginReqBean extends BaseBean {


    private ArrayList<LoginResData> data;

    public ArrayList<LoginResData> getData() {
        return data;
    }

    public void setData(ArrayList<LoginResData> data) {
        this.data = data;
    }
}
