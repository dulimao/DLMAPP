package com.app.dlm.mysdk.okhttp.listener;

/**
 * Created by Administrator on 2017/4/28.
 * 自定义事件监听
 */
public interface DisposDataListener {


    /**
     * 成功回调
     * @param resposeObj
     */
    public void onSuccess(Object resposeObj);

    /**
     * 失败回调
     * @param reasonObj
     */
    public void onFaiure(Object reasonObj);

}
