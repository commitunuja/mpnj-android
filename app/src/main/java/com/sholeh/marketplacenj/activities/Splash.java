package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.sholeh.marketplacenj.R;


public class Splash extends AppCompatActivity {
    private String TAG = "splashAcctivity";
    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv = findViewById(R.id.shimmerText);
        shimmer = new Shimmer();
        shimmer.start(tv);
        Log.e(TAG, " splash start showing");
        init();
    }

    public void init(){

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        }, 1800);

    }
}