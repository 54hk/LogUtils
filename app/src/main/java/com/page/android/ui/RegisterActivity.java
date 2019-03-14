package com.page.android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.page.android.Constants;
import com.page.android.R;
import com.page.android.utils.AppUtils;
import com.page.android.utils.DateTimePicker;
import com.page.android.utils.SharedPreferencesUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llName, llMoney, llTime, llBank, llAccount;
    EditText etName, etMoney, etBank;
    Button submitBut, reset;
    TextView tvCurrentTime, tvAccountTime, tvLoginUpTime, etNumber;
    private String loginTime;

    DateTimePicker dateTimePicker = new DateTimePicker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initFindID();
        initView();
    }

    private void initView() {
        tvCurrentTime.setText(AppUtils.getCurrentTime("yyyy/MM/dd HH:mm:ss"));
        tvAccountTime.setText(AppUtils.getCurrentTime("yyyy/MM/dd"));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset:
                SharedPreferencesUtil.removeLoginState(this);
                break;
            case R.id.submit_but: //提交资料
                //用户信息
                if (!etName.getText().toString().equals("")) {
                    Constants.USER_NAME = etName.getText().toString();
                } else {
                    toast("用户名不能为null");
                    return;
                }

                if (!etMoney.getText().toString().equals("")) {
                    Constants.USER_MONEY = etMoney.getText().toString();
                } else {
                    toast("金额不能为null");
                    return;
                }

                if (!etBank.getText().toString().equals("")) {
                    Constants.USER_BANK = etBank.getText().toString();
                } else {
                    toast("开户网点不能为null");
                    return;
                }

                if (!etNumber.getText().toString().equals("")) {
                    Constants.CARD_NUMBER = etNumber.getText().toString();
                } else {
                    toast("卡号不能为null");
                    return;
                }
                if (!tvAccountTime.getText().toString().equals("选择开户时间")) {

                } else {
                    toast("选择开户时间");
                    return;
                }
                Constants.CURRENT_TIME = tvCurrentTime.getText().toString(); //当前时间
                Constants.LOGIN_UP_TIME = tvLoginUpTime.getText().toString();//上次登录时间
                Constants.ACCOUNT_TIME = tvAccountTime.getText().toString();//开户时间

                Constants.LOGIN_UP_TIME_PRATIVE = System.currentTimeMillis() + "";
                SharedPreferencesUtil.setParam(this, "LOGIN_UP_TIME_PRATIVE", System.currentTimeMillis() + "");
                SharedPreferencesUtil.setLoginState(this);
                SharedPreferences isLogin = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
                isLogin.edit().putBoolean("isLogin", true).commit();
                finish();
                break;
            case R.id.ll_account: //开户日期
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dateTimePicker.init(this, tvAccountTime, false);
                }
                break;
        }
    }

    private void initFindID() {
        llName = findViewById(R.id.ll_name);
        llBank = findViewById(R.id.ll_bank);
        llAccount = findViewById(R.id.ll_account);

        llAccount.setOnClickListener(this);
        llMoney = findViewById(R.id.ll_money);
        llTime = findViewById(R.id.ll_time);
        etName = findViewById(R.id.et_name);
        etMoney = findViewById(R.id.et_money);
        etBank = findViewById(R.id.et_bank);
        submitBut = findViewById(R.id.submit_but);
        submitBut.setOnClickListener(this);
        tvCurrentTime = findViewById(R.id.tv_current_time);
        tvAccountTime = findViewById(R.id.tv_account_time);
        tvLoginUpTime = findViewById(R.id.tv_login_up_time);
        if (null != SharedPreferencesUtil.getParam(this, "LOGIN_UP_TIME_PRATIVE", "") &&
                !SharedPreferencesUtil.getParam(this, "LOGIN_UP_TIME_PRATIVE", "").equals("")) {
            tvLoginUpTime.setText(AppUtils.timeStamp2Date(SharedPreferencesUtil.getParam(this, "LOGIN_UP_TIME_PRATIVE", "") + "", "yyyy-MM-dd HH:mm:ss"));
        }
        etNumber = findViewById(R.id.et_number);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
