package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class DatHang_Activity extends AppCompatActivity {
    ImageButton BtnAccount, BtnHome, BtnMap, BtnStore;
    Button BtnName;
    String fbName,IDUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
    }
}
