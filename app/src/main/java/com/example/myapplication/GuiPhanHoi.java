package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GuiPhanHoi extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount;
    String fbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_phan_hoi);
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        BtnAccount = (ImageButton) findViewById(R.id.BtnTaiKhoan) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuiPhanHoi.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuiPhanHoi.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuiPhanHoi.this, DatHang_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuiPhanHoi.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuiPhanHoi.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
    }
}
