package com.app.dlm.mysdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/28.
 * 返回请求Requst
 */
public class CommonRequest {


    /**
     * 创建一个post的Key-Value请求体
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = builder.build();
        return new Request.Builder().url(url).post(body).build();
    }


    /**
     * 创建一个GET请求体
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createGetReqeust(String url, RequestParams params) {
        StringBuilder sb = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(sb.substring(0, sb.length() - 1)).get().build();
    }

}
