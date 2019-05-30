package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listOrder extends AppCompatActivity {
    Button xacnhan, thaydoi,BtnName,rank;
    GridView gridViewOrder;
    ArrayList<ViewOrder> arrayOrderView;
    viewOrderAdapter adapter;
    private DatabaseReference mdata;
    int Tong;
    String fbName,IDUser;
    private void anhxa(){
        gridViewOrder= (GridView) findViewById( R.id.viewOrder);
        Intent intent = getIntent();
        arrayOrderView = new ArrayList<ViewOrder>();
        arrayOrderView =intent.getParcelableArrayListExtra("order");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        BtnName = (Button) findViewById(R.id.button6) ;
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            BtnName.setText(fbName);
        }
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("customer").child(IDUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rank = (Button) findViewById(R.id.button7);
                rank.setText("Thành viên "+dataSnapshot.child("rank").getValue().toString()+"-"+ dataSnapshot.child("point").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final TextView TongGioHang = (TextView) findViewById(R.id.tonggiohang);
        anhxa();
        adapter = new viewOrderAdapter(this, R.layout.dongvieworder, arrayOrderView);
        gridViewOrder.setAdapter(adapter);

        gridViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayOrderView.remove(position);
                gridViewOrder.setAdapter(adapter);
                Tong =0;
                for(int i=0 ;i<arrayOrderView.size();i++)
                {
                    Tong = Tong + Integer.parseInt(arrayOrderView.get(i).getTongtien());
                }
                TongGioHang.setText(String.valueOf(Tong));
            }
        });
        Tong =0;
        for(int i=0 ;i<arrayOrderView.size();i++)
        {
            Tong = Tong + Integer.parseInt(arrayOrderView.get(i).getTongtien());
        }
        TongGioHang.setText(String.valueOf(Tong));

        xacnhan = (Button) findViewById(R.id.xacnhan) ;
        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listOrder.this, Bill_n_Voucher_list.class);
                intent.putExtra("Tong",String.valueOf(Tong));
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        thaydoi = (Button) findViewById(R.id.thaydoiOrder);
        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listOrder.this, DatHang_Activity.class);
                intent.putParcelableArrayListExtra("order", (ArrayList<? extends Parcelable>) arrayOrderView);
                intent.putExtra("Tong", String.valueOf(Tong));
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
              /*  startActivity(new Intent(getApplicationContext(), DatHang_Activity.class)
                        .putParcelableArrayListExtra("order", (ArrayList<? extends Parcelable>) arrayOrderView));
                */
            }
        });
    }
}
