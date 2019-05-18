package com.example.myapplication;

public class bill {
    public String billamount;
    public String billid;
    public String billtotal;
    public String customerid;
    public String date;
    public String point;
    public String state;
    public String voucherid;

    public bill(String billamount, String billid, String billtotal, String customerid, String date, String point, String state, String voucherid) {
        this.billamount = billamount;
        this.billid = billid;
        this.billtotal = billtotal;
        this.customerid = customerid;
        this.date = date;
        this.point = point;
        this.state = state;
        this.voucherid = voucherid;
    }
}

