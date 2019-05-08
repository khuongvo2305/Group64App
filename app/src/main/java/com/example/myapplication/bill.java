package com.example.myapplication;

public class bill {
    public Integer billamount;
    public String billid;
    public Integer billtotal;
    public String customerid;
    public String date;
    public Integer point;
    public Integer state;
    public String voucherid;

    public bill() {
    }

    public bill(Integer billamount, String billid, Integer billtotal, String customerid, String date, Integer point, Integer state, String voucherid) {
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

