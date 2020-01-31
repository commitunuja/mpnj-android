package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.marketplacenj.R;

public class AkunActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView nav_home, nav_notifikasi, nav_transaksi, navprofile;
    TextView tvx_login;

    FloatingActionButton fb_favourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        nav_home = findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_notifikasi = findViewById(R.id.nav_notifikasi);
        nav_notifikasi.setOnClickListener(this);
        nav_transaksi = findViewById(R.id.nav_transaksi);
        nav_transaksi.setOnClickListener(this);
        navprofile = findViewById(R.id.nav_profile);
        navprofile.setOnClickListener(this);
        fb_favourite = findViewById(R.id.fab_menu);
        fb_favourite.setOnClickListener(this);

        tvx_login = findViewById(R.id.tvLogIn);
        tvx_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.nav_home:
                Toast.makeText(this, "nav home", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
                break;

            case R.id.nav_transaksi:
                Toast.makeText(this, "nav transaksi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_menu:
                Toast.makeText(this, "nav fab", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_notifikasi:
                Toast.makeText(this, "nav notifikasi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvLogIn:
                Intent pindah = new Intent(this, LoginActivity.class);
                startActivity(pindah);

                break;


            default:
                break;
        }
    }
}



