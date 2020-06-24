package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;

import java.util.List;

import xyz.santeri.wvp.WrappingViewPager;

public class MyPesananActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    TabLayout tab;
    AdapterPesanan adapter;


    ImageView imgPesanan;
    private List<PesananModel> pesanan_models;
    Toolbar toolBarisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pesanan);

//
//        toolBarisi = findViewById(R.id.toolbar);
//        toolBarisi.setTitle("Pesananku");
//        setSupportActionBar(toolBarisi);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//


        viewPager = findViewById(R.id.pager_pesanan);
        tab = findViewById(R.id.tab_pesanan);
        imgPesanan = findViewById(R.id.ivSearchPesanan);

        imgPesanan.setOnClickListener(this);

        adapter = new AdapterPesanan(getSupportFragmentManager(), 3);
        adapter.addFragment(new TabSemua(), "Semua");
        adapter.addFragment(new TabBelumBayar(), "Belum Bayar");
        adapter.addFragment(new Tabdikemas(), "Dikemas");
        adapter.addFragment(new Tabdikemas(), "Dikirim");
        adapter.addFragment(new Tabdikemas(), "Selesai");
        adapter.addFragment(new Tabdikemas(), "Dibatalkan");

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        TabAction();

    }

    private void TabAction() {
//        RecyclerPesananAdapter recyclerPesananAdapter = new RecyclerPesananAdapter(MyApp.getContext(), pesanan_models);


        TextView tab0 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabpesanan, null);
        tab0.setText("Semua");
        tab0.setTextSize(12);
        viewPager.setAdapter(adapter);
        tab.getTabAt(0).setCustomView(tab0);


        TextView tab1 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabpesanan, null);
        tab1.setText("Belum Bayar");
        tab1.setTextSize(12);
        tab.getTabAt(1).setCustomView(tab1);

        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabpesanan, null);
        tab2.setText("Dikemas");
        tab2.setTextSize(12);
        tab.getTabAt(2).setCustomView(tab2);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabpesanan, null);
        tab3.setText("Dikirim");
        tab3.setTextSize(12);
        tab.getTabAt(3).setCustomView(tab3);

        TextView tab4 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabpesanan, null);
        tab4.setText("Selesai");
        tab4.setTextSize(12);
        tab.getTabAt(4).setCustomView(tab4);

        TextView tab5 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabpesanan, null);
        tab5.setText("Dibatalkan");
        tab5.setTextSize(12);
        tab.getTabAt(5).setCustomView(tab5);


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