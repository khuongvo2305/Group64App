package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Bill_n_pay extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_n_pay);
        String ID = getIntent().getStringExtra("ID");
        int billAmount = getIntent().getIntExtra("billAmount",0);
        String dateString = getIntent().getStringExtra("dateString");
        int value = getIntent().getIntExtra("value",0);
        int percentage = getIntent().getIntExtra("percentage",0);

        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(ID);
        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(billAmount);
        TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
        txtTongbill.setText(dateString);
        int giamgia,thanhtoan;
        giamgia = value + percentage*billAmount;
        thanhtoan = billAmount - giamgia;
        TextView txtTongbill3 = (TextView) findViewById(R.id.txtTongbill3);
        txtTongbill3.setText(giamgia);
        TextView txtTongbill5 = (TextView) findViewById(R.id.txtTongbill5);
        txtTongbill5.setText(thanhtoan);
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay.this, account_Activity.class);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay.this, MainActivity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay.this, Map_Activity.class);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay.this, ScanActivity.class);
                startActivity(intent);
            }
        });
    }
}
