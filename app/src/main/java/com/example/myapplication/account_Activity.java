package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class account_Activity extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnLienhe,BtnAvartar,BtnIconStar;
    Button BtnThongtin, BtnLichSugiaodich, BtnDieuKhoan,BtnGuiPhanHoi,BtnCatdat, BtnDangXuat,BtnName,BtnRank,BtnPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        BtnLienhe = (ImageButton) findViewById(R.id.imageButton9) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, LienHe.class);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, DatHang_Activity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, Map_Activity.class);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnThongtin = (Button) findViewById(R.id.button12) ;
        BtnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, Thongtin_account.class);
                startActivity(intent);
            }
        });
        BtnLichSugiaodich = (Button) findViewById(R.id.button13) ;
        BtnLichSugiaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, LichSuGiaoDich.class);
                startActivity(intent);
            }
        });
        BtnDieuKhoan = (Button) findViewById(R.id.button14) ;
        BtnDieuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, DieuKhoanSudung.class);
                startActivity(intent);
            }
        });
        BtnGuiPhanHoi = (Button) findViewById(R.id.button15) ;
        BtnGuiPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, GuiPhanHoi.class);
                startActivity(intent);
            }
        });
        BtnCatdat = (Button) findViewById(R.id.button16) ;
        BtnCatdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, CaiDat.class);
                startActivity(intent);
            }
        });
        BtnDangXuat = (Button) findViewById(R.id.button17) ;
        BtnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
