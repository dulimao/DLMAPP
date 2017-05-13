package com.dlm.app.fragment.base;


import android.app.Fragment;
import android.content.Context;

/**
 * Created by Administrator on 2017/4/28.
 */
public class BaseFragment extends Fragment {

    public String TAG = "BaseFragment";
    public Context mContext;

    @Override
    public void onStart() {
        super.onStart();
        mContext = getActivity().getApplicationContext();
//        TAG = mContext.get
    }
}
