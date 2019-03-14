package com.page.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.page.android.Constants;

/**
 * Created by 54hk on 2019/3/7.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context, Constants.DB_TABLE_NAME, null, Constants.DB_VERSION);

    }

    /**
     * 当数据库常见的回调函数
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREAT_TABLE);
        Log.e("db", "创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("db", "onUpgrade");
    }

    /**
     * 数据库打开的时候
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.e("db", "onOpen");
    }
}
