package com.page.android.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.page.android.Bean.Person;
import com.page.android.Constants;
import com.page.android.R;
import com.page.android.adapter.BillAdapter;
import com.page.android.db.DbManner;
import com.page.android.db.MySqliteHelper;
import com.page.android.utils.AppUtils;
import com.page.android.utils.MyListView;

import java.util.ArrayList;
import java.util.List;

public class BillFragment extends Fragment {

    public static String LONG_TIME = "LONG_TIME";
    private MyListView listView;
    private MySqliteHelper helper;
    private SQLiteDatabase dbBase;
    private BillAdapter billAdapter;
    private long longTime;
    private TextView outTv, outTvNull;
    private TextView inTv, inTvNull;
    private LinearLayout ll_null;
    List<Person> personList = new ArrayList<>();
    private TextView tvUntilTime, tvUntilTime1;

    public static BillFragment getInstance(long time) {
        BillFragment billFragment = new BillFragment();
        Bundle bunlde = new Bundle();
        bunlde.putLong(LONG_TIME, time);
        billFragment.setArguments(bunlde);
        return billFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.bill_fragment, null);
        longTime = getArguments().getLong(LONG_TIME, 0);

        outTvNull = inflate.findViewById(R.id.out_tv_null);
        inTvNull = inflate.findViewById(R.id.in_tv_null);
        ll_null = inflate.findViewById(R.id.ll_null);
        tvUntilTime = inflate.findViewById(R.id.tv_until_time);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        listView = inflate.findViewById(R.id.list_view);
        helper = DbManner.getInstance(getActivity());
        dbBase = helper.getWritableDatabase();
//        String outMoney = "select sum(" + Constants.TABLE_MONEY + ") from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_REMARK + "=1"; //支付
//        Cursor cursor1 = dbBase.rawQuery(outMoney, null); //获取支出的总和
//        Log.e("TTTT", cursor1.getInt(cursor1
//                .getColumnIndex("sum("+Constants.TABLE_MONEY+")")) +"");

        String selectSql = "select * from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_TIME + ">=" + longTime;
        Cursor cursor = DbManner.SelectBySql(dbBase, selectSql, null);
        List<Person> list = DbManner.curserToList(cursor);
        personList.addAll(list);

        billAdapter = new BillAdapter(getActivity(), personList);
        listView.setAdapter(billAdapter);
        View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.list_view_top, null);
        outTv = inflate1.findViewById(R.id.out_tv);
        inTv = inflate1.findViewById(R.id.in_tv);

        tvUntilTime1 = inflate1.findViewById(R.id.tv_until_time1);
        listView.addHeaderView(inflate1);
        billAdapter.notifyDataSetChanged();
        initTOtal();
        if (personList.size() > 0) {
            listView.setVisibility(View.VISIBLE);
            ll_null.setVisibility(View.GONE);
        } else {
            listView.setVisibility(View.GONE);
            ll_null.setVisibility(View.VISIBLE);
        }
        if (longTime != 0) {
            tvUntilTime.setText(AppUtils.getCurrentTime("yyyy/MM/dd") + " - " + AppUtils.timeStamp2Date(longTime + "", "yyyy-MM-dd"));
            tvUntilTime1.setText(AppUtils.getCurrentTime("yyyy/MM/dd") + " - " + AppUtils.timeStamp2Date(longTime + "", "yyyy-MM-dd"));
        }
    }

    private void initTOtal() {

        Cursor cursor1 = getinsize(); //收入
        if (cursor1.getCount() != 0) {
            cursor1.moveToFirst();
            float total = cursor1.getFloat(cursor1
                    .getColumnIndex("sum(" + Constants.TABLE_MONEY + ")"));
            Log.e("TTTT", "total ===" + total);
            if (total <= 0) {
                outTv.setText("- -");
                inTvNull.setText("- -");
            } else {
                outTv.setText(total + "");
                inTvNull.setText(total + "");
            }
        } else {
            outTv.setText("- -");
            inTvNull.setText("- -");
        }
        Cursor getoutsize = getoutsize();
        if (getoutsize.getCount() != 0) {
            getoutsize.moveToFirst();
            float total = getoutsize.getFloat(getoutsize
                    .getColumnIndex("sum(" + Constants.TABLE_MONEY + ")"));
            Log.e("TTTT", "total =----=" + total);
            if (total <= 0) {
                inTv.setText("- -");
                outTvNull.setText("- -");
            } else {
                inTv.setText(total + "");
                outTvNull.setText(total + "");
            }
        } else {
            inTv.setText("- -");
            outTvNull.setText("- -");
        }
        Cursor gettotal = gettotal();
        if (gettotal.getCount() != 0) {
            gettotal.moveToFirst();
            float total = gettotal.getFloat(getoutsize
                    .getColumnIndex("sum(" + Constants.TABLE_MONEY + ")"));
            Log.e("TTTT", "total =-gettotal---=" + total);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbBase.close();
    }

    public Cursor getinsize() {
        String outMoney = "select sum(" + Constants.TABLE_MONEY + ") from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_REMARK + "=1 and " + Constants.TABLE_TIME + ">=" + longTime; //支付
        Cursor cursor = dbBase.rawQuery(outMoney, null);
        return cursor;
    }

    public Cursor getoutsize() {
        String outMoney = "select sum(" + Constants.TABLE_MONEY + ") from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_REMARK + "=2  and " + Constants.TABLE_TIME + ">=" + longTime; //支付
        Cursor cursor = dbBase.rawQuery(outMoney, null);
        return cursor;
    }

    public Cursor gettotal() {
        String outMoney = "select sum(" + Constants.TABLE_MONEY + ") from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_TIME + ">=" + longTime; //支付
        Cursor cursor = dbBase.rawQuery(outMoney, null);
        return cursor;
    }

    //跟新
    public void getData(long ttt, int isRemark) {
        String selectSql = null;
        if (isRemark == 0) {
            selectSql = "select * from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_TIME + ">=" + ttt;
        } else if (isRemark == 1 || isRemark == 2) { // 支付
            selectSql = "select * from " + Constants.DB_TABLE_NAME + " where " + Constants.TABLE_TIME + ">=" + ttt + " and " + Constants.TABLE_REMARK + "=" + isRemark;
        }

        Cursor cursor = DbManner.SelectBySql(dbBase, selectSql, null);
        if (cursor != null) {
            List<Person> list = DbManner.curserToList(cursor);
            if (null != personList && personList.size()>0) {
                personList.clear();
                personList.addAll(list);
                billAdapter.notifyDataSetChanged();
                listView.setVisibility(View.VISIBLE);
                ll_null.setVisibility(View.GONE);
                this.longTime = ttt;
                initTOtal();
                tvUntilTime.setText(AppUtils.getCurrentTime("yyyy/MM/dd") + " - " + AppUtils.timeStamp2Date(longTime + "", "yyyy-MM-dd"));
                tvUntilTime1.setText(AppUtils.getCurrentTime("yyyy/MM/dd") + " - " + AppUtils.timeStamp2Date(longTime + "", "yyyy-MM-dd"));
            } else {
                listView.setVisibility(View.GONE);
                ll_null.setVisibility(View.VISIBLE);
            }
        } else {
            listView.setVisibility(View.GONE);
            ll_null.setVisibility(View.VISIBLE);
        }

    }

}
