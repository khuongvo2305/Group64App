package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lichsuchitiet extends AppCompatActivity {
    Button Quaylai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsuchitiet);
        Intent intent = getIntent();
        final String ID = getIntent().getStringExtra("IDUser");
        final String fbName = getIntent().getStringExtra("fbName");
        final String billid = getIntent().getStringExtra("billid");
        final String billdate = getIntent().getStringExtra("billdate");
        final String billamount = getIntent().getStringExtra("billamount");
        final String billtotal = getIntent().getStringExtra("billtotal");
        final String billvoucherid = getIntent().getStringExtra("billvoucherid");
        final String point = getIntent().getStringExtra("point");

        TextView BillId = (TextView) findViewById(R.id.txtMabill);
        BillId.setText(billid);
        TextView BillDate = (TextView) findViewById(R.id.txtNgayinbill);
        String billdate2 = billdate.split("\\=")[2].split("\\}")[0];
        BillDate.setText(billdate2);
        TextView Billamount = (TextView) findViewById(R.id.txtTongbill);
        Billamount.setText(billamount);
        TextView BillTotal = (TextView) findViewById(R.id.txtTongbill2);
        BillTotal.setText(billtotal);
        TextView Billpoint = (TextView) findViewById(R.id.txtTongbill3);
        Billpoint.setText(point);
        TextView Billvoucherid = (TextView) findViewById(R.id.txtTongbill5);
        Billvoucherid.setText(billvoucherid);


        Quaylai = (Button) findViewById(R.id.button10) ;
        Quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lichsuchitiet.this, LichSuGiaoDich.class);
                intent.putExtra("IDUser", ID);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
    }



}
