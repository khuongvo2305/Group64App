package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bill_n_Pay extends AppCompatActivity {
    private Button useVoucherBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_pickvoucher);
        String ID = getIntent().getStringExtra("ID");
        int billAmount = getIntent().getIntExtra("billAmount",0);

        TextView txtMabill = (TextView) findViewById(R.id.txtMabill);
        txtMabill.setText(ID);
        TextView txtTongbill = (TextView) findViewById(R.id.txtTongbill);
        txtTongbill.setText(billAmount);
        useVoucherBtn = (Button) findViewById(R.id.useVoucherBtn);
        useVoucherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voucher_intent = new Intent(Bill_n_Pay.this, VoucherActivity.class);
                startActivity(voucher_intent);
            }
        });
    }

}
