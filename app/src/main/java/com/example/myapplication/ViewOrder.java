package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewOrder implements Parcelable {
    private String ten;
    private String giatien;
    private String soluong;
    private String tongtien;

    public ViewOrder(String ten, String giatien, String soluong, String tongtien) {
        this.ten = ten;
        this.giatien = giatien;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    protected ViewOrder(Parcel in) {
        ten = in.readString();
        giatien = in.readString();
        soluong = in.readString();
        tongtien = in.readString();
    }

    public static final Creator<ViewOrder> CREATOR = new Creator<ViewOrder>() {
        @Override
        public ViewOrder createFromParcel(Parcel in) {
            return new ViewOrder(in);
        }

        @Override
        public ViewOrder[] newArray(int size) {
            return new ViewOrder[size];
        }
    };

    public String getTen() {
        return ten;
    }

    public String getGiatien() {
        return giatien;
    }

    public String getSoluong() {
        return soluong;
    }

    public String getTongtien() {
        return tongtien;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ten);
        dest.writeString(giatien);
        dest.writeString(soluong);
        dest.writeString(tongtien);
    }
}
