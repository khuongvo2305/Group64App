package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class account_Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String ID,fbName;
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnLienhe,BtnAvartar,BtnIconStar;
    Button BtnThongtin, BtnLichSugiaodich, BtnDieuKhoan,BtnGuiPhanHoi,BtnCatdat, BtnDangXuat,BtnName,BtnRank,BtnPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        mAuth = FirebaseAuth.getInstance();
        if (getIntent().getStringExtra("IDUser") != null)
        {
            ID = getIntent().getStringExtra("IDUser");
        }
        BtnName = (Button) findViewById(R.id.button6) ;
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            BtnName.setText(fbName);
        }
        BtnLienhe = (ImageButton) findViewById(R.id.imageButton9) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, LienHe.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, TichDiem_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, DatHang_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnThongtin = (Button) findViewById(R.id.button12) ;
        BtnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, Thongtin_account.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        BtnLichSugiaodich = (Button) findViewById(R.id.button13) ;
        BtnLichSugiaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, LichSuGiaoDich.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        BtnDieuKhoan = (Button) findViewById(R.id.button14) ;
        BtnDieuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, DieuKhoanSudung.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        BtnGuiPhanHoi = (Button) findViewById(R.id.button15) ;
        BtnGuiPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, GuiPhanHoi.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnCatdat = (Button) findViewById(R.id.button16) ;
        BtnCatdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_Activity.this, CaiDat.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnDangXuat = (Button) findViewById(R.id.button17) ;
        BtnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                updateUI();
            }
        });
    }
    private void updateUI(){
        Toast.makeText( account_Activity.this,"You're logged out",Toast.LENGTH_LONG).show();
        Intent accountIntent = new Intent(account_Activity.this, LoginActivity.class);
        startActivity(accountIntent);
        finish();
    }
}
