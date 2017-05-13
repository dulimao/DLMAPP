package com.dlm.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dlm.app.R;
import com.dlm.app.fragment.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2017/4/28.
 */
public class MessageFragment extends BaseFragment {

    private View mContentView;
    private ImageView imageview;
    private SimpleDraweeView simpleDraweeView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_message_layout,container,false);
        initView();

        return mContentView;
    }

    private void initView() {


//        imageview = (ImageView) mContentView.findViewById(R.id.imageview);
//        ImageLoaderManager.getmInstance(getActivity()).displayImage(imageview,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494031637&di=448db60b7b6f12393fa805a9066883dd&imgtype=jpg&er=1&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F287%2F54%2F6984E5MIK8K2.jpg");
        simpleDraweeView = (SimpleDraweeView) mContentView.findViewById(R.id.sdv_1);
        simpleDraweeView.setImageURI("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494031637&di=448db60b7b6f12393fa805a9066883dd&imgtype=jpg&er=1&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F287%2F54%2F6984E5MIK8K2.jpg");
    }
}
