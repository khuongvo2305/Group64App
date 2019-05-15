package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThayDoiThongtin extends AppCompatActivity {
    EditText hoten, ngaysinh, diachi, dienthoai;
    Button xacnhan, quaylai;
    String S_hoten, S_ngaysinh, S_diachi, S_dienthoai;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thongtin);
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        mdata = FirebaseDatabase.getInstance().getReference();
        hoten = (EditText) findViewById(R.id.editText);
        ngaysinh = (EditText) findViewById(R.id.editText2);
        diachi= (EditText) findViewById(R.id.editText4);
        dienthoai = (EditText) findViewById(R.id.editText3);

        xacnhan = (Button) findViewById(R.id.button5) ;
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThayDoiThongtin.this, Thongtin_account.class);
                mdata.child("customer").child(ID).child("name").setValue(hoten.getText().toString());
                mdata.child("customer").child(ID).child("birthday").setValue(ngaysinh.getText().toString());
                mdata.child("customer").child(ID).child("address").setValue(diachi.getText().toString());
                mdata.child("customer").child(ID).child("phone").setValue(dienthoai.getText().toString());
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        quaylai = (Button) findViewById(R.id.button9) ;
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThayDoiThongtin.this, Thongtin_account.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
    }
}
