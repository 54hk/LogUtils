package com.page.android.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.page.android.Bean.Person;
import com.page.android.Constants;
import com.page.android.R;
import com.page.android.db.DbManner;
import com.page.android.db.MySqliteHelper;
import com.page.android.utils.AppUtils;
import com.page.android.utils.DateTimePicker;

import java.io.Serializable;
import java.util.List;

public class DbActivity extends AppCompatActivity implements View.OnClickListener {
    int isRemark = -1;
    private Context mContext;
    private MySqliteHelper helper;
    private SQLiteDatabase db;
    private Button intert;
    private Button delete;
    private Button update;
    private Button select, deleteToPerson, updateToPerson;
    private EditText etMoney, etAddress, etOtherHouse, etYao;//金额  交易地点/附言  账户  摘要
    private CheckBox remarkout, remarkIn; //消费 收入
    DateTimePicker dateTimePicker = new DateTimePicker();
    private TextView tvShowTrun;
    private EditText etBalance;
    private LinearLayout llFour;
    private int id;
    boolean isReturn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        mContext = DbActivity.this;
        helper = DbManner.getInstance(mContext);
        initFindID();
        creat();
    }

    private void initFindID() {
        llFour = findViewById(R.id.ll_four);
        etBalance = findViewById(R.id.et_balance);
        intert = findViewById(R.id.intert);
        intert.setOnClickListener(this);
        delete = findViewById(R.id.delete);
        deleteToPerson = findViewById(R.id.delete_to_person);
        deleteToPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //删除
                SQLiteDatabase writableDatabase = helper.getWritableDatabase();
                DbManner.execSQL(writableDatabase, "delete from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_ID + "=" + id);
                writableDatabase.close();
                toase("删除成功");
                llFour.setVisibility(View.VISIBLE);
                deleteToPerson.setVisibility(View.GONE);
                updateToPerson.setVisibility(View.GONE);
                etMoney.setText("");
                etAddress.setText("");
                etOtherHouse.setText("");
                etYao.setText("");
                tvShowTrun.setText("");
                etBalance.setText("");
                isRemark = -1;
                remarkout.setChecked(false);
                remarkIn.setChecked(false);
            }
        });
        updateToPerson = findViewById(R.id.update_to_person);
        updateToPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跟新
                String moneyStr = etMoney.getText().toString();
                String balanceStr = etBalance.getText().toString();

                String addressStr = etAddress.getText().toString();

                String otherHouseStr = etOtherHouse.getText().toString();

                String showTrun = tvShowTrun.getText().toString();

                String etYaoStr = etYao.getText().toString();
                if (moneyStr.equals("") || balanceStr.equals("")|| addressStr.equals("")|| otherHouseStr.equals("") || showTrun.equals("显示时间") || showTrun.equals("")
                        || etYaoStr.equals("")) {
                    toase("填写完整");
                    return;
                } else {
                    SQLiteDatabase writableDatabase = helper.getWritableDatabase();


                    DbManner.execSQL(writableDatabase, "update  " + Constants.DB_TABLE_NAME
                            + " set " + Constants
                            .TABLE_MONEY + "=" + Float.valueOf(etMoney.getText().toString()) + "," + Constants
                            .TABLE_REMARK + "=" + isRemark + "," + Constants
                            .TABLE_ADDRESS + "='" + etAddress.getText().toString() + "'," + Constants
                            .TABLE_TIME + "=" + AppUtils.date2TimeStamp(tvShowTrun.getText().toString(), "yyyy-MM-dd HH:mm") + "," + Constants
                            .OTHER_HOUSE + "='" + etOtherHouse.getText().toString() + "'," + Constants
                            .TABLE_BLANCE + "=" + Float.valueOf(etBalance.getText().toString()) + ","
                            + Constants.TABLE_YAO + "='" + etYao.getText().toString()
                            + " 'where " + Constants.TABLE_ID + "=" + id);
                    writableDatabase.close();
                    toase("更新成功");
                    llFour.setVisibility(View.VISIBLE);
                    deleteToPerson.setVisibility(View.GONE);
                    updateToPerson.setVisibility(View.GONE);
                    etMoney.setText("");
                    etAddress.setText("");
                    etOtherHouse.setText("");
                    etYao.setText("");
                    tvShowTrun.setText("");
                    etBalance.setText("");
                    isRemark = -1;
                    remarkout.setChecked(false);
                    remarkIn.setChecked(false);

                }


            }
        });
        delete.setOnClickListener(this);
        update = findViewById(R.id.update);
        update.setOnClickListener(this);
        select = findViewById(R.id.select);
        select.setOnClickListener(this);
        etMoney = findViewById(R.id.et_money);
        remarkout = findViewById(R.id.remark_out);
        remarkIn = findViewById(R.id.remark_in);
        etYao = findViewById(R.id.et_yao);
        etAddress = findViewById(R.id.et_address);
        etOtherHouse = findViewById(R.id.et_other_house);
        tvShowTrun = findViewById(R.id.tv_show_trun);
        tvShowTrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dateTimePicker.init(mContext, tvShowTrun, true);
                }
