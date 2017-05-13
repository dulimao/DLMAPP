package com.dlm.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/5/2.
 */
public class MyViewGroup extends RelativeLayout {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MotionEvent", "MyViewGroup-------->>dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("MotionEvent", "MyViewGroup-------->>onInterceptTouchEvent");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MotionEvent", "MyViewGroup-------->>onTouchEvent");
        return super.onTouchEvent(event);

    }

    public void quickSort(int[] data, int start, int end) {
        if (start > end) {
            return;
        }

        //以起始索引为分界点
        int pivot = data[start];
        int i = start + 1;
        int j = end;
        while (true) {
            while (i < end && data[i] < pivot)
                i++;
            while (j > start && data[j] > pivot)
                j++;

            if (i < j) {
                swap(data, i, j);
            } else {
                break;
            }
        }
        //交换j和分界点的值
        swap(data, start, j);
        //递归左子序列
        quickSort(data, start, j - 1);
        //递归右子序列
        quickSort(data, j + 1, end);
    }

    public static void swap(int[] data, int i, int j) {

    }

    public static void insertSort(int [] data){
        for (int i = 1;i<data.length;i++){
            //缓存i处的元素值
            int temp = data[i];
            if (data[i]<data[i-1]){
                int j = i-1;
                //整体后移一格
                while(j>=0 && data[j] >temp){
                    data[j+1] = data[j];
                    j--;
                }
                //最后将tmep的值插入合适的位置
                data[j+1] = temp;
            }
        }
    }
}
