package com.mincat.mobilemallmanager.entity.login.request;

/**
 * @author Gin
 * @描述 登录请求实体
 */
public class LoginRequestData {

    private String account;

    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
