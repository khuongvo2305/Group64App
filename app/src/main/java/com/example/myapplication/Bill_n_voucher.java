package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Bill_n_voucher extends AppCompatActivity {
    private Button useVoucherBtn;
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_pickvoucher);
        String ID = getIntent().getStringExtra("ID");
        int billAmount = getIntent().getIntExtra("billAmount",0);
        String dateString = getIntent().getStringExtra("dateString");

        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(ID);
        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(billAmount);
        TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
        txtTongbill.setText(dateString);
        useVoucherBtn = (Button) findViewById(R.id.useVoucherBtn);
        useVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voucher_intent = new Intent(Bill_n_voucher.this, VoucherActivity.class);
                startActivity(voucher_intent);
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_voucher.this, account_Activity.class);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_voucher.this, MainActivity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_voucher.this, Map_Activity.class);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_voucher.this, ScanActivity.class);
                startActivity(intent);
            }
        });
    }

}
