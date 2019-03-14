package com.page.android.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 54hk on 2019/3/6.
 * 全局 使用的一些方法！
 */

public class AppUtils {
    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 指定日期是星期几
     */
    public static String getDayofWeek(long dateTime) {

        Date date = new Date(dateTime);
        String Week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int wek = c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "周日";
        }
        if (wek == 2) {
            Week += "周一";
        }
        if (wek == 3) {
            Week += "周二";
        }
        if (wek == 4) {
            Week += "周三";
        }
        if (wek == 5) {
            Week += "周四";
        }
        if (wek == 6) {
            Week += "周五";
        }
        if (wek == 7) {
            Week += "周六";
        }
        return Week;

    }

    /**
     * * 日期格式字符串转换成时间戳
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 时间错弄成String
     */
    public static String timeStamp2Date(String seconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        long time = new Long(seconds); //1480428796是unix时间戳，
        return sdf.format(new Date(time));

    }

    /**
     * 获取时间粗  当天  七天  一月
     *
     * @param posion
     * @return
     */
    public static Long getLongTime(long posion) {
        String currentTime = AppUtils.getCurrentTime("yyyy-MM-dd"); //等到当前的时间
        String oCurrentTime = AppUtils.date2TimeStamp(currentTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if (posion == 0) {
            return Long.valueOf(oCurrentTime);
        } else
            return Long.valueOf(Long.valueOf(oCurrentTime) - 1000 * 60 * 60 * 24 * posion);

    }

    /**
     * 获取时间 当天  七天  一月
     * @param num
     * @return
     */
    public static String nowBeforeMuchDays(int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -num);
        Date monday = c.getTime();
        String time = sdf.format(monday);
        return time;
    }

    /**
     * 早上  中午 上午  下午
     */
    public static void getDate(TextView tv) {
        Date d = new Date();
        if (d.getHours() < 11) {
            tv.setText("早上好!");
        } else if (d.getHours() < 13) {
            tv.setText("中午好!");
        } else if (d.getHours() < 18) {
            tv.setText("下午好!");
        } else if (d.getHours() < 24) {
            tv.setText("晚上好!");
        }
    }

    public static String getCardPassword(String moneyStr) {
        if (moneyStr.length() >= 8) {
            return moneyStr.substring(0, 4) + "***" + moneyStr.substring(moneyStr.length() - 4, moneyStr.length());
        }
        return moneyStr;
    }
}
