package com.app.dlm.mysdk.okhttp.listener;

/**
 * Created by Administrator on 2017/4/28.
 */
public class DisponseDataHandle {

    public DisposDataListener mListener = null;
    public Class<?> mClass = null;

    public DisponseDataHandle(DisposDataListener mListener) {
        this.mListener = mListener;
    }

    public DisponseDataHandle(DisposDataListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }

}
