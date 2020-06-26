package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.List;

public class MyPesananActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    TabLayout tab;
    AdapterPesanan adapter;
    ImageView imgPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pesanan);

        viewPager = findViewById(R.id.pager_pesanan);
        tab = findViewById(R.id.tab_pesanan);
        imgPesanan = findViewById(R.id.ivSearchPesanan);

        imgPesanan.setOnClickListener(this);

        adapter = new AdapterPesanan(getApplicationContext(), getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSearchPesanan:
                startActivity(new Intent(this, SearchPesanankuActivity.class));
                break;

            default:
                break;
        }
    }
}