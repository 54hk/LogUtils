package com.page.android.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.page.android.Constants;
import com.page.android.R;
import com.page.android.utils.AppUtils;

public class BillDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMoney;
    private TextView tvTime;
    private TextView tvHourse;
    private TextView tvNumber;
    private TextView tvNumber1;
    private ImageView ivBack;
    private LinearLayout goBill;
    private TextView tvTitle;
    private TextView tvZhuanghu;
    private TextView tvLeibie;
    private TextView tvDian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        initFindIdtoDraw();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int width = tvNumber.getMeasuredWidth();
        tvZhuanghu.setWidth(width);
        tvLeibie.setWidth(width);
        tvDian.setWidth(width);
    }

    private void initFindIdtoDraw() {
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("账户详情");
        tvMoney = findViewById(R.id.tv_money);
        tvMoney.setText(Constants.USER_MONEY);
        tvTime = findViewById(R.id.tv_time);
        tvTime.setText(Constants.ACCOUNT_TIME);
        tvHourse = findViewById(R.id.tv_hourse);
        tvHourse.setText(Constants.USER_BANK);
        tvNumber = findViewById(R.id.tv_number);
        tvNumber1 = findViewById(R.id.tv_number_1);
        tvNumber.setText(AppUtils.getCardPassword(Constants.CARD_NUMBER) +"   ");
        tvNumber1.setText(AppUtils.getCardPassword(Constants.CARD_NUMBER));
        ivBack = findViewById(R.id.iv_back);
        tvLeibie = findViewById(R.id.tv_leibie);
        goBill = findViewById(R.id.go_bill);
        tvDian = findViewById(R.id.tv_dian);
        tvZhuanghu = findViewById(R.id.tv_zhuanghu);
        goBill.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_bill:
                startActivity(new Intent(this, BillActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
