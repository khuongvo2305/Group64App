package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThayDoiThongtin extends AppCompatActivity {
    EditText ngaysinh, diachi, dienthoai;
    TextView hoten;
    Button xacnhan, quaylai;
    String S_hoten, S_ngaysinh, S_diachi, S_dienthoai,IDUser,fbName;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thongtin);
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("IDUser");
        mdata = FirebaseDatabase.getInstance().getReference();
        hoten = (TextView) findViewById(R.id.editText);
        mdata.child("customer").child(IDUser).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hoten.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ngaysinh = (EditText) findViewById(R.id.editText2);
        mdata.child("customer").child(IDUser).child("birthday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ngaysinh.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        diachi= (EditText) findViewById(R.id.editText4);
        mdata.child("customer").child(IDUser).child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                diachi.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dienthoai = (EditText) findViewById(R.id.editText3);
        mdata.child("customer").child(IDUser).child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dienthoai.setText(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        xacnhan = (Button) findViewById(R.id.button5) ;
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThayDoiThongtin.this, Thongtin_account.class);
                mdata.child("customer").child(ID).child("birthday").setValue(ngaysinh.getText().toString());
                mdata.child("customer").child(ID).child("address").setValue(diachi.getText().toString());
                mdata.child("customer").child(ID).child("phone").setValue(dienthoai.getText().toString());
                intent.putExtra("IDUser", IDUser);
                intent.putExtra("fbName", fbName);
                startActivity(intent);
            }
        });
        quaylai = (Button) findViewById(R.id.button9) ;
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThayDoiThongtin.this, Thongtin_account.class);
                mdata.child("customer").child(ID).child("birthday").setValue(ngaysinh.getText().toString());
                mdata.child("customer").child(ID).child("address").setValue(diachi.getText().toString());
                mdata.child("customer").child(ID).child("phone").setValue(dienthoai.getText().toString());
                intent.putExtra("IDUser", IDUser);
                intent.putExtra("fbName", fbName);
                startActivity(intent);
            }
        });
    }
}
