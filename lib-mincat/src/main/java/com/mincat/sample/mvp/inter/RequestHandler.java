package com.mincat.sample.mvp.inter;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author  Gin
 */

public interface RequestHandler {

    Request onBeforeRequest(Request request, Interceptor.Chain chain);

    Response onAfterRequest(Response response, Interceptor.Chain chain);
}
