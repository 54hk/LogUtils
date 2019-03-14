package com.page.android.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.page.android.Constants;

import com.page.android.R;
import com.page.android.utils.AppUtils;
import com.page.android.utils.CircleImageView;
import com.page.android.utils.GlideUtils;
import com.page.android.utils.SharedPreferencesUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private CoordinatorLayout right;
    private NavigationView left;
    private boolean isDrawer = false;
    private Context mContext;
    ImageView ivOpenClose;
    private DrawerLayout drawer;
    TextView tvLogin; //点击登录
    private TextView tvAbout; //关注
    private TextView loginName;
    private TextView loginTime;
    private ImageView ivOut;
    private LinearLayout llNoLogin, contentToolSearch; //未登录
    private RelativeLayout llYesLogin;//登录过的布局
    private TextView homeTvBank, homeTvMoney, homeCorTime;
    private CircleImageView circleImageView;
    private LinearLayout linearLayout;
    private TextView tvHomeTime;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constants.mContext = this;
        mContext = MainActivity.this;
        SharedPreferencesUtil.getLoginState(mContext);
        initDrawer();
        initView();
        isShow();
        initBanner();
    }


    private void isShow() {

        SharedPreferences isLogin = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
//        isLogin.edit().putBoolean("isLogin", false).commit();
        boolean isLoginBoolean = isLogin.getBoolean("isLogin", false);
        if (isLoginBoolean == false) {
//            if (Constants.USER_NAME.equals("")) { //未登录
                tvLogin.setVisibility(View.VISIBLE);
                llNoLogin.setVisibility(View.VISIBLE);
                llYesLogin.setVisibility(View.INVISIBLE);
                loginName.setVisibility(View.GONE);
                loginTime.setVisibility(View.GONE);
                ivOut.setVisibility(View.GONE);
                circleImageView.setImageResource(R.mipmap.head_default);
//            } else {

//            }
        }else{
            tvLogin.setVisibility(View.INVISIBLE);
            loginName.setVisibility(View.VISIBLE);
            loginName.setText(Constants.USER_NAME);
            loginTime.setVisibility(View.VISIBLE);
            llNoLogin.setVisibility(View.INVISIBLE);
            llYesLogin.setVisibility(View.VISIBLE);
            loginTime.setText("上次登录" + Constants.CURRENT_TIME.replace("/", "-"));
            ivOut.setVisibility(View.VISIBLE);
            homeTvMoney.setText("¥ " + Constants.USER_MONEY);
            homeTvBank.setText(AppUtils.getCardPassword(Constants.CARD_NUMBER));


            homeCorTime.setText(AppUtils.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
            GlideUtils.load(mContext, "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3202533782,366534917&fm=27&gp=0.jpg", circleImageView);
        }

    }


    private void initView() {
        //初始侧滑的宽度
        ViewGroup.LayoutParams layoutParams = left.getLayoutParams();
        layoutParams.width = AppUtils.getScreenWidth(this) / 3 * 2;
        left.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login: //登陆
                if (Constants.USER_NAME.equals("")) {
                    startActivityForResult(new Intent(mContext, RegisterActivity.class), 1);
                } else {
                    SharedPreferences isLogin = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
                    isLogin.edit().putBoolean("isLogin", true).commit();
                    drawer.closeDrawer(GravityCompat.START);
                }
                isShow();
                break;
            case R.id.tv_about: //关于
                startActivityForResult(new Intent(mContext, RegisterActivity.class), 1);
                break;
            case R.id.iv_out: //退出登录

                String LOGIN_UP_TIME = Constants.LOGIN_UP_TIME;
                SharedPreferences isLogin = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
                isLogin.edit().putBoolean("isLogin", false).commit();
//                SharedPreferencesUtil.removeLoginState(this);

                drawer.closeDrawer(GravityCompat.START);
                isShow();
                SharedPreferencesUtil.setParam(this, "LOGIN_UP_TIME_PRATIVE", AppUtils.date2TimeStamp(LOGIN_UP_TIME,"yyyy-MM-dd HH:mm:ss") + "");
//                startActivityForResult(new Intent(mContext, RegisterActivity.class), 1);
                break;
            case R.id.ll_yes_login: //账单
                startActivity(new Intent(mContext, BillDetailActivity.class));
                break;
            case R.id.ll_go_db: //数据库
                startActivity(new Intent(mContext, DbActivity.class));
                break;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //初始化侧滑
    private void initDrawer() {
        tvHomeTime = findViewById(R.id.tv_home_time);
        banner = findViewById(R.id.banner);
        AppUtils.getDate(tvHomeTime);
        ivOpenClose = findViewById(R.id.iv_open_close);
        contentToolSearch = findViewById(R.id.content_tool_search);
        contentToolSearch.setVisibility(View.VISIBLE);
        llNoLogin = findViewById(R.id.ll_no_login);
        llYesLogin = findViewById(R.id.ll_yes_login);
        llYesLogin.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        homeTvBank = findViewById(R.id.home_tv_bank);
        homeTvMoney = findViewById(R.id.home_tv_Money);
        homeCorTime = findViewById(R.id.home_corr_time);
        drawer = findViewById(R.id.drawer_layout);
        right = findViewById(R.id.right);
        left = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ivOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        tvLogin = headerView.findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);
        linearLayout = headerView.findViewById(R.id.ll_go_db);
        linearLayout.setOnClickListener(this);
        tvAbout = headerView.findViewById(R.id.tv_about);
        tvAbout.setOnClickListener(this);
        loginTime = headerView.findViewById(R.id.login_time);
        loginName = headerView.findViewById(R.id.login_name);
        ivOut = headerView.findViewById(R.id.iv_out);
        ivOut.setOnClickListener(this);
        circleImageView = headerView.findViewById(R.id.iv_head);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isDrawer) {
                    return left.dispatchTouchEvent(motionEvent);
                } else {
                    return false;
                }
            }
        });
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer = true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                right.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        isShow();
    }

    ArrayList<Integer> images = new ArrayList<>();

    //初始化轮播图
    private void initBanner() {

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        images.add(R.mipmap.b1);
        images.add(R.mipmap.b2);
        images.add(R.mipmap.b3);
        images.add(R.mipmap.b4);
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

}
