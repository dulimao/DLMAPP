package com.dlm.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dlm.app.R;
import com.dlm.app.fragment.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/28.
 */
public class MineFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_layout,container,false);
        return view;
    }
}
