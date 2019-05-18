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
    //Biến sử lý unpaid-bill
    private Integer upbstate;
    private String upbcustomerid;
    private Integer upbtotal;
    private Integer upbamount;
    private String upbid;
    private String upbdate;
    private Integer upbpoint;
    private String upbvoucherid;
    private Integer oldpoint;
    private Integer newpoint;

    DatabaseReference mData;
    ImageButton BtnAccount, BtnStore, BtnOrder, BtnMap, BtnIconStar, BtnAvartar,BtnLienhe;
    Button BtnName, BtnRank,BtnPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String idUser = getIntent().getStringExtra("idUser");
        String ID = getIntent().getStringExtra("idBill");
        int billAmount = getIntent().getIntExtra("billAmount",0);
        String dateString = getIntent().getStringExtra("dateBill");
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, account_Activity.class);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CashierNewBillActivity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CashierNewBillActivity.class);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnLienhe = (ImageButton) findViewById(R.id.imageButton9) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LienHe.class);
                startActivity(intent);
            }
        });
        /////////////////
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
                Intent pay_Intent = new Intent(MainActivity.this, Bill_n_pay.class);
                pay_Intent.putExtra("value", value);
                pay_Intent.putExtra("percentage", percentage);
                Intent voucher_Intent = new Intent(MainActivity.this, ListVoucher.class);
                voucher_Intent.putExtra("value", value);
                voucher_Intent.putExtra("percentage", percentage);
                voucher_Intent.putExtra("detail",detail);
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
                Intent bill_Intent = new Intent(MainActivity.this, Bill_n_voucher.class);
                bill_Intent.putExtra("ID", ID);
                bill_Intent.putExtra("billAmount", billAmount);
                bill_Intent.putExtra("date",date);
                Intent bill_pay_Intent = new Intent(MainActivity.this, Bill_n_pay.class);
                bill_pay_Intent.putExtra("ID", ID);
                bill_pay_Intent.putExtra("billAmount", billAmount);
                bill_pay_Intent.putExtra("date",date);
            }



            // Hiện thực hàm khởi tạo Bill() nhận tham số là chuỗi json được quét từ QR,
            // nhận 3 thông số ID, date, billamount
            // Sau đó gọi hàm hiển thị màn hình chọn voucher
            // Sau khi chọn voucher, phân loại voucher, giảm theo % hay trừ thẳng, từ đó tính
            // 3 giá trị còn lại
            // gọi hàm trả message về cho cashier số tiền cần thanh toán
            // Sau khi cashier chuyển status thành true - đã thanh toán trên firebase,
            // Hàm on  change sẽ cập nhật lịch sử giao dịch và điểm cho user (đồng thời cập nhật hạng, tặng voucher nếu đủ)
            // https://firebase.google.com/docs/database/android/read-and-write?authuser=0
            //
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
       /* User usr = new User("123","456","543","123");
        mData = FirebaseDatabase.getInstance().getReference();
        //New user
        mData.child("User").setValue(usr); */
        //

        //Xử lý sau khi cashier xác nhận bill
        mData = FirebaseDatabase.getInstance().getReference();
        /*mData.addValueEventListener(new ValueEventListener() {

            private Class history;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Chack trạng thái thanh toán
                upbstate = Integer.parseInt(dataSnapshot.child("unpaidbill").child("state").getValue().toString());
                //Nếu đã thanh toán
                if (upbstate == 1)
                {
                    //TODO
                    //Set các giá trị
                    upbcustomerid = dataSnapshot.child("unpaidbill").child("customerid").getValue().toString();
                    upbtotal = Integer.parseInt(dataSnapshot.child("unpaidbill").child("billtotal").getValue().toString());
                    upbamount = Integer.parseInt(dataSnapshot.child("unpaidbill").child("billamount").getValue().toString());
                    upbid =  dataSnapshot.child("unpaidbill").child("billid").getValue().toString();
                    upbdate = dataSnapshot.child("unpaidbill").child("date").toString();
                    upbpoint = Integer.parseInt(dataSnapshot.child("unpaidbill").child("point").getValue().toString());
                    upbvoucherid = dataSnapshot.child("unpaidbill").child("voucherid").getValue().toString();

                    //Thêm bill vào history
                    bill newbill = new bill(upbamount, upbid,upbtotal,upbcustomerid,upbdate,upbpoint,upbstate,upbvoucherid);
                    mData.child("customer").child(upbcustomerid).child("history").child(upbid).setValue(newbill);
                    Toast.makeText(MainActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();

                    //Sửa point
                    oldpoint = Integer.parseInt(dataSnapshot.child("customer").child(upbcustomerid).child("point").getValue().toString());
                    newpoint = upbpoint + oldpoint;
                    mData.child("customer").child(upbcustomerid).child("point").setValue(newpoint);

                    //reset unpaid bill
                    mData.child("unpaidbill").child("billamount").setValue(0);
                    mData.child("unpaidbill").child("billid").setValue("null");
                    mData.child("unpaidbill").child("billtotal").setValue(0);
                    //mData.child("unpaidbill").child("customerid").setValue("null");
                    mData.child("unpaidbill").child("date").setValue("null");
                    mData.child("unpaidbill").child("point").setValue(0);
                    mData.child("unpaidbill").child("state").setValue(0);
                    mData.child("unpaidbill").child("voucherid").setValue("null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Thanh toán thất bại!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }




}
