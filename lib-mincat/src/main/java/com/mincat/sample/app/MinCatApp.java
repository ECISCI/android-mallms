package com.mincat.sample.app;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.mincat.sample.mvp.XApi;
import com.mincat.sample.mvp.inter.NetProvider;
import com.mincat.sample.mvp.inter.RequestHandler;
import com.mincat.sample.mvp.utils.NetError;
import com.mincat.sample.mvp.utils.SSLSocketClient;
import com.mincat.sample.utils.Constants;
import com.mincat.sample.utils.LogUtils;

import org.xutils.BuildConfig;
import org.xutils.x;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author Gin
 * @描述 应用程序启动
 */
public class MinCatApp extends Application {

    public static MinCatApp applicationUtils = new MinCatApp();

    // 此处flag设置为false 将屏蔽所有L类日志的打印
    public boolean flag = true;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationUtils = this;
        // 在此处对Xutils进行初始化！！！如果不进行初始化Xutils网络访问不可使用
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        init();
        // 必须在此处注册Provider
        initRxJavaOkHttp();
    }

    private void initRxJavaOkHttp() {
        XApi.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {
                //忽略https配置
                builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());//配置
                builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());//配置
            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return new RequestHandler() {
                    @Override
                    public Request onBeforeRequest(Request request, Interceptor.Chain chain) {

                        Request.Builder builder = request.newBuilder();
                        //  builder.addHeader("sysId", getSysId());

                        return builder.build();
                    }

                    @Override
                    public Response onAfterRequest(Response response, Interceptor.Chain chain) {
                        return null;
                    }

                };
            }

            @Override
            public long configConnectTimeoutMills() {
                return 14000;
            }

            @Override
            public long configReadTimeoutMills() {
                return 14000;
            }

            @Override
            public boolean configLogEnable() {
                return false;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public boolean dispatchProgressEnable() {
                return false;
            }
        });
    }

    // 初始化
    private void init() {
        LogUtils.isDebug = flag;

        if (Constants.Config.DEVELOPER_MODE
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll().penaltyLog().penaltyDeath().penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll().penaltyLog().penaltyDeath()
                    .detectLeakedSqlLiteObjects().build());
        }
    }


    // MyApp
    public static MinCatApp getInstance() {
        return applicationUtils;
    }

}
