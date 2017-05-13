package com.app.dlm.mysdk.okhttp;

import com.app.dlm.mysdk.okhttp.listener.DisponseDataHandle;
import com.app.dlm.mysdk.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/28.
 * 请求的发送，请求参数配置，支持https
 */
public class CommonOkHttpClient {

    private static OkHttpClient mOkHttpClient;
    private static final int TIME_OUT = 30;//超时事件

    static{

        OkHttpClient.Builder builder= new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        builder.followRedirects(true);//支持重定向

        //https支持
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        //TODO 待研究
//        builder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        //生成OkhttpClient对象
        mOkHttpClient = builder.build();
    }


    /**
     * 发送请求
     * @param request
     * @param
     * @return
     */
    public static Call get(Request request, DisponseDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
