package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class TichDiem_Activity extends AppCompatActivity {
    ImageButton BtnAccount, BtnOrder, BtnMap, BtnHome,BtnLienhe,BtnAvartar,BtnIconStar;
    Button BtnSudungvoucher,BtnName,BtnRank,BtnPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        BtnLienhe = (ImageButton) findViewById(R.id.imageButton9) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, LienHe.class);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, account_Activity.class);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, DatHang_Activity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, Map_Activity.class);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        BtnSudungvoucher = (Button) findViewById(R.id.button2) ;
        BtnSudungvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, ListVoucher.class);
                startActivity(intent);
            }
        });
    }
}
