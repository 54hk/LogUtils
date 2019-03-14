package com.page.android.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.page.android.Constants;

public class Person  implements Parcelable {
    //    int _id = cursor.getInt(cursor.getColumnIndex(Constants.TABLE_ID));
//    int money = cursor.getInt(cursor.getColumnIndex(Constants.TABLE_MONEY));
//    int remark = cursor.getInt(cursor.getColumnIndex(Constants.TABLE_REMARK));
//    String address = cursor.getString(cursor.getColumnIndex(Constants.TABLE_ADDRESS));
//    long time = cursor.getLong(cursor.getColumnIndex(Constants.TABLE_TIME));
    private int _id;
    private float money;
    private int remark;
    private String address;
    private long time;
    private String otherHouse;
    private float tableBlance;
    private String yao;
    public boolean isShow = false;

    public Person(int _id, float money, int remark, String address, long time, String otherHouse, float tableBlance, String yao) {
        this._id = _id;
        this.money = money;
        this.remark = remark;
        this.tableBlance = tableBlance;
        this.address = address;
        this.otherHouse = otherHouse;
        this.time = time;
        this.yao = yao;
    }

    protected Person(Parcel in) {
        _id = in.readInt();
        money = in.readFloat();
        remark = in.readInt();
        address = in.readString();
        time = in.readLong();
        otherHouse = in.readString();
        tableBlance = in.readFloat();
        yao = in.readString();
        isShow = in.readByte() != 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getYao() {
        return yao;
    }

    public void setYao(String yao) {
        this.yao = yao;
    }

    public String getOtherHouse() {
        return otherHouse;
    }

    public void setOtherHouse(String otherHouse) {
        this.otherHouse = otherHouse;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float getMoney() {
        return money;
    }

    public float getTableBlance() {
        return tableBlance;
    }

    public void setTableBlance(float tableBlance) {
        this.tableBlance = tableBlance;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_id=" + _id +
                ", money=" + money +
                ", remark=" + remark +
                ", address='" + address + '\'' +
                ", time=" + time +
                ", otherHouse='" + otherHouse + '\'' +
                ", tableBlance=" + tableBlance +
                ", yao='" + yao + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeFloat(money);
        dest.writeInt(remark);
        dest.writeString(address);
        dest.writeLong(time);
        dest.writeString(otherHouse);
        dest.writeFloat(tableBlance);
        dest.writeString(yao);
        dest.writeByte((byte) (isShow ? 1 : 0));
    }
}
