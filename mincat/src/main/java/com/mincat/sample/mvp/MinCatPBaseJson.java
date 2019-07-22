package com.mincat.sample.mvp;

import android.content.Context;

import com.mincat.sample.mvp.inter.IView;
import com.mincat.sample.utils.ConstantsRx;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
/**
 * @author  Gin
 */
public class MinCatPBaseJson<V extends IView> extends MinCatPresent<V> {

    public static final String TAG = MinCatPBaseJson.class.getSimpleName();

    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected Map<String, String> mHeaders = null; // 添加请求头

    protected Context context;


    protected RequestBody getRequestBody(String body) {

        return RequestBody.create(JSON, body);
    }

    protected void addRequestHeaders(String sign) {
        if (mHeaders == null) {
            mHeaders = new HashMap<>();
        }
        mHeaders.clear();
        mHeaders.put(ConstantsRx.CONTENT_TYPE_KEY, ConstantsRx.CONTENT_TYPE_VALUE);
    }
}
