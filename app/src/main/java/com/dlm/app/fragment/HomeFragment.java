package com.dlm.app.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.dlm.mysdk.okhttp.execption.OkHttpException;
import com.app.dlm.mysdk.okhttp.listener.DisposDataListener;
import com.dlm.app.R;
import com.dlm.app.adapter.CourseAdapter;
import com.dlm.app.fragment.base.BaseFragment;
import com.dlm.app.module.recommend.BaseRecommandModel;
import com.dlm.app.netcenter.RequestCenter;
import com.dlm.app.utils.ToastUtil;

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    /*
    UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mCategoryView;
    private TextView mQRCodeView;
    private TextView mSearchView;
    private ImageView mLoadingView;


    private BaseRecommandModel mRecommandData;
    private CourseAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "HomeFragment";
        requestRecommandData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);

        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    //发送首页列表数据请求
    private void requestRecommandData() {

        RequestCenter.requestRecommandData(new DisposDataListener() {
            @Override
            public void onSuccess(Object resposeObj) {
                showSuccess(resposeObj);
            }


            @Override
            public void onFaiure(Object reasonObj) {
                OkHttpException e = (OkHttpException) reasonObj;
                Log.d(TAG, "请求失败" + "msg:" + e.getEmsg() + " code:" + e.getEcode() + " message:" + e.getMessage());
                ToastUtil.showToast(mContext, "请求失败");

            }
        });
    }

    private void showSuccess(Object resposeObj) {
        mLoadingView.setVisibility(View.GONE);

        BaseRecommandModel m = (BaseRecommandModel) resposeObj;
        if (m.data.list != null && m.data.list.size() > 0) {
            mListView.setVisibility(View.VISIBLE);
            mAdapter = new CourseAdapter(mContext, m.data.list);
            mListView.setAdapter(mAdapter);
        } else {
            showErrorView();
        }


    }

    public void showErrorView() {
        ToastUtil.showToast(mContext, "数据错误");
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
