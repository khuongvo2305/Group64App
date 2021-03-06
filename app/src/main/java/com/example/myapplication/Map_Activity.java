package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map_Activity extends AppCompatActivity {
    ImageButton BtnAccount, BtnHome, BtnOrder, BtnStore;
    String fbName,IDUser;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
                Intent intent = new Intent(Map_Activity.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, DatHang_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_Activity.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
    }
}
