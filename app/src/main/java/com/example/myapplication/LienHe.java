package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;

public class LienHe extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    String fbName,IDUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            }
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        BtnAccount = (ImageButton) findViewById(R.id.BtnTaiKhoan) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LienHe.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LienHe.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LienHe.this, DatHang_Activity.class);
                ArrayList<ViewOrder> OrderArrayList = new ArrayList<>();
                intent.putParcelableArrayListExtra("order", (ArrayList<? extends Parcelable>) OrderArrayList);
                intent.putExtra("Tong", "0");
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LienHe.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LienHe.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
    }
}
