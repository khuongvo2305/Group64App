package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello World");
        // Read from the database
        private DatabaseReference mDatabase;
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        @IgnoreExtraProperties
        enum Rank
        {
            NEW, GOLD, DIAMOND;
        }
        enum VoucherType
        {
            VALUE, PERCENTAGE, FREE;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format( new Date()   );
        Date date       = format.parse ( "2009-12-31" );
        public class Voucher
        {
            public String ID;
            public VoucherType type;
            public String detail;
            public int value;
            public int percentage;
            public int free;
            public boolean available;
            public Date date;
            public Voucher(){};
            public Voucher(String ID, VoucherType type, String detail, int value, Date date) {
                this.ID = ID;
                this.type = type;
                this.date = date;
                if (type == VoucherType.VALUE) this.value = value;
                else if (type == VoucherType.PERCENTAGE) this.percentage = value;
                else if (type == VoucherType.FREE) this.free = value;
                available = 1;
                this.detail = detail;
            }
        }

        public class Bill
        {
            public String ID;
            public Date date;
            public int BillAmount;
            public Voucher voucher;
            public int BillDiscount;
            public int BillTotal;
            public boolean state;
            public int point;

            public Bill(String ID, Date date, int billAmount, Voucher voucher) {
                this.ID = ID;
                this.date = date;
                BillAmount = billAmount;
                this.voucher = voucher;
                //if...
            }
        }
        public class User {

            public String ID;
            public String name;
            public String phone;
            public String address;
            public int point;
            public Rank rank;
            public Voucher[] VoucherDatabase;
            public int voucherAvailable;
            public Bill[] History;
            public int visitTimes;

            public User() {
                // Default constructor required for calls to DataSnapshot.getValue(User.class)
            }

            public User(String ID, String name, String phone, String address, int point, Voucher[] voucherDatabase, Bill[] history) {
                this.ID = ID;
                this.name = name;
                this.phone = phone;
                this.address = address;
                this.point = point;
                VoucherDatabase = voucherDatabase;
                History = history;
            }

            public User(String ID, String name, String phone, String address) {
                this.ID = ID;
                this.name = name;
                this.phone = phone;
                this.address = address;
                this.rank = Rank.NEW;
                this.visitTimes = 0;
                this.point = 0;
            }

            private void writeNewUser(String json) {
                User usr = new User(json);

                mDatabase.child("users").child(userId).setValue(user);
            }


        }
    }



}
