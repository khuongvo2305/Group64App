package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VoucherActivity extends AppCompatActivity {
    private ImageButton myVoucherBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher);
        myVoucherBtn =  findViewById(R.id.imageButton6);
        myVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voucher_intent = new Intent(VoucherActivity.this, VoucherActivity.class);
                startActivity(voucher_intent);
            }
        });
    }
}
