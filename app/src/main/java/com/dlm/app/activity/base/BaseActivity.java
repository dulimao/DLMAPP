package com.dlm.app.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/4/28.
 */
public class BaseActivity extends AppCompatActivity {

    public String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }
}
