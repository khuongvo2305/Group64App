package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class BillQRActivity extends AppCompatActivity {
    private static final int MAX_LENGTH = 8;
    ImageButton BtnAccount, BtnHome, BtnOrder, BtnMap;
    DatabaseReference mData;
    public static class RandomString {

        // function to generate a random string of length n
        static String getAlphaNumericString(int n) {

            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    ;

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }

            return sb.toString();
        }
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier_qr);
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        //EditText edtSoTien = (EditText) findViewById(R.id.edtSoTien);
        //Button btntaobill = (Button) findViewById(R.id.btntaobill);
        String billAmount = getIntent().getStringExtra("SoTien");
        final String IDrandom = RandomString.getAlphaNumericString(8);
        final String ID = IDrandom;
        //String billAmount = edtSoTien.getText().toString();
        String dateString = date;
        String QRtext = ID + "-" + dateString + "-" + billAmount + "-";

        final TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(ID);
        final TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(billAmount);
        final TextView txtNgayinbill = (TextView) findViewById(R.id.txtNgayinbill);
        txtNgayinbill.setText(dateString);
        ImageView QRCode = (ImageView) findViewById(R.id.imageQR);
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(QRtext, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.imageQR)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        Button BtnDTT = (Button) findViewById(R.id.buttonDaThanhToan) ;

        BtnDTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData = FirebaseDatabase.getInstance().getReference();
                if (mData.child("unpaidbill").child("billid").equals(ID)) {
                    mData.child("unpaidbill").child("state").setValue(1);
                    Toast.makeText(BillQRActivity.this, "Thanh toán thành công",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(BillQRActivity.this, CashierNewBillActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(BillQRActivity.this, "Thanh toán thất bại",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        Button BtnHuy = (Button) findViewById(R.id.buttonHuyDonHang);
        BtnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMabill.setText("");
                txtNgayinbill.setText("");
                txtTongbill.setText("");
                Intent intent = new Intent(BillQRActivity.this, CashierNewBillActivity.class);
                startActivity(intent);
            }
        });

        Button BtnQuayLai = (Button) findViewById(R.id.buttonQuayLai);
        BtnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillQRActivity.this, CashierNewBillActivity.class);
                startActivity(intent);
            }
        });

        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("unpaidbill").child("billid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.getValue().toString().equals(ID)) {
                        Intent intent = new Intent(BillQRActivity.this, CashierLastBillActivity.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
