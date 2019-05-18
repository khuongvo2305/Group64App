package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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

import java.util.Objects;

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
    private Integer oldpoint;
    private Integer newpoint;
    private String name;
    private Integer voucherpoint;
    DatabaseReference mData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier_finalbill);
        //Firebase
        mData = FirebaseDatabase.getInstance().getReference();
        mData.addValueEventListener(new ValueEventListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Chack trạng thái thanh toán
                upbstate = Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("state").getValue()).toString();

                    upbcustomerid = Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("customerid").getValue()).toString();
                    upbtotal = Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("billtotal").getValue()).toString();
                    upbamount = Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("billamount").getValue()).toString();
                    upbid =  Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("billid").getValue()).toString();
                    upbdate = dataSnapshot.child("unpaidbill").child("date").toString();
                    upbpoint = Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("point").getValue()).toString();
                    upbvoucherid = Objects.requireNonNull(dataSnapshot.child("unpaidbill").child("voucherid").getValue()).toString();
                    oldpoint = Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("customer").child(upbcustomerid).child("point").getValue()).toString());
                    name = Objects.requireNonNull(dataSnapshot.child("customer").child(upbcustomerid).child("name").getValue()).toString();
                    TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
                    txtMabill.setText(upbid);
                    TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
                    txtTongbill.setText(upbamount);
                    TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
                    txtNgayinbill.setText(upbdate);
                    TextView txtVoucherID = (TextView) findViewById(R.id.txtTongbill2);
                    txtVoucherID.setText(upbvoucherid);
                    TextView txtMaKH = (TextView) findViewById(R.id.txtMaKH);
                    txtMaKH.setText(name);
                    TextView txtThanhToan = (TextView) findViewById(R.id.txtTongbill5);
                    txtThanhToan.setText(upbtotal);

                Button BtnDTT = (Button) findViewById(R.id.buttonDaThanhToan) ;

                BtnDTT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mData = FirebaseDatabase.getInstance().getReference();
                        mData.child("unpaidbill").child("state").setValue(1);

                        //TODO
                        //Tạo mới bill trong history
                        bill newbill = new bill(upbamount, upbid,upbtotal,upbcustomerid,upbdate,upbpoint,upbstate,upbvoucherid);
                        mData.child("customer").child(upbcustomerid).child("history").child(upbid).setValue(newbill);
                        Toast.makeText(CashierLastBillActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();

                        //Sửa điểm
                        newpoint = Integer.parseInt(upbpoint) + oldpoint;
                        mData.child("customer").child(upbcustomerid).child("point").setValue(newpoint.toString());

                        //Check rank và điểm tặng voucher
                        voucherpoint = newpoint / 50 - oldpoint / 50;
                        if (voucherpoint > 0) //Có thăng tiến
                        {
                            //rank
                            if (newpoint >= 400 && oldpoint < 400) //lên KimCuong
                            {
                                //check rank cũ
                                if (oldpoint < 50) //New
                                {
                                    voucher VoucherBac = new voucher("Giảm ngay 50.000 - Quà tặng lên hạng thành viên BẠC", "1", "0", "50000");
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherBac);
                                    voucher VoucherVang = new voucher("Giảm ngay 100.000 - Quà tặng lên hạng thành viên VÀNG", "1", "0", "100000");
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherVang);
                                    voucher VoucherKimCuong = new voucher("Giảm ngay 200.000 - Quà tặng lên hạng thành viên KIM CƯƠNG", "1", "0", "200000");
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherKimCuong);
                                    voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                    voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                    mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                    if (newpoint / 50 > 8)
                                    {
                                        for (int i = 0; i < newpoint / 50 - 8; i++)
                                        {
                                            voucher Voucher50kimcuong = new voucher("Giảm ngay 40% - Quà tặng 50 điểm hạng KIM CƯƠNG", "0.6", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50kimcuong);
                                        }
                                    }
                                }
                                else
                                {
                                    if (oldpoint < 200) //Bạc
                                    {
                                        voucher VoucherVang = new voucher("Giảm ngay 100.000 - Quà tặng lên hạng thành viên VÀNG", "1", "0", "100000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherVang);
                                        voucher VoucherKimCuong = new voucher("Giảm ngay 200.000 - Quà tặng lên hạng thành viên KIM CƯƠNG", "1", "0", "200000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherKimCuong);
                                        if (oldpoint < 100)
                                        {
                                            voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                        }
                                        if (oldpoint >= 100 && oldpoint < 150)
                                        {
                                            voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                        }
                                        voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                        if (newpoint / 50 > 8)
                                        {
                                            for (int i = 0; i < newpoint / 50 - 8; i++)
                                            {
                                                voucher Voucher50kimcuong = new voucher("Giảm ngay 40% - Quà tặng 50 điểm hạng KIM CƯƠNG", "0.6", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50kimcuong);
                                            }
                                        }

                                    }
                                    else //Vàng
                                    {
                                        voucher VoucherKimCuong = new voucher("Giảm ngay 200.000 - Quà tặng lên hạng thành viên KIM CƯƠNG", "1", "0", "200000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherKimCuong);
                                        if (oldpoint < 250)
                                        {
                                            voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                        }
                                        if (oldpoint >= 250 && oldpoint < 300)
                                        {
                                            voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                        }
                                        if (oldpoint >= 300 && oldpoint < 350)
                                        {
                                            voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                        }
                                        if (newpoint / 50 > 8)
                                        {
                                            for (int i = 0; i < newpoint / 50 - 8; i++)
                                            {
                                                voucher Voucher50kimcuong = new voucher("Giảm ngay 40% - Quà tặng 50 điểm hạng KIM CƯƠNG", "0.6", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50kimcuong);
                                            }
                                        }

                                    }
                                }
                                mData.child("customer").child(upbcustomerid).child("rank").setValue("KIM CƯƠNG");
                            }
                            else
                            {
                                if (newpoint >= 200 && oldpoint < 200) //lên vàng
                                {
                                    //check rank cũ
                                    if (oldpoint < 50) //new
                                    {
                                        voucher VoucherBac = new voucher("Giảm ngay 50.000 - Quà tặng lên hạng thành viên BẠC", "1", "0", "50000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherBac);
                                        voucher VoucherVang = new voucher("Giảm ngay 100.000 - Quà tặng lên hạng thành viên VÀNG", "1", "0", "100000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherVang);
                                        voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                        if (newpoint / 50 > 4)
                                        {
                                            for (int i = 0; i < newpoint / 50 - 4; i++)
                                            {
                                                voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                            }
                                        }
                                    }
                                    else  //bạc
                                    {
                                        voucher VoucherVang = new voucher("Giảm ngay 100.000 - Quà tặng lên hạng thành viên VÀNG", "1", "0", "100000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherVang);
                                        if (oldpoint < 100)
                                        {
                                            voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                        }
                                        if (oldpoint >= 100 && oldpoint < 150)
                                        {
                                            voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                            mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                        }
                                        if (newpoint / 50 > 4)
                                        {
                                            for (int i = 0; i < newpoint / 50 - 4; i++)
                                            {
                                                voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                            }
                                        }
                                    }
                                    mData.child("customer").child(upbcustomerid).child("rank").setValue("VÀNG");
                                }
                                else
                                {
                                    if (newpoint >= 50 && oldpoint < 50) //lên bạc
                                    {
                                        voucher VoucherBac = new voucher("Giảm ngay 50.000 - Quà tặng lên hạng thành viên BẠC", "1", "0", "50000");
                                        mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(VoucherBac);
                                        mData.child("customer").child(upbcustomerid).child("rank").setValue("BẠC");
                                        if (newpoint / 50 > 1)
                                        {
                                            for (int i = 0; i < newpoint / 50 - 1; i++)
                                            {
                                                voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                            }
                                        }
                                    }
                                    else //Không lên hạng
                                    {
                                        if (newpoint < 200) //đang bạc
                                        {
                                            for (int i = 0; i < voucherpoint; i++)
                                            {
                                                voucher Voucher50bac = new voucher("Giảm ngay 20% - Quà tặng 50 điểm hạng BẠC", "0.8", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50bac);
                                            }
                                        }
                                        if (newpoint < 400 && newpoint >= 200) //đang vàng
                                        {
                                            for (int i = 0; i < voucherpoint; i++)
                                            {
                                                voucher Voucher50vang = new voucher("Giảm ngay 30% - Quà tặng 50 điểm hạng VÀNG", "0.7", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50vang);
                                            }
                                        }
                                        if (newpoint > 400) //Đang kim cương
                                        {
                                            for (int i = 0; i < voucherpoint; i++)
                                            {
                                                voucher Voucher50kimcuong = new voucher("Giảm ngay 40% - Quà tặng 50 điểm hạng KIM CƯƠNG", "0.6", "0", "0");
                                                mData.child("customer").child(upbcustomerid).child("voucher").push().child("voucher").setValue(Voucher50kimcuong);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        //Xóa voucher đã dùng\
                        mData.child("customer").child(upbcustomerid).child("voucher").child(upbvoucherid).removeValue();

                        //Xóa unpaidbill
                        mData.child("unpaidbill").child("billamount").setValue("0");
                        mData.child("unpaidbill").child("billid").setValue("NULL");
                        mData.child("unpaidbill").child("billtotal").setValue("0");
                        mData.child("unpaidbill").child("customerid").setValue("NULL");
                        mData.child("unpaidbill").child("date").setValue("0");
                        mData.child("unpaidbill").child("point").setValue("0");
                        mData.child("unpaidbill").child("state").setValue("0");
                        mData.child("unpaidbill").child("voucherid").setValue("NULL");

                        Toast.makeText(getApplicationContext() , "hahaha", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CashierLastBillActivity.this, CashierNewBillActivity.class);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Thanh toán thất bại!", Toast.LENGTH_SHORT).show();
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
