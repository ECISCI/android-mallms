package com.mincat.mobilemallmanager.entity.login.response;

/**
 * @author Gin
 * @描述 登录成功返回参数
 */
public class LoginResponseData {

    private String userAddress;
    private String userEmail;
    private String userGender;
    private String userIcon;
    private String userIdCard;
    private String userLastLogin;
    private String userLevel;
    private String userNickName;
    private String userPhone;
    private String userRealName;
    private String userRegisterDevice;
    private String userRegisterTime;
    private String userType;
    private String userToken;

    public String getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(String userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(String userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserRegisterDevice() {
        return userRegisterDevice;
    }

    public void setUserRegisterDevice(String userRegisterDevice) {
        this.userRegisterDevice = userRegisterDevice;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }


    @Override
    public String toString() {
        return "LoginResponseData{" +
                "userAddress='" + userAddress + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userIdCard='" + userIdCard + '\'' +
                ", userLastLogin='" + userLastLogin + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userRegisterDevice='" + userRegisterDevice + '\'' +
                ", userRegisterTime='" + userRegisterTime + '\'' +
                ", userType='" + userType + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}
