package com.dlm.app.netcenter;

import com.app.dlm.mysdk.okhttp.CommonOkHttpClient;
import com.app.dlm.mysdk.okhttp.listener.DisponseDataHandle;
import com.app.dlm.mysdk.okhttp.listener.DisposDataListener;
import com.app.dlm.mysdk.okhttp.request.CommonRequest;
import com.app.dlm.mysdk.okhttp.request.RequestParams;
import com.dlm.app.module.recommend.BaseRecommandModel;

/**
 * Created by Administrator on 2017/4/28.
 * 存放应用中所有的请求
 */
public class RequestCenter {


    /**
     * 根据参数发送所有post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param listener 回调监听
     * @param clasz    实体类
     */
    public static void postRequest(String url, RequestParams params, DisposDataListener listener, Class<?> clasz) {
        CommonOkHttpClient.get(CommonRequest.createGetReqeust(url, params), new DisponseDataHandle(listener, clasz));
    }

    /**
     * 发送首页请求
     *
     * @param listener
     */
    public static void requestRecommandData(DisposDataListener listener) {
        postRequest(HttpConstants.BAIDU_URL, null, listener, BaseRecommandModel.class);
    }

}
