package com.page.android.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;

import com.page.android.Bean.Person;
import com.page.android.Constants;
import com.page.android.R;
import com.page.android.adapter.BillAdapter;
import com.page.android.db.DbManner;
import com.page.android.db.MySqliteHelper;
import com.page.android.utils.MyListView;

import java.util.ArrayList;
import java.util.List;

public class DbOperateActivity extends AppCompatActivity {

    MyListView listView;

    List<Person> personList = new ArrayList<>();
    private MySqliteHelper helper;
    private SQLiteDatabase dbBase;
    private BillAdapter billAdapter;
    private String what = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_operate);
        what = getIntent().getStringExtra("what");
        listView = findViewById(R.id.list_view);
        selectDb();
        for (int i = 0; i < personList.size(); i++) {//全展示踹
            personList.get(i).isShow = true;
        }
        billAdapter = new BillAdapter(this, personList);
        listView.setAdapter(billAdapter);
        billAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("people", personList.get(position));
                intent.putExtra("what", what);
                setResult(1, intent);
                finish();
            }
        });
    }

    private void selectDb() {
        helper = DbManner.getInstance(this);
        dbBase = helper.getWritableDatabase();
        String selectSql = "select * from " + Constants.DB_TABLE_NAME;
        Cursor cursor = DbManner.SelectBySql(dbBase, selectSql, null);
        List<Person> list = DbManner.curserToList(cursor);
        personList.addAll(list);
    }


}
