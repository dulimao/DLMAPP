package com.dlm.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlm.app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/14.
 * 自定义控件：1、继承系统控件，2、组合系统控件，3、自定义绘制控件
 * *  自定义日历控件
 * 步骤：1：布局，初始化控件2：添加业务逻辑，填充数据，3：定制UI和事件
 */
public class CalendarView extends LinearLayout {

    private Button btn_prev, btn_next;
    private TextView tv_curentdata;
    private GridView gd_calendar;

    private Calendar mCalendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");


    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bindControll();
        bindControllEvent();
        renderCalendar();
    }


    private void bindControll() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.calendar_layout, this);
        btn_next = (Button) rootView.findViewById(R.id.btn_next);
        btn_prev = (Button) rootView.findViewById(R.id.btn_prev);
        tv_curentdata = (TextView) rootView.findViewById(R.id.tv_curentdate);
        gd_calendar = (GridView) rootView.findViewById(R.id.grid_calendar);
    }

    private void bindControllEvent() {

        btn_prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mCalendar.add(Calendar.MONTH, -1);//日历向前翻一个月
                renderCalendar();
            }
        });

        btn_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendar.add(Calendar.MONTH, 1);//向后翻一个月
                renderCalendar();

            }
        });

    }

    //绘制日历
    private void renderCalendar() {

        tv_curentdata.setText(format.format(mCalendar.getTime()));

        ArrayList<Date> date_Cells = new ArrayList<>();
        Calendar calendar = (Calendar) mCalendar.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);//将当前日历置于当月第一天
        //当前月份之前有多少天
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //日期向前移到需要绘制的日期
        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);

        int maxCellCount = 6 * 7;
        while (date_Cells.size() < maxCellCount) {
            date_Cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        gd_calendar.setAdapter(new CalendarAdaper(getContext(), date_Cells));

    }


    private class CalendarAdaper extends BaseAdapter {

        private Context mContext;
        private ArrayList<Date> mDateCells;
        private LayoutInflater inflater;

        private CalendarAdaper(Context context, ArrayList<Date> date_Cells) {
            this.mContext = context;
            this.mDateCells = date_Cells;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return mDateCells.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar, parent, false);
            }

            Date date = mDateCells.get(position);
            int day = date.getDate();

            MyTextView textView = (MyTextView) convertView;
            textView.setText(String.valueOf(day));

            Date now = new Date();

            if (now.getYear() == date.getYear() && now.getMonth() == date.getMonth() && now.getDay() == date.getDay()) {
                textView.setDraw(true);
            }

            return convertView;
        }
    }
}
