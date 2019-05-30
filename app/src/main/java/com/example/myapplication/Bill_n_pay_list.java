package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Bill_n_pay_list extends AppCompatActivity {
    private Button useVoucherBtn, xacnhanBtn;
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    String fbName,IDUser;
    ImageButton BtnIconStar;
    Button BtnRank,BtnPoint;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_n_pay);
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        Intent intent = getIntent();
        final String IDUser = getIntent().getStringExtra("IDUser");
        final String BillId = getIntent().getStringExtra("IDBill");
        final String IDVoucher = getIntent().getStringExtra("VoucherID");
        final String date = getIntent().getStringExtra("date");
        String Percen = getIntent().getStringExtra("Percen");
        final String BillAmount = getIntent().getStringExtra("billAmount");
        String Value = getIntent().getStringExtra("Value");
        String DetailVoucher = getIntent().getStringExtra("Detail");

        int giamgia,thanhtoan;
        float percentage = Float.parseFloat(Percen);
        float value = Float.parseFloat(Value);
        float billAmount = Float.parseFloat(BillAmount);
        giamgia = (int) (value + (1-percentage)*billAmount);
        if (giamgia >= billAmount) giamgia = (int) billAmount;
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
                Intent xacnhan_intent = new Intent(Bill_n_pay_list.this, ScanActivity.class);
                TextView mahoadon = (TextView) findViewById(R.id.txtMabill);
                TextView ngay = (TextView) findViewById(R.id.txtNgayinbill);
                TextView tongcong = (TextView) findViewById(R.id.txtTongbill);
                TextView voucher = (TextView) findViewById(R.id.txtTongbill2);
                TextView giamgia = (TextView) findViewById(R.id.txtTongbill3);
                TextView thanhtoan = (TextView) findViewById(R.id.txtTongbill5);
                Integer point = Integer.parseInt(thanhtoan.getText().toString())/10000;
                mdata = FirebaseDatabase.getInstance().getReference();
                String hash =mdata.child("customerOrder").push().getKey();
                String Point = String.valueOf(point);
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("billid").setValue(mahoadon.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("billamount").setValue(tongcong.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("billtotal").setValue(thanhtoan.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("customerid").setValue(IDUser);
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("date").setValue(ngay.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("point").setValue(Point);
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("state").setValue("0");
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("voucherid").setValue(IDVoucher);
                xacnhan_intent.putExtra("IDUser",IDUser);
                xacnhan_intent.putExtra("fbName",fbName);
                startActivity(xacnhan_intent);
            }
        });
        useVoucherBtn = (Button) findViewById(R.id.useVoucherBtn);
        useVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voucher_intent = new Intent(Bill_n_pay_list.this, ListVoucher_list.class);
                voucher_intent.putExtra("IDUser", IDUser);
                voucher_intent.putExtra("billAmount",BillAmount);
                voucher_intent.putExtra("date", date);
                voucher_intent.putExtra("IDBill", BillId);
                voucher_intent.putExtra("fbName",fbName);
                startActivity(voucher_intent);
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay_list.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay_list.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay_list.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_pay_list.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
    }

}
