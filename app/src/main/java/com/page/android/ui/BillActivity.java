package com.page.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TextView;

import com.page.android.Constants;
import com.page.android.R;
import com.page.android.adapter.MyPagerAdapter;
import com.page.android.utils.AppUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BillActivity extends AppCompatActivity
        implements View.OnClickListener {
    private CoordinatorLayout right;
    private Context mContext;
    private LinearLayout llMore, llTopMessage;
    private TextView tvTitle;
    private ImageView ivBack;
    private ViewPager viewPager;
    ArrayList<Fragment> fragments;
    private ArrayList<String> title;
    private MyPagerAdapter myPagerAdapter;
    private TextView tvNumber;
    private TextView tvRenMoney;
    private TextView tvMore;
    private TextView tvTime3;
    private TextView tvTime2;
    private TextView tvTime1;
    private LinearLayout llTime1;
    private LinearLayout llTime2;
    private LinearLayout llTime3;
    private BillFragment instance;
    private BillFragment instanceYue;
    private BillFragment instancedantian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        mContext = BillActivity.this;
        initDrawer();
        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        instancedantian = BillFragment.getInstance(AppUtils.getLongTime(0));
        fragments.add(instancedantian); //当天
        fragments.add(BillFragment.getInstance(AppUtils.getLongTime(6))); //7tian
        instanceYue = BillFragment.getInstance(AppUtils.getLongTime(29));
        fragments.add(instanceYue); //一个月
        this.instance = BillFragment.getInstance(0);
        fragments.add(this.instance);
        title = new ArrayList<>();
        title.add("当天");
        title.add("七天");
        title.add("一个月");
        title.add("更多筛选");

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments, title);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        onClickTime(3);
        viewPager.setCurrentItem(2);
    }

    //初始化侧滑
    private void initDrawer() {
        tvNumber = findViewById(R.id.tv_number);
        tvNumber.setText(AppUtils.getCardPassword(Constants.CARD_NUMBER));
        tvRenMoney = findViewById(R.id.tv_ren_money);
        tvRenMoney.setText("人民币 " + Constants.USER_MONEY);
        tvTime1 = findViewById(R.id.tv_time1);
        llTime1 = findViewById(R.id.ll_time1);
        llTime1.setOnClickListener(this);
        tvTime2 = findViewById(R.id.tv_time2);
        llTime2 = findViewById(R.id.ll_time2);
        llTime2.setOnClickListener(this);
        tvTime3 = findViewById(R.id.tv_time3);
        llTime3 = findViewById(R.id.ll_time3);
        llTime3.setOnClickListener(this);
        tvMore = findViewById(R.id.tv_more);

        viewPager = findViewById(R.id.view_pager);
        llMore = findViewById(R.id.ll_more);
        llMore.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        llTopMessage = findViewById(R.id.ll_top_message);
        tvTitle.setText("账户明细");
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        right = findViewById(R.id.right);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    onClickTime(1);
                } else if (i == 1) {
                    onClickTime(2);
                } else if (i == 2) {
                    onClickTime(3);
                } else if (i == 3) {
                    onClickTime(4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_time1:
                onClickTime(1);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_time2:
                onClickTime(2);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_time3:
                onClickTime(3);
                viewPager.setCurrentItem(2);
                break;
            case R.id.ll_more:

                startActivityForResult(new Intent(mContext, WindowActivity.class), 200);
                overridePendingTransition(R.anim.slide_in_from_right, 0);
                break;

        }
    }

    public void onClickTime(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tvTime1.setBackground(null);
            tvTime1.setTextColor(getResources().getColor(R.color.color_444444));
            tvTime2.setBackground(null);
            tvTime2.setTextColor(getResources().getColor(R.color.color_444444));
            tvTime3.setBackground(null);
            tvTime3.setTextColor(getResources().getColor(R.color.color_444444));
            tvMore.setBackground(null);
            tvMore.setTextColor(getResources().getColor(R.color.color_444444));
        }
        if (position == 1) {
            tvTime1.setTextColor(getResources().getColor(R.color.color_08b7f3));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvTime1.setBackground(getResources().getDrawable(R.drawable.tab_bottom));
            }
        }

        if (position == 2) {
            tvTime2.setTextColor(getResources().getColor(R.color.color_08b7f3));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvTime2.setBackground(getResources().getDrawable(R.drawable.tab_bottom));
            }
        }
        if (position == 3) {
            tvTime3.setTextColor(getResources().getColor(R.color.color_08b7f3));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvTime3.setBackground(getResources().getDrawable(R.drawable.tab_bottom));
            }
        }
        if (position == 4) {
            tvMore.setTextColor(getResources().getColor(R.color.color_08b7f3));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvMore.setBackground(getResources().getDrawable(R.drawable.tab_bottom));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 1) {

            if (data != null) {
                long resultTime = data.getLongExtra("resultTime", 0);
                int isRemark = data.getIntExtra("isRemark", 0);
                String dataStr = data.getStringExtra("dataStr");
                if (dataStr.equals("dangtian")) { //当天
                    viewPager.setCurrentItem(0);
                    onClickTime(1);
                    instancedantian.getData(resultTime, isRemark);
                } else if (dataStr.equals("yiyue")) {  //yiyue
                    viewPager.setCurrentItem(2);
                    onClickTime(3);
                    instanceYue.getData(resultTime, isRemark);
                } else {
                    viewPager.setCurrentItem(3);
                    onClickTime(4);
                    instance.getData(resultTime, isRemark);
                }

            }
        }
    }
}
