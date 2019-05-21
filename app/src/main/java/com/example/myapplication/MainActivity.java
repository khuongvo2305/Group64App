package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum Rank {
    DIAMOND, GOLD, NEW, SILVER;
}

public class MainActivity extends AppCompatActivity {
    private String json;
    DatabaseReference mData;
    ImageButton BtnMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String idUser = getIntent().getStringExtra("idUser");
        String ID = getIntent().getStringExtra("idBill");
        int billAmount = getIntent().getIntExtra("billAmount",0);
        String dateString = getIntent().getStringExtra("dateBill");

        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CashierNewBillActivity.class);
                startActivity(intent);
            }
        });

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        class Voucher
        {
            public String detail;
            public int value;
            public int percentage;
            public boolean available;
            public Voucher(){};
            public Voucher(String detail, int value) {
                available = true;
                this.detail = detail;
            }
        }

        class Bill
        {
            public String ID;
            public String dateString;
            public int billAmount;
            public Voucher voucher;
            public int BillDiscount;
            public int BillTotal;
            public boolean state;
            public int point;

            public Bill(String ID, String date, int billAmount) {
                this.ID = ID;
                this.dateString = date;
                this.billAmount = billAmount;
                this.voucher = voucher;
                this.state = false; //chưa thanh toán
            }
        }
        new Bill(ID, dateString, billAmount);
        class User {

            public String ID;
            public String name;
            public String phone;
            public String address;
            public int point;
            public Rank rank;
            public Voucher[] VoucherDatabase;
            public int voucherAvailable;
            public Bill[] History;

            public User() {
                // Default constructor required for calls to DataSnapshot.getValue(User.class)
            }
            public User(String ID, String name, String phone, String address) {
                this.ID = ID;
                this.name = name;
                this.phone = phone;
                this.address = address;
                this.rank = Rank.NEW;
                this.point = 0;
            }
        }
        mData = FirebaseDatabase.getInstance().getReference();
    }
}
