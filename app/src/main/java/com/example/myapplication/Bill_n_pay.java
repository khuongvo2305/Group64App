package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Bill_n_pay extends AppCompatActivity {
    private Button useVoucherBtn, xacnhanBtn;
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_n_pay);
<<<<<<< HEAD
        Intent intent = getIntent();
        final String IDUser = getIntent().getStringExtra("IDUser");
        final String BillId = getIntent().getStringExtra("IDBill");
        final String IDVoucher = getIntent().getStringExtra("VoucherID");
        final String date = getIntent().getStringExtra("date");
        String Percen = getIntent().getStringExtra("Percen");
        final String BillAmount = getIntent().getStringExtra("billAmount");
        String Value = getIntent().getStringExtra("Value");
        String DetailVoucher = getIntent().getStringExtra("Detail");
=======
        String ID = getIntent().getStringExtra("ID");
        String billAmount = getIntent().getStringExtra("billAmount");
        String dateString = getIntent().getStringExtra("dateString");

        int billInt = Integer.parseInt(billAmount);
        int value = getIntent().getIntExtra("value",0);
        int percentage = getIntent().getIntExtra("percentage",0);
>>>>>>> a26d972f5f72ed063b7edbf55f44f1303656fc18

        int giamgia,thanhtoan;
<<<<<<< HEAD
        float percentage = Float.parseFloat(Percen);
        float value = Float.parseFloat(Value);
        float billAmount = Float.parseFloat(BillAmount);

        giamgia = (int) (value + percentage*billAmount);
        String GiamGiaview = String.valueOf(giamgia);

        thanhtoan = (int)(billAmount - giamgia);
        String Thanhtoanview =String.valueOf(thanhtoan);
        TextView valueView = (TextView) findViewById(R.id.txtMabill);
        valueView.setText(BillId);
        TextView GiamGiaView = (TextView) findViewById(R.id.txtTongbill3);
        GiamGiaView.setText(GiamGiaview);
        TextView ThanhToanView = (TextView) findViewById(R.id.txtTongbill5);
        ThanhToanView.setText(Thanhtoanview);
        TextView TongBillView = (TextView) findViewById(R.id.txtTongbill);
        TongBillView.setText(BillAmount);
        TextView NgayInBillView = (TextView) findViewById(R.id.txtNgayinbill);
        NgayInBillView.setText(date);
        TextView DetailVoucherView = (TextView) findViewById(R.id.txtTongbill2);
        DetailVoucherView.setText(DetailVoucher);
        xacnhanBtn = (Button) findViewById(R.id.xacnhan) ;
        xacnhanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xacnhan_intent = new Intent(Bill_n_pay.this, ScanActivity.class);
                TextView mahoadon = (TextView) findViewById(R.id.txtMabill);
                TextView ngay = (TextView) findViewById(R.id.txtNgayinbill);
                TextView tongcong = (TextView) findViewById(R.id.txtTongbill);
                TextView voucher = (TextView) findViewById(R.id.txtTongbill2);
                TextView giamgia = (TextView) findViewById(R.id.txtTongbill3);
                TextView thanhtoan = (TextView) findViewById(R.id.txtTongbill5);
                Float point = Float.parseFloat(tongcong.getText().toString())/10000;
                String Point = String.valueOf(point);
                mdata = FirebaseDatabase.getInstance().getReference();
                mdata.child("unpaidbill").child("billid").setValue(mahoadon.getText().toString());
                mdata.child("unpaidbill").child("billamount").setValue(tongcong.getText().toString());
                mdata.child("unpaidbill").child("billtotal").setValue(thanhtoan.getText().toString());
                mdata.child("unpaidbill").child("customerid").setValue(IDUser);
                mdata.child("unpaidbill").child("date").setValue(ngay.getText().toString());
                mdata.child("unpaidbill").child("point").setValue(Point);
                mdata.child("unpaidbill").child("state").setValue("1");
                mdata.child("unpaidbill").child("voucherid").setValue(IDVoucher);
                startActivity(xacnhan_intent);
            }
        });
        useVoucherBtn = (Button) findViewById(R.id.useVoucherBtn);
        useVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voucher_intent = new Intent(Bill_n_pay.this, ListVoucher.class);
                voucher_intent.putExtra("IDUser", IDUser);
                voucher_intent.putExtra("billAmount",BillAmount);
                voucher_intent.putExtra("date", date);
                voucher_intent.putExtra("IDBill", BillId);
                startActivity(voucher_intent);
            }
        });
=======
        giamgia = value + percentage*billInt;
        thanhtoan = billInt - giamgia;
        TextView txtTongbill3 = (TextView) findViewById(R.id.txtTongbill3);
        txtTongbill3.setText(giamgia);
        TextView txtTongbill5 = (TextView) findViewById(R.id.txtTongbill5);
        txtTongbill5.setText(thanhtoan);
>>>>>>> a26d972f5f72ed063b7edbf55f44f1303656fc18
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
