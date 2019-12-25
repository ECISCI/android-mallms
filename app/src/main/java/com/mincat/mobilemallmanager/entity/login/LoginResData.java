package com.mincat.mobilemallmanager.entity.login;

import com.mincat.mobilemallmanager.bean.BaseBean;

/**
 * @author Gin
 * @描述 登录成功返回参数
 */
public class LoginResData extends BaseBean {

    /**
     * @描述.返回给用户的token
     */
    private String token;

    /**
     * @描述.用户地址
     */
    private String address;

    /**
     * @描述.用户邮箱
     */
    private String email;

    /**
     * @描述.用户手机号码
     */
    private String phone;

    /**
     * @描述.当前用户类型
     */
    private String userType;

    /**
     * @描述.用户唯一标识ID
     */
    private String user_id;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
