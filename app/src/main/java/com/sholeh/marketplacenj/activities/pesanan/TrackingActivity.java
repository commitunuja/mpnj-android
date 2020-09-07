package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sholeh.marketplacenj.R;

public class TrackingActivity extends AppCompatActivity {
    WebView myweb;
    String kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        kode = getIntent().getStringExtra("kode");
        Toast.makeText(this, ""+kode, Toast.LENGTH_SHORT).show();
        myweb = (WebView) findViewById(R.id.webview);
        myweb.getSettings().setJavaScriptEnabled(true);
        myweb.loadUrl("http://belanj.id/pesanan/trackingwebv/"+kode);
    }
}