//                dateTimePicker.initTimePicker(mContext);
            }
        });
        remarkout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    remarkIn.setChecked(false);
                    isRemark = 1;
                }
            }
        });
        remarkIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    remarkout.setChecked(false);
                    isRemark = 2;
                }
            }
        });
    }

    /**
     * 点击常见数据库
     */
    public void creat() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {


//        public static String CREAT_TABLE = "create table " + DB_TABLE_NAME + "(" + TABLE_ID + " integer primary key  autoincrement," +
//                TABLE_MONEY + " integer ," + TABLE_REMARK + " varchar(20) " + TABLE_ADDRESS + "varchar(50) )";
        switch (view.getId()) {
            case R.id.intert: //增加

                // 跟新
                String moneyStr = etMoney.getText().toString();
                String balanceStr = etBalance.getText().toString();

                String addressStr = etAddress.getText().toString();

                String otherHouseStr = etOtherHouse.getText().toString();

                String showTrun = tvShowTrun.getText().toString();

                String etYaoStr = etYao.getText().toString();
                if (moneyStr.equals("") || balanceStr.equals("")|| addressStr.equals("")|| otherHouseStr.equals("") || showTrun.equals("显示时间") || showTrun.equals("")
                        || etYaoStr.equals("")) {
                    toase("填写完整");
                    return;
                } else {
                    SQLiteDatabase writableDatabase = helper.getWritableDatabase();
                    String intertSql = "insert into " + Constants.DB_TABLE_NAME + "(" + Constants
                            .TABLE_MONEY + "," + Constants
                            .TABLE_REMARK + "," + Constants
                            .TABLE_ADDRESS + "," + Constants
                            .TABLE_TIME + "," + Constants
                            .OTHER_HOUSE + "," + Constants
                            .TABLE_BLANCE + "," + Constants.TABLE_YAO + ") values(" + Float.valueOf(etMoney.getText().toString())
                            + "," + isRemark + ",'" + etAddress.getText().toString() + "'," + AppUtils.date2TimeStamp(tvShowTrun.getText().toString() + "",
                            "yyyy-MM-dd HH:mm") + ",'" + etOtherHouse.getText().toString() + "',"
                            + Float.valueOf(etBalance.getText().toString()) + ",'" + etYao.getText().toString() + "')";
                    DbManner.execSQL(writableDatabase, intertSql);
                    writableDatabase.close();

                    etMoney.setText("");
                    etAddress.setText("");
                    etOtherHouse.setText("");
                    etYao.setText("");
                    tvShowTrun.setText("");
                    etBalance.setText("");
                    isRemark = -1;
                    remarkout.setChecked(false);
                    remarkIn.setChecked(false);
                    toase("增加成功");
                }

                break;
            case R.id.delete: //删除
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                startActivityForResult(new Intent(mContext, DbOperateActivity.class)
                        .putExtra("what", "delete"), 2);
                break;
            case R.id.update: //更新
                startActivityForResult(new Intent(mContext, DbOperateActivity.class)
                        .putExtra("what", "update"), 2);
                break;
            case R.id.select://查询
                startActivity(new Intent(mContext, DbOperateActivity.class).putExtra("what", "select"));
//                SQLiteDatabase writableDatabsase = helper.getWritableDatabase();
//                String selectSql = "select * from " + Constants.DB_TABLE_NAME + " ";
//                Cursor cursor = DbManner.SelectBySql(writableDatabsase, selectSql, null);
//                List<Person> list = DbManner.curserToList(cursor);
//                for (Person person : list) {
//                    Log.e("YYYY", person.toString());
//                }
//                writableDatabsase.close();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void toase(String mgs) {
        Toast.makeText(this, mgs, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == 1) { //更新
            if (null != data) {
                Person people = data.getParcelableExtra("people");
                id = people.get_id();
                etMoney.setText(people.getMoney() + "");
                etAddress.setText(people.getAddress());
                etOtherHouse.setText(people.getOtherHouse());
                etYao.setText(people.getYao());
                tvShowTrun.setText(AppUtils.timeStamp2Date(people.getTime() + "", "yyyy-MM-dd HH:mm:ss") + "");
                etBalance.setText(people.getTableBlance() + "");
                if (people.getRemark() == 1) {
                    remarkout.setChecked(true);
                    remarkIn.setChecked(false);
                } else {
                    remarkout.setChecked(false);
                    remarkIn.setChecked(true);
                }
                if (null != data.getStringExtra("what")) {
                    llFour.setVisibility(View.GONE);
                    if (data.getStringExtra("what").equals("delete")) {
                        //s删除
                        deleteToPerson.setVisibility(View.VISIBLE);
                        updateToPerson.setVisibility(View.GONE);
                    } else {
                        //更新
                        updateToPerson.setVisibility(View.GONE);
                        updateToPerson.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    public void panduan() {


    }
}
