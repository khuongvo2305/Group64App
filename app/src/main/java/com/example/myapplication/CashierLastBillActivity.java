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

public class CashierLastBillActivity extends AppCompatActivity {
    private static final int MAX_LENGTH = 8;
    ImageButton BtnAccount, BtnHome, BtnOrder, BtnMap;
    private String json;
    //Biến sử lý unpaid-bill
    private String upbstate;
    private String upbcustomerid;
    private String upbtotal;
    private String upbamount;
    private String upbid;
    private String upbdate;
    private String upbpoint;
    private String upbvoucherid;
    private String upbdiscount;
    private String oldpoint;
    private String newpoint;
    DatabaseReference mData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier_finalbill);
        //Firebase
        mData = FirebaseDatabase.getInstance().getReference();
//        mData.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //Chack trạng thái thanh toán
//                upbstate =dataSnapshot.child("unpaidbill").child("state").getValue().toString();
//
//                    upbcustomerid = dataSnapshot.child("unpaidbill").child("customerid").getValue().toString();
//                    upbtotal = dataSnapshot.child("unpaidbill").child("billtotal").getValue().toString();
//                    upbamount = dataSnapshot.child("unpaidbill").child("billamount").getValue().toString();
//                    upbid =  dataSnapshot.child("unpaidbill").child("billid").getValue().toString();
//                    upbdate = dataSnapshot.child("unpaidbill").child("date").toString();
//                    upbpoint = dataSnapshot.child("unpaidbill").child("point").getValue().toString();
//                    upbvoucherid = dataSnapshot.child("unpaidbill").child("voucherid").getValue().toString();
//                    upbdiscount = dataSnapshot.child("unpaidbill").child("billdiscount").getValue().toString();
//                    TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
//                    txtMabill.setText(upbid);
//                    TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
//                    txtTongbill.setText(upbamount);
//                    TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
//                    txtNgayinbill.setText(upbdate);
//                    TextView txtVoucherID = (TextView) findViewById(R.id.txtTongbill2);
//                    txtVoucherID.setText(upbvoucherid);
//                    TextView txtMaKH = (TextView) findViewById(R.id.txtMaKH);
//                    txtMaKH.setText(upbcustomerid);
//                    TextView txtThanhToan = (TextView) findViewById(R.id.txtTongbill5);
//                    txtThanhToan.setText(upbtotal);
//
//                //}
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Thanh toán thất bại!", Toast.LENGTH_SHORT).show();
//            }
//        });
        upbstate = mData.child("unpaidbill").child("state").toString();
        upbcustomerid = mData.child("unpaidbill").child("customerid").toString();
        upbtotal = mData.child("unpaidbill").child("billtotal").toString();
        upbamount = mData.child("unpaidbill").child("billamount").toString();
        upbid =  mData.child("unpaidbill").child("billid").toString();
        upbdate = mData.child("unpaidbill").child("date").toString();
        upbpoint = mData.child("unpaidbill").child("point").toString();
        upbvoucherid = mData.child("unpaidbill").child("voucherid").toString();
        upbdiscount = mData.child("unpaidbill").child("billdiscount").toString();
        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(upbid);
        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(upbamount);
        TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
        txtNgayinbill.setText(upbdate);
        TextView txtVoucherID = (TextView) findViewById(R.id.txtTongbill2);
        txtVoucherID.setText(upbvoucherid);
        TextView txtMaKH = (TextView) findViewById(R.id.txtMaKH);
        txtMaKH.setText(upbcustomerid);
        TextView txtThanhToan = (TextView) findViewById(R.id.txtTongbill5);
        txtThanhToan.setText(upbtotal);


       Button BtnDTT = (Button) findViewById(R.id.buttonDaThanhToan) ;

        BtnDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData = FirebaseDatabase.getInstance().getReference();
                mData.child("unpaidbill").child("state").setValue(1);
                Toast.makeText(getApplicationContext() , "Thanh toán thành công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CashierLastBillActivity.this, CashierNewBillActivity.class);
                startActivity(intent);

            }
        });


        Button BtnHuy = (Button) findViewById(R.id.buttonHuyDonHang);
        BtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CashierLastBillActivity.this, CashierNewBillActivity.class);
                startActivity(intent);
            }
        });

//
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashierLastBillActivity.this, account_Activity.class);
                startActivity(intent);
            }
        });

        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashierLastBillActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashierLastBillActivity.this, DatHang_Activity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashierLastBillActivity.this, Map_Activity.class);
                startActivity(intent);
            }
        });

    }
}
