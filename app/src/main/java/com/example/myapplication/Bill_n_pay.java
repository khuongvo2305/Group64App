package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Bill_n_pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_n_pay);
        String ID = getIntent().getStringExtra("ID");
        int billAmount = getIntent().getIntExtra("billAmount",0);
        int value = getIntent().getIntExtra("value",0);
        int percentage = getIntent().getIntExtra("percentage",0);
        String ID_vc = getIntent().getStringExtra("ID_vc");
        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(ID);
        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(billAmount);
        TextView txtTongbill2 = (TextView) findViewById(R.id.txtTongbill2);
        txtTongbill2.setText(ID_vc);
        int giamgia,thanhtoan;
        if(value != 0) giamgia = value;
        else giamgia = percentage*billAmount;
        thanhtoan = billAmount - giamgia;
        TextView txtTongbill3 = (TextView) findViewById(R.id.txtTongbill3);
        txtTongbill3.setText(giamgia);
        TextView txtTongbill5 = (TextView) findViewById(R.id.txtTongbill5);
        txtTongbill5.setText(thanhtoan);

    }
}
