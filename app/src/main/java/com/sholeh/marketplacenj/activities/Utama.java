package com.sholeh.marketplacenj.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.mfragment.FragmentFavorite;
import com.sholeh.marketplacenj.mfragment.FragmentNotifikasi;
import com.sholeh.marketplacenj.mfragment.FragmentProfil;
import com.sholeh.marketplacenj.mfragment.FragmentTransaksi;
import com.sholeh.marketplacenj.mfragment.Keranjang.KeranjangFragment;
import com.sholeh.marketplacenj.mfragment.homepage.HomepageFragment;
import com.sholeh.marketplacenj.util.Preferences;

public class Utama extends AppCompatActivity implements BottomAppBar.OnClickListener {

    BottomAppBar bottomNavigation;

    private ImageView nav_home, nav_notifikasi, nav_transaksi, nav_profile;
    FloatingActionButton fb_favourite;

    Toolbar toolBarisi;
    AppBarLayout appBarLayout;

    Preferences preferences;
    String id_konsumen;
    boolean doubleBackToExitPressedOnce = false;
    private Object Homepage;


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

        Homepage = new Homepage();

        perizinan();
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
                Intent intent = new Intent(this, Homepage.class);
                startActivity(intent);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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


    private void perizinan() {
        ActivityCompat.requestPermissions(Utama.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                99);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 99: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    perizinan();
                }
                return;
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
