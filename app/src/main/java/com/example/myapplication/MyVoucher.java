package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyVoucher extends AppCompatActivity {
    private Button idBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher);
        final String ID = getIntent().getStringExtra("ID");
        final int value = getIntent().getIntExtra("value",0);
        final int percentage = getIntent().getIntExtra("percentage",0);
        String detail = getIntent().getStringExtra("detail");

        idBtn = (Button) findViewById(R.id.idBtn);
        idBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bill_pay_intent = new Intent(MyVoucher.this, Bill_n_pay.class);
                bill_pay_intent.putExtra("value", value);
                bill_pay_intent.putExtra("percentage",percentage);
                bill_pay_intent.putExtra("ID_vc",ID);
                startActivity(bill_pay_intent);
            }
        });
    }
}
