package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;

import customfonts.MyTextView;

public class PengaturanAkun extends AppCompatActivity implements View.OnClickListener {

    MyTextView tvx_password;

    Toolbar toolBarisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun);

        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Pengaturan Akun");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvx_password = findViewById(R.id.tv_password);
        tvx_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_password:
                startActivity(new Intent(this,UbahPassword.class));
                break;

                default:
                    break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }



}
