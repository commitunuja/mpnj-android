package com.sholeh.marketplacenj.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.mfragment.FragmentFavorite;
import com.sholeh.marketplacenj.mfragment.FragmentHome;
import com.sholeh.marketplacenj.mfragment.FragmentNotifikasi;
import com.sholeh.marketplacenj.mfragment.FragmentProfil;
import com.sholeh.marketplacenj.mfragment.FragmentTransaksi;
import com.sholeh.marketplacenj.util.Preferences;

public class Utama extends AppCompatActivity implements  BottomAppBar.OnClickListener{

    BottomAppBar bottomNavigation;

    private ImageView nav_home, nav_notifikasi, nav_transaksi, nav_profile;
    FloatingActionButton fb_favourite;

    Toolbar toolBarisi;
    AppBarLayout appBarLayout;

    Preferences preferences;
    String id_konsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Akun");
        setSupportActionBar(toolBarisi);
        appBarLayout = findViewById(R.id.appBarToolbar);
        appBarLayout.setVisibility(View.GONE);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        bottomNavigation = findViewById(R.id.navigation_bottombar);
        bottomNavigation.setOnClickListener(this);
        nav_home = findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_notifikasi = findViewById(R.id.nav_notifikasi);
        nav_notifikasi.setOnClickListener(this);
        nav_transaksi = findViewById(R.id.nav_transaksi);
        nav_transaksi.setOnClickListener(this);
        nav_profile = findViewById(R.id.nav_profile);
        nav_profile.setOnClickListener(this);
        fb_favourite = findViewById(R.id.fab_menu);
        fb_favourite.setOnClickListener(this);

        loadFragment(new FragmentHome());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


//    @Override
//    public void onClick(View v) {
//
//
//        switch (v.getId()) {
//            case R.id.nav_transaksi:
////                new FragmentTransaksi();
//                Toast.makeText(this, "nav transaksi", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.fab_menu:
//                Toast.makeText(this, "nav fab", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.nav_notifikasi:
//                Toast.makeText(this, "nav notifikasi", Toast.LENGTH_SHORT).show();
////                new FragmentNotifikasi();
//                break;
//
//
//
//
//
//            default:
//                break;
//        }
//        return loadFragment(fragment);
//
//    }



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//
////        case R.id.nav_transaksi:
//////                new FragmentTransaksi();
////                Toast.makeText(this, "nav transaksi", Toast.LENGTH_SHORT).show();
////                break;
////
////            case R.id.fab_menu:
////                Toast.makeText(this, "nav fab", Toast.LENGTH_SHORT).show();
////                break;
////
////            case R.id.nav_notifikasi:
////                Toast.makeText(this, "nav notifikasi", Toast.LENGTH_SHORT).show();
//////                new FragmentNotifikasi();
////                break;
////
//        switch (item.getItemId()){
//            case R.id.nav_transaksi:
////                fragment = new FragmentHome();
//                Toast.makeText(this, "nav transaksi", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_notifikasi:
////                fragment = new FragmentFavourite();
//                Toast.makeText(this, "nav notifikasi", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return loadFragment(fragment);
//    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.nav_home:
                fragment =   new FragmentHome();
                loadFragment(fragment);
                break;

            case R.id.nav_notifikasi:
                fragment =   new FragmentNotifikasi();
                loadFragment(fragment);
                break;

            case R.id.fab_menu:
                fragment =   new FragmentFavorite();
                loadFragment(fragment);
                break;

            case R.id.nav_transaksi:
                fragment =   new FragmentTransaksi();
                loadFragment(fragment);
                break;

            case R.id.nav_profile:
                boolean login = preferences.getSPSudahLogin();

                if (login){
                    fragment =   new FragmentProfil();
                    loadFragment(fragment);
                }else{
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }


                break;



        }

    }



    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_utama, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
