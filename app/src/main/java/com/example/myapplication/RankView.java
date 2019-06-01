package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RankView extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore,BtnAccount,BtnScan;
    private DatabaseReference mdata;
    Button BtnName,rank;
    TextView point,rankview,pointToNextLV;
    String IDUser,fbName;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
//            BtnName.setText(fbName);
        }
        mAuth = FirebaseAuth.getInstance();
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("customer").child(IDUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pointS =  dataSnapshot.child("point").getValue().toString();
                int pointInt = Integer.parseInt(pointS);
                int point;
               if(pointInt <= 50) {
                   setContentView(R.layout.activity_ranknew_view);
                   point = 50 - pointInt;
                   pointToNextLV = (TextView) findViewById(R.id.pointToNextLV);
                   pointToNextLV.setText("Còn " + point + "đ để lên Thành Viên Bạc");
               }
               else if (pointInt <= 250) {
                   setContentView(R.layout.activity_rankbac_view);
                   point = 250 - pointInt;
                   pointToNextLV = (TextView) findViewById(R.id.pointToNextLV);
                   pointToNextLV.setText("Còn " + point + "đ để lên Thành Viên Vàng");
               }
               else if (pointInt <= 400) {
                   setContentView(R.layout.activity_rankvang_view);
                   point = 400 - pointInt;
                   pointToNextLV = (TextView) findViewById(R.id.pointToNextLV);
                   pointToNextLV.setText("Còn " + point + "đ để lên Thành Viên Kim Cương");
               }
               else {
                   setContentView(R.layout.activity_rankkimcuong_view);
                   point =50 - (pointInt % 50);
                   pointToNextLV = (TextView) findViewById(R.id.pointToNextLV);
                   pointToNextLV.setText("Còn " + point + "đ để lên nhận ưu đãi tiếp theo");
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText( RankView.this,""+fbName,Toast.LENGTH_LONG).show();
        mdata.child("customer").child(IDUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rank = (Button) findViewById(R.id.button7);
                point = (TextView) findViewById(R.id.point);
                rank.setText("Thành viên "+dataSnapshot.child("rank").getValue().toString()+"-"+ dataSnapshot.child("point").getValue().toString());
                point.setText(dataSnapshot.child("point").getValue().toString());
                //rankview = (TextView) findViewById(R.id.rank);
                //rankview.setText("Thành viên "+dataSnapshot.child("rank").getValue().toString());
                BtnName = (Button) findViewById(R.id.button6) ;
                BtnName.setText(fbName);
                BtnAccount = (ImageButton) findViewById(R.id.imageButton19);
                BtnAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RankView.this, account_Activity.class);
                        intent.putExtra("fbName", fbName);
                        intent.putExtra("IDUser", IDUser);
                        startActivity(intent);
                    }
                });
                BtnHome = (ImageButton) findViewById(R.id.imageButton18);
                BtnHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RankView.this, MainActivity.class);
                        intent.putExtra("fbName", fbName);
                        intent.putExtra("IDUser", IDUser);
                        startActivity(intent);
                    }
                });
                BtnOrder = (ImageButton) findViewById(R.id.imageButton21);
                BtnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RankView.this, DatHang_Activity.class);
                        intent.putExtra("fbName", fbName);
                        intent.putExtra("IDUser", IDUser);
                        startActivity(intent);
                    }
                });
                BtnMap = (ImageButton) findViewById(R.id.imageButton20);
                BtnMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RankView.this, Map_Activity.class);
                        intent.putExtra("fbName", fbName);
                        intent.putExtra("IDUser", IDUser);
                        startActivity(intent);
                    }
                });
                BtnScan = (ImageButton) findViewById(R.id.imageButton17);
                BtnScan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RankView.this, ScanActivity.class);
                        intent.putExtra("fbName", fbName);
                        intent.putExtra("IDUser", IDUser);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
