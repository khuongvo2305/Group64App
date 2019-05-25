package com.example.myapplication;
import com.google.firebase.database.IgnoreExtraProperties;

public class customerOrder {
    public String billamout;
    public String billid;
    public String billtotal;
    public String customerid;
    public String date;
    public String point;
    public String state;
    public String voucherid;

    public customerOrder() {
    }

    public customerOrder(String billamout, String billid, String billtotal, String customerid, String date, String point, String state, String voucherid) {
        this.billamout = billamout;
        this.billid = billid;
        this.billtotal = billtotal;
        this.customerid = customerid;
        this.date = date;
        this.point = point;
        this.state = state;
        this.voucherid = voucherid;
    }
}