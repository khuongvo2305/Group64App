package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DieuKhoanSudung extends AppCompatActivity {
    Button Quaylai;
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
        setContentView(R.layout.activity_dieu_khoan_sudung);
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("IDUser");
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
                        String detail = dataSnapshot.getValue().toString();
                        arraydetail.add(detail);
                        adapter1.notifyDataSetChanged();
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

        Quaylai = (Button) findViewById(R.id.quaylai) ;
        Quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DieuKhoanSudung.this, account_Activity.class);
                startActivity(intent);
            }
        });

    }
}
