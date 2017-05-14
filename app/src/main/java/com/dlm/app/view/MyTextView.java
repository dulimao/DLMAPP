package com.dlm.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dlm.app.utils.DensityUtil;

/**
 * Created by Administrator on 2017/5/14.
 */
public class MyTextView extends TextView {

    private Paint mPaint = new Paint();
    private boolean isDraw;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStrokeWidth(DensityUtil.dip2px(2));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    public void setDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isDraw) {
//            canvas.translate(getWidth() / 2, getHeight() / 2);
//            canvas.drawCircle(0, 0, getWidth() / 2, mPaint);
            Rect rect = new Rect(0,0,getWidth(),getHeight());
            canvas.drawRect(rect,mPaint);

        }
        super.onDraw(canvas);
    }
}
