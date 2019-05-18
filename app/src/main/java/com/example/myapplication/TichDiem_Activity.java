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
    String fbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        BtnName = (Button) findViewById(R.id.button6) ;
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            BtnName.setText(fbName);
        }
        BtnLienhe = (ImageButton) findViewById(R.id.imageButton9) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, LienHe.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, DatHang_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnSudungvoucher = (Button) findViewById(R.id.button2) ;
        BtnSudungvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TichDiem_Activity.this, ListVoucher.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
    }
}
