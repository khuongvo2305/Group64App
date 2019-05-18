package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Thongtin_account extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    Button thaydoi;
    TextView hoten, ngaysinh, diachi, sodienthoai;
    String fbName;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_account);
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        mdata =FirebaseDatabase.getInstance().getReference();
        mdata.child("customer").child(ID).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hoten = (TextView) findViewById(R.id.textView33);
                hoten.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdata.child("customer").child(ID).child("birthday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ngaysinh = (TextView) findViewById(R.id.textView35);
                ngaysinh.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdata.child("customer").child(ID).child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sodienthoai = (TextView) findViewById(R.id.textView32);
                sodienthoai.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdata.child("customer").child(ID).child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                diachi = (TextView) findViewById(R.id.textView34);
                diachi.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        thaydoi = (Button) findViewById(R.id.button5) ;
        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, ThayDoiThongtin.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

        BtnAccount = (ImageButton) findViewById(R.id.BtnTaiKhoan) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, DatHang_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
    }
}
