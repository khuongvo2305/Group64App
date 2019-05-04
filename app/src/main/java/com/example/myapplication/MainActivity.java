package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;
enum Rank {
    DIAMOND, GOLD, NEW;
}
enum VoucherType {
    PERCENTAGE, VALUE;
}

public class MainActivity extends AppCompatActivity {
    private String json;
    DatabaseReference mData;
    ImageButton BtnAccount, BtnStore, BtnOrder, BtnMap, BtnIconStar, BtnAvartar,BtnLienhe;
    Button BtnName, BtnRank,BtnPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnLienhe = (ImageButton) findViewById(R.id.imageButton9) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LienHe.class);
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
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(MainActivity.this, DatHang_Activity.class);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map_Activity.class);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TichDiem_Activity.class);
                startActivity(intent);
            }
        });

        /////////////////
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        class Voucher
        {
            public String ID;
            public VoucherType type;
            public String detail;
            public int value;
            public int percentage;
            public boolean available;
            public Date date;
            public Voucher(){};
            public Voucher(String ID, VoucherType type, String detail, int value, Date date) {
                this.ID = ID;
                this.type = type;
                this.date = date;
                if (type == VoucherType.VALUE) this.value = value;
                else if (type == VoucherType.PERCENTAGE) this.percentage = value;
                available = true;
                this.detail = detail;
            }
        }

        class Bill
        {
            public String ID;
            public Date date;
            public int billAmount;
            public Voucher voucher;
            public int BillDiscount;
            public int BillTotal;
            public boolean state;
            public int point;

            public Bill(String ID, Date date, int billAmount) {
                this.ID = ID;
                this.date = date;
                this.billAmount = billAmount;
                this.voucher = voucher;
                this.state = false; //chưa thanh toán
                Intent bill_Intent = new Intent(MainActivity.this, Bill_n_Pay.class);
                bill_Intent.putExtra("ID", ID);
                bill_Intent.putExtra("billAmount", billAmount);
                bill_Intent.putExtra("date",date);
                startActivity(bill_Intent);

                // TO DO
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
            public int visitTimes;

            public User() {
                // Default constructor required for calls to DataSnapshot.getValue(User.class)
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



        }
       /* User usr = new User("123","456","543","123");
        mData = FirebaseDatabase.getInstance().getReference();
        //New user
        mData.child("User").setValue(usr); */
        //

    }



}
