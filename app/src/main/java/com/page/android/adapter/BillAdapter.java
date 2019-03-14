package com.page.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.page.android.Bean.Person;
import com.page.android.Constants;
import com.page.android.R;
import com.page.android.utils.AppUtils;

import org.w3c.dom.Text;

import java.util.List;

public class BillAdapter extends BaseAdapter {
    private Context context;
    private List<Person> list;

    public BillAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bill_adapter, null);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.tvToFromMoney.setText(list.get(position).getMoney() + "");
        myViewHolder.tvYue.setText(list.get(position).getTableBlance() + "");
        if (list.get(position).getRemark() == 1) {
            myViewHolder.tvRemark.setText(list.get(position).getYao());
            myViewHolder.tvIn.setText("支出");
            myViewHolder.ivImg.setImageResource(R.mipmap.green);
        } else {
            myViewHolder.tvIn.setText("收入");
            myViewHolder.tvRemark.setText(list.get(position).getYao());
            myViewHolder.ivImg.setImageResource(R.mipmap.red);
        }
        myViewHolder.tvAdress.setText(list.get(position).getAddress());
        myViewHolder.tvTime1.setText(AppUtils.timeStamp2Date(list.get(position).getTime() + "", "yyyyMMdd HH:mm"));
        myViewHolder.tvHourse.setText(list.get(position).getOtherHouse());
        myViewHolder.tvTime2.setText(AppUtils.timeStamp2Date(list.get(position).getTime() + "", "yyyyMMdd"));
        myViewHolder.tvBottomTime.setText(AppUtils.timeStamp2Date(list.get(position).getTime() + "", "yyyy-MM-dd"));
        myViewHolder.tvDay.setText(AppUtils.getDayofWeek(list.get(position).getTime()) + "");

        if (!list.get(position).isShow) { //隐藏
            myViewHolder.tvTime1Title.setVisibility(View.GONE);
            myViewHolder.tvTime1.setVisibility(View.GONE);
            myViewHolder.tvTime2.setVisibility(View.GONE);
            myViewHolder.tvTime2Title.setVisibility(View.GONE);
            myViewHolder.tvHourseTitle.setVisibility(View.GONE);
            myViewHolder.tvHourse.setVisibility(View.GONE);
        } else {
            myViewHolder.tvTime1Title.setVisibility(View.VISIBLE);
            myViewHolder.tvTime1.setVisibility(View.VISIBLE);
            myViewHolder.tvTime2.setVisibility(View.VISIBLE);
            myViewHolder.tvTime2Title.setVisibility(View.VISIBLE);
            myViewHolder.tvHourseTitle.setVisibility(View.VISIBLE);
            myViewHolder.tvHourse.setVisibility(View.VISIBLE);
        }
        myViewHolder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.get(position).isShow) {
                    list.get(position).isShow = true;
                    myViewHolder.tvTime1Title.setVisibility(View.VISIBLE);
                    myViewHolder.tvTime1.setVisibility(View.VISIBLE);
                    myViewHolder.tvTime2.setVisibility(View.VISIBLE);
                    myViewHolder.tvTime2Title.setVisibility(View.VISIBLE);
                    myViewHolder.tvHourseTitle.setVisibility(View.VISIBLE);
                    myViewHolder.tvHourse.setVisibility(View.VISIBLE);
                    myViewHolder.ivShow.setImageResource(R.mipmap.gengduoicon);
                } else {

                    myViewHolder.ivShow.setImageResource(R.mipmap.gengduoicon1);
                    list.get(position).isShow = false;
                    myViewHolder.tvTime1Title.setVisibility(View.GONE);
                    myViewHolder.tvTime1.setVisibility(View.GONE);
                    myViewHolder.tvTime2.setVisibility(View.GONE);
                    myViewHolder.tvTime2Title.setVisibility(View.GONE);
                    myViewHolder.tvHourseTitle.setVisibility(View.GONE);
                    myViewHolder.tvHourse.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }

    public class MyViewHolder {
        private TextView tvToFromMoney;
        private TextView tvYue;
        private TextView tvRemark;
        private TextView tvAdress;
        private TextView tvTime1, tvTime2, tvHourse;
        private ImageView ivImg;
        private TextView tvTime1Title, tvTime2Title, tvHourseTitle;
        private TextView tvIn;
        private TextView tvBottomTime;
        private TextView tvDay;
        ImageView ivShow;

        public MyViewHolder(View view) {
            ivImg = view.findViewById(R.id.iv_img);
            tvDay = view.findViewById(R.id.tv_day);
            tvIn = view.findViewById(R.id.tv_in);
            tvTime1Title = view.findViewById(R.id.tv_time1_title);
            tvTime2Title = view.findViewById(R.id.tv_time2_title);
            tvHourseTitle = view.findViewById(R.id.tv_hourse_title);
            tvTime1 = view.findViewById(R.id.tv_time1);
            tvHourse = view.findViewById(R.id.tv_hourse);
            tvTime2 = view.findViewById(R.id.tv_time2);
            tvYue = view.findViewById(R.id.tv_yue);
            tvAdress = view.findViewById(R.id.tv_adress);
            tvBottomTime = view.findViewById(R.id.tv_bottom_time);
            tvRemark = view.findViewById(R.id.tv_remark);
            ivShow = view.findViewById(R.id.iv_show);
            tvToFromMoney = view.findViewById(R.id.tv_to_from_money);
        }
    }
}
