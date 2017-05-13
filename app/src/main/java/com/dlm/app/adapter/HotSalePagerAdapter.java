package com.dlm.app.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.dlm.mysdk.ImageLoadUtil.ImageLoaderManager;
import com.dlm.app.R;
import com.dlm.app.module.recommend.RecommandBodyValue;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/29.
 */
public class HotSalePagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<RecommandBodyValue> values;
    private ImageLoaderManager mImageLoader;

    public HotSalePagerAdapter(Context context, ArrayList<RecommandBodyValue> value) {
        this.mContext = context;
        this.values = value;
        this.mInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoaderManager.getmInstance(mContext);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecommandBodyValue value = values.get(position % values.size());
        View contentView = mInflater.inflate(R.layout.item_hot_product_pager_layout, container,false);
        TextView titleView = (TextView) contentView.findViewById(R.id.title_view);
        TextView infoView = (TextView) contentView.findViewById(R.id.info_view);
        TextView gonggaoView = (TextView) contentView.findViewById(R.id.gonggao_view);
        TextView saleView = (TextView) contentView.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = (ImageView) contentView.findViewById(R.id.image_one);
        imageViews[1] = (ImageView) contentView.findViewById(R.id.image_two);
        imageViews[2] = (ImageView) contentView.findViewById(R.id.image_three);

        titleView.setText(value.title);
        infoView.setText(value.price);
        gonggaoView.setText(value.info);
        saleView.setText(value.text);
        for (int i = 0; i < imageViews.length; i++) {
            mImageLoader.displayImage(imageViews[i], value.url.get(i));
        }
        container.addView(contentView, 0);
        return contentView;
    }
}
