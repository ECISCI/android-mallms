package com.mincat.sample.mvp;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mincat.sample.mvp.inter.IModel;
import com.mincat.sample.mvp.utils.NetError;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author Gin
 */
public abstract class MinCatApiSubscriber<T extends IModel> extends ResourceSubscriber<T> {


    @Override
    public void onError(Throwable e) {

        NetError error = null;

        if (e != null) {
            if (!(e instanceof NetError)) {

                if (e instanceof UnknownHostException || e instanceof SocketTimeoutException || e instanceof ConnectException) {

                    error = new NetError(e, NetError.NoConnectError);

                } else if (e instanceof JSONException || e instanceof JsonParseException || e instanceof JsonSyntaxException) {

                    error = new NetError(e, NetError.ParseError);

                } else {

                    error = new NetError(e, NetError.OtherError);
                }
            } else {

                error = (NetError) e;
            }

            if (useCommonErrorHandler() && XApi.getCommonProvider() != null) {

                if (XApi.getCommonProvider().handleError(error)) { //使用通用异常处理
                    return;
                }
            }
            errorListener(error);
        }
    }

    protected abstract void errorListener(NetError error);

    @Override
    public void onComplete() {

    }

    protected boolean useCommonErrorHandler() {
        return true;
    }
}
