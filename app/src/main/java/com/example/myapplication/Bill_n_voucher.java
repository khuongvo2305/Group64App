package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bill_n_voucher extends AppCompatActivity {
    private Button useVoucherBtn, xacnhanBtn;
    private DatabaseReference mdata;
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_pickvoucher);
        final String IDUser = getIntent().getStringExtra("IDUser");
        final String IDBill = getIntent().getStringExtra("IDBill");
        final String date = getIntent().getStringExtra("date");
        final String billAmount = getIntent().getStringExtra("billAmount");
        Toast.makeText(Bill_n_voucher.this,""+IDUser,Toast.LENGTH_LONG).show();
        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(IDBill);
        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(billAmount);
        TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
        txtNgayinbill.setText(date);
        TextView txtTongbill5 = (TextView) findViewById(R.id.txtTongbill5);
        txtTongbill5.setText(billAmount);
        useVoucherBtn = (Button) findViewById(R.id.useVoucherBtn);
        useVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPay = new Intent(Bill_n_voucher.this, ListVoucher.class);
                intentPay.putExtra("IDUser", IDUser);
                intentPay.putExtra("billAmount", billAmount);
                intentPay.putExtra("date", date);
                intentPay.putExtra("IDBill", IDBill);
                startActivity(intentPay);
            }
        });
        xacnhanBtn = (Button) findViewById(R.id.xacnhan) ;
        xacnhanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xacnhan_intent = new Intent(Bill_n_voucher.this, ScanActivity.class);
                TextView mahoadon = (TextView) findViewById(R.id.txtMabill);
                TextView ngay = (TextView) findViewById(R.id.txtNgayinbill);
                TextView tongcong = (TextView) findViewById(R.id.txtTongbill);
                TextView voucher = (TextView) findViewById(R.id.txtTongbill2);
                TextView giamgia = (TextView) findViewById(R.id.txtTongbill3);
                TextView thanhtoan = (TextView) findViewById(R.id.txtTongbill5);
                int point = Integer.parseInt(tongcong.getText().toString())%10000;
                String Point = String.valueOf(point);
                mdata = FirebaseDatabase.getInstance().getReference();
                mdata.child("unpaidbill").child("billid").setValue(mahoadon.getText().toString());
                mdata.child("unpaidbill").child("billamount").setValue(tongcong.getText().toString());
                mdata.child("unpaidbill").child("billtotal").setValue(thanhtoan.getText().toString());
                mdata.child("unpaidbill").child("customerid").setValue(IDUser);
                mdata.child("unpaidbill").child("date").setValue(ngay.getText().toString());
                mdata.child("unpaidbill").child("point").setValue(Point);
                mdata.child("unpaidbill").child("state").setValue("0");
                mdata.child("unpaidbill").child("voucherid").setValue("null");
                startActivity(xacnhan_intent);
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
