package com.page.android.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.page.android.R;
import com.page.android.utils.AppUtils;

public class WindowActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rlOne, rlTwo, rlThree, rl_two_one, rl_two_two, rl_two_three;
    ImageView tvOne, tvTwo, tvThree, tv_two_one, tv_two_two, tv_two_three;
    private TextView reset;
    private TextView ok;
    private ImageView cha;

    private long resultTime = -1; //天数
    private int isRemark = 0; //0.全部 1.支付  2.收入
    private String dataStr = "";//当天  一月   6yue
    private TextView tvv_two_three;
    private TextView tvv_two_two;
    private TextView tvv_two_one;
    private TextView tvSix;
    private TextView tvYue;
    private TextView tvDangtian;
    private TextView tvSelectTime;//选择显示的日期

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        //进入退出效果 注意这里 创建的效果对象是 Slide()
        rlOne = findViewById(R.id.rl_one);
        rlOne.setOnClickListener(this);
        rlTwo = findViewById(R.id.rl_two);
        rlTwo.setOnClickListener(this);
        rlThree = findViewById(R.id.rl_three);
        rlThree.setOnClickListener(this);
        tvOne = findViewById(R.id.iv_one);
        tvTwo = findViewById(R.id.tv_two);
        tvv_two_three = findViewById(R.id.tvv_two_three);
        tvv_two_two = findViewById(R.id.tvv_two_two);
        tvv_two_one = findViewById(R.id.tvv_two_one);
        tvThree = findViewById(R.id.iv_three);
        rl_two_one = findViewById(R.id.rl_two_one);
        rl_two_one.setOnClickListener(this);
        rl_two_two = findViewById(R.id.rl_two_two);
        rl_two_two.setOnClickListener(this);
        rl_two_three = findViewById(R.id.rl_two_three);
        rl_two_three.setOnClickListener(this);
        tv_two_one = findViewById(R.id.tv_two_one);
        tvSelectTime = findViewById(R.id.tv_select_time);
        tv_two_two = findViewById(R.id.tv_two_two);
        tv_two_three = findViewById(R.id.tv_two_three);
        tvSix = findViewById(R.id.tv_six);
        tvYue = findViewById(R.id.tv_yiyue);
        tvDangtian = findViewById(R.id.tv_dangtian);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);
        ok = findViewById(R.id.ok);
        ok.setOnClickListener(this);
        cha = findViewById(R.id.cha);
        cha.setOnClickListener(this);
        resultTime = AppUtils.getLongTime(29);
        tvSelectTime.setText("(" + AppUtils.nowBeforeMuchDays(29) + " - "
                + AppUtils.nowBeforeMuchDays(0) + ")");
        dataStr = "yiyue";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_one: //当天
                tvOne.setVisibility(View.VISIBLE);
                tvTwo.setVisibility(View.INVISIBLE);
                tvThree.setVisibility(View.INVISIBLE);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rlOne.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rlThree.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rlTwo.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }


                dataStr = "dangtian";
                resultTime = AppUtils.getLongTime(0);
                tvSix.setTextColor(getResources().getColor(R.color.color_666666));
                tvYue.setTextColor(getResources().getColor(R.color.color_666666));
                tvDangtian.setTextColor(getResources().getColor(R.color.color_08b7f3));

                tvSelectTime.setText("(" + AppUtils.timeStamp2Date(AppUtils.getLongTime(0) + "", "yyyy/MM/dd")+" - "+AppUtils.timeStamp2Date(AppUtils.getLongTime(0) + "", "yyyy/MM/dd") + ")");
                break;
            case R.id.rl_two://一个月
                tvOne.setVisibility(View.INVISIBLE);
                tvTwo.setVisibility(View.VISIBLE);
                tvThree.setVisibility(View.INVISIBLE);
                resultTime = AppUtils.getLongTime(29);
                dataStr = "yiyue";

                tvSelectTime.setText("(" + AppUtils.nowBeforeMuchDays(29) + " - "
                        + AppUtils.nowBeforeMuchDays(0) + ")");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rlTwo.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rlThree.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rlOne.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }
                tvDangtian.setTextColor(getResources().getColor(R.color.color_666666));
                tvSix.setTextColor(getResources().getColor(R.color.color_666666));
                tvYue.setTextColor(getResources().getColor(R.color.color_08b7f3));
                break;
            case R.id.rl_three: //6个月
                tvOne.setVisibility(View.INVISIBLE);
                tvTwo.setVisibility(View.INVISIBLE);
                tvThree.setVisibility(View.VISIBLE);
                tvDangtian.setTextColor(getResources().getColor(R.color.color_666666));
                tvYue.setTextColor(getResources().getColor(R.color.color_666666));
                tvSix.setTextColor(getResources().getColor(R.color.color_08b7f3));
                resultTime = AppUtils.getLongTime(29 * 6);
                dataStr = "six";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rlThree.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rlTwo.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rlOne.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }

                tvSelectTime.setText("(" + AppUtils.nowBeforeMuchDays(29 * 6) + " - "
                        + AppUtils.nowBeforeMuchDays(0) + ")");
                break;

            case R.id.rl_two_one: //全部
                tv_two_one.setVisibility(View.VISIBLE);
                tv_two_two.setVisibility(View.INVISIBLE);
                tv_two_three.setVisibility(View.INVISIBLE);
                tvv_two_three.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_two.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_one.setTextColor(getResources().getColor(R.color.color_08b7f3));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rl_two_one.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rl_two_two.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rl_two_three.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }
                break;
            case R.id.rl_two_two: //收入
                tv_two_one.setVisibility(View.INVISIBLE);
                tv_two_two.setVisibility(View.VISIBLE);
                tv_two_three.setVisibility(View.INVISIBLE);

                tvv_two_three.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_two.setTextColor(getResources().getColor(R.color.color_08b7f3));
                tvv_two_one.setTextColor(getResources().getColor(R.color.color_666666));
                isRemark = 2;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rl_two_two.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rl_two_one.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rl_two_three.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }
                break;
            case R.id.rl_two_three: //支出
                tv_two_one.setVisibility(View.INVISIBLE);
                tv_two_two.setVisibility(View.INVISIBLE);
                tv_two_three.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rl_two_three.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rl_two_one.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rl_two_two.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }
                tvv_two_three.setTextColor(getResources().getColor(R.color.color_08b7f3));
                tvv_two_two.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_one.setTextColor(getResources().getColor(R.color.color_666666));
                isRemark = 1;
                break;
            case R.id.reset: //重置
                tvOne.setVisibility(View.INVISIBLE);
                tvTwo.setVisibility(View.VISIBLE);
                tvThree.setVisibility(View.INVISIBLE);
                tv_two_one.setVisibility(View.VISIBLE);
                tv_two_two.setVisibility(View.INVISIBLE);
                tv_two_three.setVisibility(View.INVISIBLE);
                tvDangtian.setTextColor(getResources().getColor(R.color.color_666666));
                tvYue.setTextColor(getResources().getColor(R.color.color_08b7f3));
                tvSix.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_three.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_two.setTextColor(getResources().getColor(R.color.color_666666));
                tvv_two_one.setTextColor(getResources().getColor(R.color.color_08b7f3));
                resultTime = AppUtils.getLongTime(29);
                isRemark = 0;
                dataStr = "yiyue";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rlTwo.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rlThree.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rlOne.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rl_two_one.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_c_shape));
                    rl_two_two.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                    rl_two_three.setBackground(getResources().getDrawable(R.drawable.head_circle_stroke_dddd_shape));
                }


                tvSelectTime.setText("(" + AppUtils.nowBeforeMuchDays(29) + " - "
                        + AppUtils.nowBeforeMuchDays(0) + ")");
                break;
            case R.id.ok: //queding
                Intent bundle = new Intent();
                bundle.putExtra("resultTime", resultTime);
                bundle.putExtra("isRemark", isRemark);
                bundle.putExtra("dataStr", dataStr);
                setResult(1, bundle);
                finish();
                break;
            case R.id.cha:
                finish();
                break;
        }
    }
}
