package com.mincat.sample.mvp.inter;


/**
 * @author Gin
 */
public interface IModel {

    /**
     * 空数据
     *
     * @return
     */
    boolean responseDataIsNull();

    /**
     * 验证错误
     *
     * @return
     */
    boolean authError();

    /**
     * 错误码
     *
     * @return
     */
    String getErrCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getErrorMsg();


}
