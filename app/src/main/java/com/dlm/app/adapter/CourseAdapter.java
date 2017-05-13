package com.dlm.app.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.dlm.mysdk.ImageLoadUtil.ImageLoaderManager;
import com.dlm.app.R;
import com.dlm.app.module.recommend.RecommandBodyValue;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/29.
 */
public class CourseAdapter extends BaseAdapter {

    private static final int CARD_COUNT = 4;
    private static final int VIDEO_TYPE = 0X00;
    private static final int CARD_SIGLE_PIC = 0X01;
    private static final int CARD_MUTIL_PIC = 0X02;
    private static final int CARD_VIEW_PAGER = 0X03;

    private Context mContext;
    private LayoutInflater mInflater;
    private ViewHolder mViewHolder;
    private ArrayList<RecommandBodyValue> mData;

    private ImageLoaderManager mImageLoader;


    public CourseAdapter(Context context, ArrayList<RecommandBodyValue> mData) {
        this.mContext = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoaderManager.getmInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return ((RecommandBodyValue) getItem(position)).type;
//        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        int viewType = getItemViewType(position);
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            switch (viewType) {
                case CARD_SIGLE_PIC:
                case CARD_MUTIL_PIC:
                case VIDEO_TYPE:
                    convertView = mInflater.inflate(R.layout.item_product_card_one_layout, viewGroup, false);
                    mViewHolder.mLogoView = (CircleImageView) convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = (TextView) convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = (TextView) convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = (TextView) convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = (TextView) convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = (TextView) convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = (TextView) convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout = (LinearLayout) convertView.findViewById(R.id.product_photo_layout);
                    mViewHolder.mProductView = (ImageView) convertView.findViewById(R.id.item_produce_view);
                    break;
                case CARD_VIEW_PAGER:
                    convertView = mInflater.inflate(R.layout.item_product_card_three_layout, viewGroup, false);
                    mViewHolder.mViewPager = (ViewPager) convertView.findViewById(R.id.pager);
                    break;
                default:
                    convertView = mInflater.inflate(R.layout.item_product_card_one_layout, viewGroup, false);
                    break;
            }
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        //绑定数据
        switch (viewType) {
            case CARD_SIGLE_PIC:
            case CARD_MUTIL_PIC:
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat("天前"));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
//                mViewHolder.mZanView.setText("点赞".concat(value.zan));


                mImageLoader.displayImage(mViewHolder.mLogoView, value.logo);
                if (value.url != null && value.url.size() > 0)
                    mImageLoader.displayImage(mViewHolder.mProductView, value.url.get(0));
                break;
            case CARD_VIEW_PAGER:

                ArrayList<RecommandBodyValue> values = parseToRecommandBodyValueList(value);

                mViewHolder.mViewPager.setAdapter(new HotSalePagerAdapter(mContext, values));
                mViewHolder.mViewPager.setCurrentItem(values.size()*100);


                break;
        }


        return convertView;
    }


    private static ArrayList<RecommandBodyValue> parseToRecommandBodyValueList(RecommandBodyValue value) {

        ArrayList<RecommandBodyValue> values = new ArrayList<>();
        String[] titles = value.title.split("@");
        String[] infos = value.info.split("@");
        String[] prices = value.price.split("@");
        String[] texts = value.text.split("@");
        ArrayList<String> urls = value.url;

        int start = 0;
        for (int i = 0; i < titles.length; i++) {
            RecommandBodyValue bodyValue = new RecommandBodyValue();
            bodyValue.title = titles[i];
            bodyValue.info = infos[i];
            bodyValue.price = prices[i];
            bodyValue.text = texts[i];
            bodyValue.url = getUrls(urls, start, 3);
            start += 3;
            values.add(bodyValue);
        }
        return values;


    }

    private static ArrayList<String> getUrls(ArrayList<String> urls, int start, int interval) {
        ArrayList<String> urlList = new ArrayList<String>();
        for (int i = start; i < start + interval; i++) {
            urlList.add(urls.get(i));
        }
        return urlList;
    }

    private static class ViewHolder {
        //所有Card共有属性
        private CircleImageView mLogoView;
        private TextView mTitleView;
        private TextView mInfoView;
        private TextView mFooterView;
        //Video Card特有属性
        private RelativeLayout mVieoContentLayout;
        private ImageView mShareView;

        //Video Card外所有Card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        //Card One特有属性
        private LinearLayout mProductLayout;
        //Card Two特有属性
        private ImageView mProductView;
        //Card Three特有属性
        private ViewPager mViewPager;
    }
}
