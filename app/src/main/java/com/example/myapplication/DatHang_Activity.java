package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatHang_Activity extends AppCompatActivity {
    ImageButton BtnAccount, BtnHome, BtnMap, BtnStore, cong, tru,BtnIconStar, BtnAvartar;
    Button xacnhanDialog, huydialog,BtnName,rank,BtnRank,BtnPoint;
    ImageView hinhsp,Btngiohang;
    TextView tensp, editsoluongsp, thanhtien, txttongtien;
    GridView gridViewmenu;
    int soluongsanpham;
    String tempt;
    ArrayList<Menu> arrayMenu;
    ArrayList<ViewOrder> OrderArrayList;
    MenuAdapter adapter;
    String fbName,IDUser;
    private DatabaseReference mdata;
    public void Dialogmenu(final int position){
        final Dialog dialog = new Dialog(DatHang_Activity.this);
        dialog.setContentView(R.layout.dialog_menu);

        hinhsp = (ImageView) dialog.findViewById(R.id.sanphamview);
        tensp = (TextView) dialog.findViewById(R.id.txttensp) ;
        editsoluongsp = (TextView) dialog.findViewById(R.id.edittextsoluong) ;
        thanhtien = (TextView) dialog.findViewById(R.id.thanhtien);
        tensp.setText(arrayMenu.get(position).getTen());
        hinhsp.setImageResource(arrayMenu.get(position).getHinh());
        thanhtien.setText("0");
        cong = (ImageButton) dialog.findViewById(R.id.cong) ;
        tru  = (ImageButton) dialog.findViewById(R.id.tru);

        tempt=editsoluongsp.getText().toString();
        soluongsanpham= Integer.parseInt(tempt);
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluongsanpham=soluongsanpham+1;
                tempt=String.valueOf(soluongsanpham);
                editsoluongsp.setText(tempt);
                thanhtien.setText(String.valueOf(soluongsanpham*Integer.parseInt(arrayMenu.get(position).getMota())));
            }
        });
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluongsanpham>0) {
                    soluongsanpham = soluongsanpham - 1;
                    tempt = String.valueOf(soluongsanpham);
                    editsoluongsp.setText(tempt);
                    thanhtien.setText(String.valueOf(soluongsanpham*Integer.parseInt(arrayMenu.get(position).getMota())));
                }
            }
        });

        xacnhanDialog =(Button) dialog.findViewById(R.id.xacnhan);
        huydialog = (Button) dialog.findViewById(R.id.huy);
        txttongtien = (TextView) findViewById(R.id.tonggiohang);

        xacnhanDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp1 = Integer.parseInt(thanhtien.getText().toString());
                int temp2;
                if (txttongtien.getText().toString().equals("")) temp2 = 0;
                else
                temp2 = Integer.parseInt(txttongtien.getText().toString());
                txttongtien.setText(String.valueOf(temp1+temp2));
                String ten = arrayMenu.get(position).getTen().toString();
                String giatien = arrayMenu.get(position).getMota();
                String soluong =tempt;
                String tongtien = String.valueOf(temp1);
                ViewOrder test123 = new ViewOrder(ten, giatien, soluong,tongtien);
                if (OrderArrayList !=null)
                OrderArrayList.add(test123);
                dialog.cancel();
            }

        });
        huydialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void anhxamenu(){
        gridViewmenu = (GridView) findViewById( R.id.viewmenu);
        arrayMenu = new ArrayList<>();
        arrayMenu.add(new Menu("Trà sữa truyền thống", "30000", R.drawable.trasua));
        arrayMenu.add(new Menu("Trà đào cam xả", "40000", R.drawable.tradao));
        arrayMenu.add(new Menu("Sữa tươi trân châu đường đen", "20000", R.drawable.suatuoitranchau));
        arrayMenu.add(new Menu("Cà phê", "32000", R.drawable.caphe));
        arrayMenu.add(new Menu("Nước ép cam", "22000", R.drawable.nuocam));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        anhxamenu();
        adapter = new MenuAdapter(this, R.layout.dong_menu, arrayMenu);
        gridViewmenu.setAdapter(adapter);
        Intent intent = getIntent();
        OrderArrayList = intent.getParcelableArrayListExtra("order");
        String Tong = intent.getStringExtra("Tong");
        TextView test = (TextView) findViewById(R.id.tonggiohang);
        test.setText(Tong);
        gridViewmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialogmenu(position);
            }
        });
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
        Btngiohang = (ImageView) findViewById(R.id.listorder) ;
        Btngiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), listOrder.class)
                        .putParcelableArrayListExtra("order", (ArrayList<? extends Parcelable>) OrderArrayList)
                        .putExtra("fbName",fbName)
                        .putExtra("IDUser",IDUser));
            }
        });
        BtnAccount = (ImageButton) findViewById(R.id.imageButton19) ;
        BtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, account_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnHome = (ImageButton) findViewById(R.id.imageButton18) ;
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, MainActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnMap = (ImageButton) findViewById(R.id.imageButton20) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, Map_Activity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnStore = (ImageButton) findViewById(R.id.imageButton17) ;
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, ScanActivity.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnName = (Button) findViewById(R.id.button6) ;
        BtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
        BtnRank = (Button) findViewById(R.id.button7) ;
        BtnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatHang_Activity.this, RankView.class);
                intent.putExtra("fbName",fbName);
                intent.putExtra("IDUser", IDUser);
                startActivity(intent);
            }
        });
    }
}
