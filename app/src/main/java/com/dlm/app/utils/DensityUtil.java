package com.dlm.app.utils;

import android.content.Context;

/**
 * 
 * @author jieranyishen
 * 
 */
public class DensityUtil
{

    public static float scale = 1;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue)
    {
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getResourceDimens(Context context,int resId){
        return context.getResources().getDimensionPixelSize(resId);
    }


    public static void initValues(Context context){
        scale = context.getResources().getDisplayMetrics().density;
    }
}
