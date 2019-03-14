package com.page.android;

import android.content.Context;

/**
 * Created by 54hk on 2019/3/7.
 */

public class Constants {
    public static Context mContext;


    public static String DB_TABLE_NAME = "info"; //数据库表名
    public static int DB_VERSION = 1;//数据库的版本

    //字段
    public static String TABLE_ID = "_id"; //表id
    public static String TABLE_MONEY = "table_money"; //金额
    public static String TABLE_REMARK = "table_remark";//   1.支付  2.收入
    public static String TABLE_ADDRESS = "table_address";//交易地点/附言
    public static String TABLE_TIME = "table_time";//时间
    public static String OTHER_HOUSE = "other_house"; //对方账户
    public static String TABLE_BLANCE = "table_blance";//余额

    public static String TABLE_YAO = "table_yao";//摘要
    //    sql语句
    public static String CREAT_TABLE = "create table " + DB_TABLE_NAME + "(" + TABLE_ID + " integer primary key  autoincrement," +
            TABLE_MONEY + " float ," + TABLE_REMARK + " float, " + TABLE_ADDRESS + " varchar(50)," + TABLE_TIME + " long, "
            + OTHER_HOUSE + " varchar(50) ," + TABLE_BLANCE + " float ,"+TABLE_YAO+" varchar(50) )"; //创建表

    // 用户资料
    public static String IS_SAVE_MESSAGE = "IS_SAVE_MESSAGE";//保存的用户信息
    public static String USER_NAME = "";//用户名称
    public static String USER_MONEY = "";//用户的金额
    public static String USER_BANK = "";//开户网点
    public static String CURRENT_TIME = "";//当前时间
    public static String LOGIN_UP_TIME = "";//上次登录时间
    public static String LOGIN_UP_TIME_PRATIVE = "";//上次时间
    public static String ACCOUNT_TIME = "";//开户时间
    public static String CARD_NUMBER = "";//卡号
    public static String BALANCE = "";//余额
}
