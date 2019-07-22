package com.mincat.sample.mvp.utils;

/**
 * @Modifier Gin
 */
public class NetError extends Exception {

    private Throwable exception;
    private int type = NoConnectError;

    // 数据解析异常
    public static final int ParseError = 0;
    // 无连接异常
    public static final int NoConnectError = 1;
    // 用户验证异常
    public static final int AuthError = 2;
    // 无数据返回异常
    public static final int NoDataError = 3;
    // 业务异常
    public static final int BusinessError = 4;
    // 其他异常
    public static final int OtherError = 5;

    private String mErrorCode;

    public NetError(Throwable exception, int type) {
        this.exception = exception;
        this.type = type;
    }

    public NetError(String errorCode, String detailMessage, int type) {
        super(detailMessage);
        mErrorCode = errorCode;
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (exception != null) return exception.getMessage();
        return super.getMessage();
    }

    public int getType() {
        return type;
    }

    public String getErrorCode() {
        return mErrorCode;
    }
}
