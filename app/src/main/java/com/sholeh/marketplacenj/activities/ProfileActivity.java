package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResProfil;


import java.util.List;

import retrofit2.Call;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView nav_home, nav_notifikasi, nav_transaksi, navprofile;
    TextView tvx_login, tvx_namaCustomter, tvx_logout;

    FloatingActionButton fb_favourite;
    Toolbar toolBarisi;

    SharedPreferences preferences;
    SharedPreferences.Editor input;
    boolean status = false;
    String id_konsumen, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("App", Context.MODE_PRIVATE);


        String id = preferences.getString(CONSTANTS.ID_KONSUMEN, null);

        Toast.makeText(this, "idNYA: "+id , Toast.LENGTH_SHORT).show();




        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Akun");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        tvx_namaCustomter = findViewById(R.id.tvCustomerName);



//        id_konsumen =  preferences.getString("id_konsume",);
//        username = SharePreferenceUtils.getInstance().getString(CONSTANTS.USER_NAME);
//


//        Toast.makeText(this, "id_konsumen"+id_konsumen + "USER "+username, Toast.LENGTH_SHORT).show();

//        tvx_login = findViewById(R.id.tvLogIn);
//        tvx_login.setOnClickListener(this);
//
//        tvx_namaCustomter = findViewById(R.id.tvCustomerNama);
//        tvx_logout = findViewById(R.id.tvLogout);
//        tvx_logout.setOnClickListener(this);


        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("AKUN PEMBELI"));

        tabLayout.addTab(tabLayout.newTab().setText("AKUN PELAPAK"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);

        PagerAdapterAkun adapter = new PagerAdapterAkun(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.nav_home:
                input = preferences.edit();
                input.putBoolean("aktif", true);
                input.commit();
                Intent i = new Intent(this, MainActivity.class);
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

//            case R.id.tvLogIn:
//                Intent pindah = new Intent(this, LoginActivity.class);
//                startActivity(pindah);
//
//                break;


            default:
                break;
        }
    }

    public void tampilProfi(){
//
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<ResProfil> call = service.TampilDataProfil(id_konsumen);
    }
}



