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
import android.widget.Button;
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

public class ListVoucher_list extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore, BtnAccount;
    Button BtnName;
    String fbName,IDUser;


    private  DatabaseReference mdata;
    private  ListView listView;
    private  ArrayList<String> arraydetail = new ArrayList<>();
    private  ArrayList<String> arrayListID = new ArrayList<>();
    private  ArrayList<String> arrayValue = new ArrayList<>();
    private  ArrayList<String> arrayPercen = new ArrayList<>();
    private  ArrayAdapter<String> adapter1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_voucher);
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("IDUser");
        final String BillId = intent.getStringExtra("IDBill");
        final String date = intent.getStringExtra("date");
        final String BillAmount = intent.getStringExtra("billAmount");
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraydetail);
        mdata= FirebaseDatabase.getInstance().getReference();
        listView = (ListView) findViewById(R.id.ListView_ID);
        listView.setAdapter(adapter1);
        mdata.child("customer").child(ID).child("voucher").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String string =  dataSnapshot.getKey().toString();
                arrayListID.add(string);
                mdata.child("customer").child(ID).child("voucher").child(string).child("voucher").child("detail").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            String detail = dataSnapshot.getValue().toString();
                            arraydetail.add(detail);
                            adapter1.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                mdata.child("customer").child(ID).child("voucher").child(string).child("voucher").child("value").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            String value = dataSnapshot.getValue().toString();
                            arrayValue.add(value);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                mdata.child("customer").child(ID).child("voucher").child(string).child("voucher").child("percen").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {

                            String person = dataSnapshot.getValue().toString();
                            arrayPercen.add(person);
                        }
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
                Intent intent = new Intent(ListVoucher_list.this, Bill_n_pay_list.class);
                intent.putExtra("IDUser", ID);
                intent.putExtra("VoucherID", arrayListID.get(position));
                intent.putExtra("Detail", arraydetail.get(position));
                intent.putExtra("Value", arrayValue.get(position));
                intent.putExtra("Percen", arrayPercen.get(position));
                intent.putExtra("IDBill",BillId);
                intent.putExtra("date", date);
                intent.putExtra("billAmount", BillAmount);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });


        BtnHome = (ImageButton) findViewById(R.id.BtnHome) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListVoucher_list.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.BtnDatHang) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListVoucher_list.this, DatHang_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.BtnCuaHang) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListVoucher_list.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.BtnTichdiem) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListVoucher_list.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                startActivity(intent);
            }
        });
    }
}
