package com.mincat.mobilemallmanager.bean;

import com.mincat.mobilemallmanager.bean.BaseBean;
import com.mincat.mobilemallmanager.entity.login.response.LoginResponseData;

import java.util.ArrayList;

/**
 * @author Ming
 *
 * @描述
 */
public class LoginBean extends BaseBean {


    private ArrayList<LoginResponseData> data;

    public ArrayList<LoginResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<LoginResponseData> data) {
        this.data = data;
    }
}
