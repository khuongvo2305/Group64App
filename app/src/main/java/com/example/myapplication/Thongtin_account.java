package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Thongtin_account extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_account);
        BtnAccount = (ImageButton) findViewById(R.id.BtnTaiKhoan) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, account_Activity.class);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, MainActivity.class);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, DatHang_Activity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, Map_Activity.class);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtin_account.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
    }
}
