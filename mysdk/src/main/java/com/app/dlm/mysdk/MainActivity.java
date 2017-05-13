package com.app.dlm.mysdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testOkhttpClient() {
        //发送一个GET请求
//        CommonOkHttpClient.get(CommonRequest.createGetReqeust("http://www.baid.com", null),
//                new CommonJsonCallback(new DisponseDataHandle(new DisposDataListener() {
//                    @Override
//                    public void onSuccess(Object resposeObj) {
//
//                    }
//
//                    @Override
//                    public void onFaiure(Object reasonObj) {
//
//                    }
//                })));
    }


    public void testUniversalImageLoader() {

        //配置ImageLoader的相关参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader imageLoader = ImageLoader.getInstance();

        //图片显示的相关配置
        DisplayImageOptions options = new DisplayImageOptions.Builder().build();

        imageLoader.displayImage("url", new ImageView(this), options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
            }
        });

    }
}


