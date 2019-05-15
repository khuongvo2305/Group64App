package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LichSuGiaoDich extends AppCompatActivity {
    ImageButton BtnHome, BtnOrder,BtnMap,BtnStore, BtnAccount;
    Button Quaylai;
    private  DatabaseReference mdata;
    private  ListView listView;
    private  ArrayList<String> arraybillid = new ArrayList<>();
    private  ArrayList<String> arrayListIDhistory = new ArrayList<>();
    private  ArrayList<String> arraybillamount = new ArrayList<>();
    private  ArrayList<String> arraybilltotal = new ArrayList<>();
    private  ArrayList<String> arraybilldate = new ArrayList<>();
    private  ArrayList<String> arraybillpoint = new ArrayList<>();
    private  ArrayList<String> arraybillVoucherID = new ArrayList<>();
    private  ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        mdata= FirebaseDatabase.getInstance().getReference();
        listView = (ListView) findViewById(R.id.ListView_ID);
        mdata.child("customer").child(ID).child("history").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String string =  dataSnapshot.getKey().toString();
                Collections.reverse(arrayListIDhistory);
                arrayListIDhistory.add(string);
                Collections.reverse(arrayListIDhistory);
                adapter1.notifyDataSetChanged();
                mdata.child("customer").child(ID).child("history").child(string).child("billid").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String billid = dataSnapshot.getValue().toString();
                        Collections.reverse(arrayListIDhistory);
                        arraybillid.add(billid);
                        Collections.reverse(arrayListIDhistory);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                mdata.child("customer").child(ID).child("history").child(string).child("billamount").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String billamount = dataSnapshot.getValue().toString();
                        Collections.reverse(arrayListIDhistory);
                        arraybillamount.add(billamount);
                        Collections.reverse(arrayListIDhistory);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                mdata.child("customer").child(ID).child("history").child(string).child("billtotal").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String billamount = dataSnapshot.getValue().toString();
                        Collections.reverse(arrayListIDhistory);
                        arraybilltotal.add(billamount);
                        Collections.reverse(arrayListIDhistory);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                mdata.child("customer").child(ID).child("history").child(string).child("date").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String billamount = dataSnapshot.getValue().toString();
                        Collections.reverse(arrayListIDhistory);
                        arraybilldate.add(billamount);
                        Collections.reverse(arrayListIDhistory);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                mdata.child("customer").child(ID).child("history").child(string).child("point").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String billamount = dataSnapshot.getValue().toString();
                        Collections.reverse(arrayListIDhistory);
                        arraybillpoint.add(billamount);
                        Collections.reverse(arrayListIDhistory);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                mdata.child("customer").child(ID).child("history").child(string).child("voucherid").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String billamount = dataSnapshot.getValue().toString();
                        Collections.reverse(arrayListIDhistory);
                        arraybillVoucherID.add(billamount);
                        Collections.reverse(arrayListIDhistory);
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
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListIDhistory);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LichSuGiaoDich.this, Lichsuchitiet.class);
                intent.putExtra("ID", ID);
                intent.putExtra("billid", arraybillid.get(position));
                intent.putExtra("billdate", arraybilldate.get(position));
                intent.putExtra("billamount", arraybillamount.get(position));
                intent.putExtra("billtotal", arraybilltotal.get(position));
                intent.putExtra("point",arraybillpoint.get(position));
                intent.putExtra("billvoucherid", arraybillVoucherID.get(position));
                startActivity(intent);
            }
        });

        Quaylai = (Button) findViewById(R.id.button11) ;
        Quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichSuGiaoDich.this, account_Activity.class);
                startActivity(intent);
            }
        });



    }
}
