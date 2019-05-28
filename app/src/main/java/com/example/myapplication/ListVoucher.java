package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.L;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ListVoucher extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore, BtnAccount;


    private  DatabaseReference mdata;
    private  ListView listView;
    private  ArrayList<String> arraybillid = new ArrayList<>();
    private  ArrayList<String> arrayListID = new ArrayList<>();
    private  ArrayList<String> arrayValue = new ArrayList<>();
    private  ArrayList<String> arrayPercen = new ArrayList<>();
    private  ArrayAdapter<String> adapter1;
    private  String key;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_voucher);
//        Intent intent = getIntent();
//        final String ID = intent.getStringExtra("IDUser");
//        final String BillId = intent.getStringExtra("IDBill");
//        final String date = intent.getStringExtra("date");
//        final String BillAmount = intent.getStringExtra("billAmount");
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraybillid);
        mdata= FirebaseDatabase.getInstance().getReference();
        listView = (ListView) findViewById(R.id.ListView_ID);
        listView.setAdapter(adapter1);
        mdata.child("customerOrder").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String string =  dataSnapshot.getKey().toString();
                key = string;
                arrayListID.add(string);
                mdata.child("customerOrder").child(string).child("unpaidbill").child("billid").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String detail = dataSnapshot.getValue().toString();
                        arraybillid.add(detail);
                        adapter1.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                mdata.child("customerOrder").child(string).child("unpaidbill").child("customerid").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String customerid = dataSnapshot.getValue().toString();
                        arrayListID.add(customerid);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListVoucher.this, CashierLastBillActivity.class);
                intent.putExtra("customerID", arrayListID.get(position));
                intent.putExtra("billID", arraybillid.get(position));
                intent.putExtra("billKey", key);
                startActivity(intent);
            }
        });




    }
}
