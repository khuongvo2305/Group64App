package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Bill_n_Voucher_list extends AppCompatActivity {
    private Button useVoucherBtn, xacnhanBtn,rank;
    private DatabaseReference mdata;
    private TextView scanResults;
    private TextView scanResults_ID;
    private TextView scanResults_Date;
    private TextView scanResults_BillAmount;
    static class RandomString {

        // function to generate a random string of length n
        static String getAlphaNumericString(int n) {

            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    ;

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                // generate a random number between
                        // 0 to AlphaNumericString variable length
                        int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }

            return sb.toString();
        }
    }
    String fbName, QRCode,IDUser;
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    Button BtnName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_pickvoucher);
        scanResults = (TextView) findViewById(R.id.scan_results);
        scanResults_ID = (TextView) findViewById(R.id.txtMabill);
        scanResults_Date = (TextView) findViewById(R.id.txtNgayinbill);
        scanResults_BillAmount = (TextView) findViewById(R.id.txtTongbill);
        BtnName = (Button) findViewById(R.id.button6) ;

        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        BtnName = (Button) findViewById(R.id.button6) ;
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            BtnName.setText(fbName);
        }
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("customer").child(IDUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rank = (Button) findViewById(R.id.button7);
                rank.setText("Thành viên "+dataSnapshot.child("rank").getValue().toString()+"-"+ dataSnapshot.child("point").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        TextView txtTongbill5 = (TextView) findViewById(R.id.txtTongbill5);
        final String IDrandom = RandomString.getAlphaNumericString(8);
        final String IDBill = IDrandom;

        String date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String dateString = date1;
        scanResults_ID.setText(IDBill);
        scanResults_Date.setText(date1);
        scanResults_BillAmount.setText(getIntent().getStringExtra("Tong"));
        final String IDUser = getIntent().getStringExtra("IDUser");
        final String date = date1;
        final String billAmount = (String) scanResults_BillAmount.getText();
        txtTongbill5.setText(billAmount);
//        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
//        txtMabill.setText(IDBill);
//        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
//        txtTongbill.setText(billAmount);
//        TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
//        txtNgayinbill.setText(date);
//        TextView txtTongbill5 = (TextView) findViewById(R.id.txtTongbill5);
//        txtTongbill5.setText(billAmount);
        useVoucherBtn = (Button) findViewById(R.id.useVoucherBtn);
        useVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPay = new Intent(Bill_n_Voucher_list.this, ListVoucher_list.class);
                intentPay.putExtra("IDUser", IDUser);
                intentPay.putExtra("billAmount", billAmount);
                intentPay.putExtra("date", date);
                intentPay.putExtra("IDBill", IDBill);
                //intentPay.putExtra("fbName",fbName);
                startActivity(intentPay);
            }
        });
        xacnhanBtn = (Button) findViewById(R.id.xacnhan) ;
        xacnhanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xacnhan_intent = new Intent(Bill_n_Voucher_list.this, ScanActivity.class);
                TextView mahoadon = (TextView) findViewById(R.id.txtMabill);
                TextView ngay = (TextView) findViewById(R.id.txtNgayinbill);
                TextView tongcong = (TextView) findViewById(R.id.txtTongbill);
                TextView voucher = (TextView) findViewById(R.id.txtTongbill2);
                TextView giamgia = (TextView) findViewById(R.id.txtTongbill3);
                TextView thanhtoan = (TextView) findViewById(R.id.txtTongbill5);
                int point = Integer.parseInt(tongcong.getText().toString())/10000;
                String Point = String.valueOf(point);
                mdata = FirebaseDatabase.getInstance().getReference();
                customerOrder test = new customerOrder(tongcong.getText().toString(),mahoadon.getText().toString(),thanhtoan.getText().toString(),IDUser,ngay.getText().toString(),Point,"0","null");
                String hash = mdata.child("customerOrder").push().getKey();

                mdata.child("customerOrder").child(hash).child("unpaidbill").setValue(test);
                /*
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("billid").setValue(mahoadon.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("billamount").setValue(tongcong.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("billtotal").setValue(thanhtoan.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("customerid").setValue(IDUser);
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("date").setValue(ngay.getText().toString());
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("point").setValue(Point);
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("state").setValue("0");
                mdata.child("customerOrder").child(hash).child("unpaidbill").child("voucherid").setValue("null");
                */
                xacnhan_intent.putExtra("IDUser",IDUser);
                xacnhan_intent.putExtra("fbName",fbName);
                startActivity(xacnhan_intent);
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_Voucher_list.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_Voucher_list.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_Voucher_list.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill_n_Voucher_list.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
    }

}
