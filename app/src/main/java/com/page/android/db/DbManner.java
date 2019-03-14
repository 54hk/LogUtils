package com.page.android.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.page.android.Bean.Person;
import com.page.android.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 54hk on 2019/3/7.
 */

public class DbManner {
    private static MySqliteHelper sqliteHelper;

    public static MySqliteHelper getInstance(Context context) {
        if (null == sqliteHelper) {
            sqliteHelper = new MySqliteHelper(context);
        }
        return sqliteHelper;
    }

    public static void execSQL(SQLiteDatabase db, String sql) {
        if (null != db && !sql.equals("")) {
            db.execSQL(sql);
        }
    }

    /**
     * @param db
     * @param sql
     * @param selectionArgs 查询条件的占位符
     * @return
     */
    public static Cursor SelectBySql(SQLiteDatabase db, String sql, String[] selectionArgs) {
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    /**
     * 讲cursor弄成集合
     *
     * @param cursor
     * @return
     */
    public static List<Person> curserToList(Cursor cursor) {
        List<Person> list = new ArrayList<>();
        Person person;
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex(Constants.TABLE_ID));
            float money = cursor.getFloat(cursor.getColumnIndex(Constants.TABLE_MONEY));
            int remark = cursor.getInt(cursor.getColumnIndex(Constants.TABLE_REMARK));
            String address = cursor.getString(cursor.getColumnIndex(Constants.TABLE_ADDRESS));
            long time = cursor.getLong(cursor.getColumnIndex(Constants.TABLE_TIME));
            String yao = cursor.getString(cursor.getColumnIndex(Constants.TABLE_YAO));
            String house = cursor.getString(cursor.getColumnIndex(Constants.OTHER_HOUSE));
            float blance = cursor.getFloat(cursor.getColumnIndex(Constants.TABLE_BLANCE));
//            (int _id, int money, int remark, String address, long time
            person = new Person(_id, money, remark, address, time,house,blance,yao);
            list.add(person);
        }
        return list;
    }
}
