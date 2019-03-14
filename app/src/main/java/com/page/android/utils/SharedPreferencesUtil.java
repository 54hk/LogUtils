package com.page.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.page.android.Constants;


public class SharedPreferencesUtil {
    /**
     * 设置全部的用户信息
     */
    public static void setLoginState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.IS_SAVE_MESSAGE, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", Constants.USER_NAME);
        editor.putString("userMoney", Constants.USER_MONEY);
        editor.putString("userBank", Constants.USER_BANK);
        editor.putString("currentTime", Constants.CURRENT_TIME);
        editor.putString("loginUpTime", Constants.LOGIN_UP_TIME);
        editor.putString("accountTime", Constants.ACCOUNT_TIME);
        editor.putString("cardNumber", Constants.CARD_NUMBER);
        editor.putString("LOGIN_UP_TIME_PRATIVE" ,Constants.LOGIN_UP_TIME_PRATIVE);
        editor.commit();
    }

    /**
     * 移除全部的用户信息
     */
    public static void removeLoginState(Context context) {
        String login_up_time_prative = (String) SharedPreferencesUtil.getParam(context, "LOGIN_UP_TIME_PRATIVE", "");
        SharedPreferences sharedPreferences = Constants.mContext.getSharedPreferences(Constants.IS_SAVE_MESSAGE, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Constants.USER_NAME = "";
        Constants.USER_MONEY = "";
        Constants.USER_BANK = "";
        Constants.CURRENT_TIME = ""; //当前时间
        Constants.LOGIN_UP_TIME = "";//上次登录时间
        Constants.ACCOUNT_TIME = "";//开户时间
        Constants.CARD_NUMBER = "";//卡号
//        SharedPreferencesUtil.setParam(context, "LOGIN_UP_TIME_PRATIVE", login_up_time_prative+ "");

    }

    public static void getLoginState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.IS_SAVE_MESSAGE, Context.MODE_MULTI_PROCESS);
        if (sharedPreferences != null && !sharedPreferences.getString("userName", "").equals("")) {
            Constants.USER_NAME = sharedPreferences.getString("userName", "");
            Constants.USER_MONEY = sharedPreferences.getString("userMoney", "");
            Constants.USER_BANK = sharedPreferences.getString("userBank", "");
            Constants.CURRENT_TIME = sharedPreferences.getString("currentTime", "");
            Constants.LOGIN_UP_TIME = sharedPreferences.getString("loginUpTime", "");
            Constants.ACCOUNT_TIME =  sharedPreferences.getString("accountTime", "");
            Constants.CARD_NUMBER =  sharedPreferences.getString("cardNumber", "");
        }

    }

    /**
     * 修改用户信息
     */
    public static void setParam(Context context, String key, Object object) {

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(Constants.IS_SAVE_MESSAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }


    /**
     * 删除key
     */
    public static void removeKey(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(Constants.IS_SAVE_MESSAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object getParam(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = Constants.mContext.getSharedPreferences(Constants.IS_SAVE_MESSAGE, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }


}
