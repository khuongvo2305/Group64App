package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
enum Rank {
    DIAMOND, GOLD, NEW;
}
enum VoucherType {
    PERCENTAGE, VALUE;
}

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ListView lvTintuc;
    ArrayList<TinTuc> arrayTinTuc;
    ArrayList arrayLink;
    TinTucAdapter adapter;
    String fbName,IDUser;
    Button rank;
    private DatabaseReference mdata;

    private void AnhXaLink() {
        arrayLink = new ArrayList<>();

        arrayLink.add(0, new link("https://the64coffee.blogspot.com/2019/05/giai-nhiet-mua-he-cung-64-coffee-sale.html"));
        arrayLink.add(1, new link("https://the64coffee.blogspot.com/2019/05/64-coffee-sale-10-cho-hoc-sinh-sinh-vien.html"));
    }

    private void AnhXa() {
        lvTintuc = (ListView) findViewById(R.id.listviewTintuc);
        arrayTinTuc = new ArrayList<>();

        arrayTinTuc.add(new TinTuc("Giảm 30% mọi loại kem tại quán!", R.drawable.hinh1));
        arrayTinTuc.add(new TinTuc("Giảm 10% cho học sinh sinh viên!", R.drawable.hinh2));
    }


    private String json;
    DatabaseReference mData;
    ImageButton BtnAccount, BtnStore, BtnOrder, BtnMap, BtnIconStar, BtnAvartar,BtnLienhe;
    Button BtnName, BtnRank,BtnPoint;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        BtnName = (Button) findViewById(R.id.button6) ;
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
            BtnName.setText(fbName);
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
        AnhXa();
        AnhXaLink();

        adapter = new TinTucAdapter(this, R.layout.tin_tuc, arrayTinTuc);
        lvTintuc.setAdapter(adapter);

        lvTintuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                link link = (com.example.myapplication.link) arrayLink.get(position);
                webView = (WebView) findViewById(R.id.webview);
                webView.loadUrl(String.valueOf(link.getLink()));
                //Intent intent = new Intent(MainActivity.this, web.class);
                //intent.putExtra("link", String.valueOf(link.getLink()));
                //intent.putExtra("fbName",fbName);
                //intent.putExtra("IDUser", IDUser);
                //startActivity(intent);
            }
        });
        BtnLienhe = (ImageButton) findViewById(R.id.btnCall) ;
        BtnLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Bill_n_voucher.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnAvartar = (ImageButton) findViewById(R.id.imageButton11) ;
        BtnAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnIconStar = (ImageButton) findViewById(R.id.imageButton5) ;
        BtnIconStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnPoint = (Button) findViewById(R.id.button8) ;
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnOrder = (ImageButton) findViewById(R.id.imageButton21) ;
        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ViewOrder> OrderArrayList = new ArrayList<>();
                Intent intent = new Intent(MainActivity.this, DatHang_Activity.class);
                intent.putParcelableArrayListExtra("order", (ArrayList<? extends Parcelable>) OrderArrayList);
                intent.putExtra("Tong", "0");
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser",IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
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
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(date);
                if (type == VoucherType.VALUE) this.value = value;
                else if (type == VoucherType.PERCENTAGE) this.percentage = value;
                available = true;
                this.detail = detail;
                Intent pay_Intent = new Intent(MainActivity.this, Bill_n_pay.class);
                pay_Intent.putExtra("ID", ID);
                pay_Intent.putExtra("value", value);
                pay_Intent.putExtra("date",dateString);
                pay_Intent.putExtra("percentage", percentage);
                Intent voucher_Intent = new Intent(MainActivity.this, ListVoucher.class);
                voucher_Intent.putExtra("ID", ID);
                voucher_Intent.putExtra("value", value);
                voucher_Intent.putExtra("date",dateString);
                voucher_Intent.putExtra("percentage", percentage);
                voucher_Intent.putExtra("detail",detail);
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
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(date);

                Intent bill_Intent = new Intent(MainActivity.this, Bill_n_voucher.class);
                bill_Intent.putExtra("ID", ID);
                bill_Intent.putExtra("billAmount", billAmount);
                bill_Intent.putExtra("date",dateString);
                startActivity(bill_Intent);
                Intent bill_pay_Intent = new Intent(MainActivity.this, Bill_n_pay.class);
                bill_pay_Intent.putExtra("ID", ID);
                bill_pay_Intent.putExtra("billAmount", billAmount);
                bill_pay_Intent.putExtra("date",dateString);
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
