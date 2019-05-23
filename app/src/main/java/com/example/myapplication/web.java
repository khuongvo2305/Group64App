package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;

public class web extends AppCompatActivity {

    WebView webView;
    String IDUser,fbName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        if (getIntent().getStringExtra("IDUser") != null)
        {
            IDUser = getIntent().getStringExtra("IDUser");
        }
        if (getIntent().getStringExtra("fbName") != null) {
            fbName = getIntent().getStringExtra("fbName");
        }
        webView = (WebView) findViewById(R.id.webview);

        Intent intent =  getIntent();
        String link = intent.getStringExtra("link");
        intent.putExtra("fbName",fbName);
        intent.putExtra("IDUser", IDUser);
        webView.loadUrl(link);
    }
}
