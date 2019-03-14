package com.page.android.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

/**
 * Created by mafanwei on 2018/5/21.
 * 选择时间工具类
 */

public class DateTimePicker implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Context context;
    private static DatePickerDialog datePickerDialog;
    private static TimePickerDialog pickerDialog;
    private static Calendar calendar;
    private static TextView editText;
    private boolean isShow;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void init(Context context1, TextView editText1, boolean isShow) {
        calendar = Calendar.getInstance();
        editText = editText1;
        context = context1;
        this.isShow = isShow; //是否显示小时
        datePickerDialog = new DatePickerDialog(context, this, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        editText.setText(String.valueOf(new StringBuilder()
                .append(year)
                .append("-")
                .append((month + 1) < 10 ? "0"
                        + (month + 1) : (month + 1))
                .append("-")
                .append((day < 10) ? "0" + day : day)));
        if (isShow)
            initTimePicker(context);
        Log.e("TTTT", "++++++++++++++++" + AppUtils.date2TimeStamp(editText.getText().toString(), "yyyy-MM-dd"));
//        initTimePicker(context);
    }


    public void initTimePicker(Context context1) {
        pickerDialog = new TimePickerDialog(context, this, 0, 0, true);
        pickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String s = (hourOfDay < 10 ? "0" + hourOfDay : hourOfDay) + ":" + (minute < 10 ? "0" + minute : minute);
        editText.setText(editText.getText().toString() + " " + s);
        Log.e("TTTT", AppUtils.date2TimeStamp(editText.getText().toString(), "yyyy-MM-dd HH:mm"));
    }
}
