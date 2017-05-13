package com.dlm.app.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dlm.app.R;
import com.dlm.app.activity.base.BaseActivity;
import com.dlm.app.fragment.HomeFragment;
import com.dlm.app.fragment.MessageFragment;
import com.dlm.app.fragment.MineFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/28.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager fm;
    private HomeFragment mHomeFragmet;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrentFragment;


    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        //默认显示homefragment
        mHomeFragmet = new HomeFragment();
        fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, mHomeFragmet);
        fragmentTransaction.commit();


        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder request = new Request.Builder();
        request.get().url("http://www.baidu.com").build();
        Call call = okHttpClient.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }


    private void initView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout = (RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mPondView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    @Override
    public void onClick(View view) {

        FragmentTransaction transaction = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mMessageFragment, transaction);
                hideFragment(mMineFragment, transaction);
                if (mHomeFragmet == null) {
                    mHomeFragmet = new HomeFragment();
                    transaction.add(R.id.content_layout, mHomeFragmet);
                } else {
                    mCurrentFragment = mHomeFragmet;
                    transaction.show(mCurrentFragment);
                }

                break;
            case R.id.message_layout_view:
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mHomeFragmet, transaction);
                hideFragment(mMineFragment, transaction);
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    transaction.add(R.id.content_layout, mMessageFragment);
                } else {
                    mCurrentFragment = mMessageFragment;
                    transaction.show(mCurrentFragment);
                }

                break;

            case R.id.mine_layout_view:
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                hideFragment(mHomeFragmet, transaction);
                hideFragment(mMessageFragment, transaction);
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.content_layout, mMineFragment);
                } else {
                    mCurrentFragment = mMineFragment;
                    transaction.show(mCurrentFragment);
                }
                break;
        }

        transaction.commit();
    }


    private void hideFragment(Fragment fragment, FragmentTransaction transaction) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }
}
