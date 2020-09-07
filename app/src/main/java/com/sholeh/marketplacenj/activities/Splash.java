package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;


public class Splash extends AppCompatActivity {
    private String TAG = "splashAcctivity";
    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView imageview = findViewById(R.id.ic_logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        imageview.startAnimation(animation);

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
                    Intent intent = new Intent(Splash.this, Utama.class);
                    startActivity(intent);
                    finish();

            }
        }, 1800);

    }
}
