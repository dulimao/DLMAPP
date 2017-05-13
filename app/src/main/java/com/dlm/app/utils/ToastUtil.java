package com.dlm.app.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.dlm.app.R;


/**
 * Created by Administrator on 2017/4/16.
 */
public class ToastUtil {

    private static Toast sToast = null;


    public static void showToast(Context context, String msg) {
        ToastUtil.showToast(context, msg, true);
    }


    //通用toast
    public static void showToast(Context context, String msg, boolean repeat) {
        showToast(context, msg, repeat, false, 60);
    }

    public static void showToast(Context context, String msg, boolean repeat, boolean showGravity, int toBottomDistinct) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        if (sToast == null) {
            sToast = new Toast(context);
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.bg_toast_text);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(14);
            int paddingLeft = DensityUtil.getResourceDimens(context, R.dimen.toast_padding_left);
            int paddingTop = DensityUtil.getResourceDimens(context, R.dimen.toast_padding_top);
            textView.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
            sToast.setView(textView);
            sToast.setDuration(2000);
        }
        if (showGravity) {
            sToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            sToast.setGravity(Gravity.BOTTOM, 0, DensityUtil.dip2px(toBottomDistinct));
        }

        TextView tv = (TextView) sToast.getView();
        if (repeat) {
            tv.setText(msg);
            sToast.show();
        } else {
            if (!tv.getText().toString().equals(msg)) {
                tv.setText(msg);
                sToast.show();
            }
        }

    }
}
