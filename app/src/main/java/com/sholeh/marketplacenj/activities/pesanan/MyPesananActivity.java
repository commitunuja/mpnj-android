package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.R;

public class MyPesananActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    TabLayout tab;
    AdapterPesanan adapter;
    ImageView imgPesanan, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pesanan);

        viewPager = findViewById(R.id.pager_pesanan);
        tab = findViewById(R.id.tab_pesanan);
//        imgPesanan = findViewById(R.id.ivSearchPesanan);
//        back = findViewById(R.id.ivBack);

//        back.setOnClickListener(this);
//        imgPesanan.setOnClickListener(this);

        adapter = new AdapterPesanan(getApplicationContext(), getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ivSearchPesanan:
////                startActivity(new Intent(this, SearchPesanankuActivity.class));
//                break;
//            case R.id.ivBack:
////                startActivity(new Intent(this, .class));
//            default:
//                break;
//        }
    }
}