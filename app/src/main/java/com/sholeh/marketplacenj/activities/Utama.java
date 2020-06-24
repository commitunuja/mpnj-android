package com.sholeh.marketplacenj.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.mfragment.FragmentProfil;
import com.sholeh.marketplacenj.mfragment.Keranjang.KeranjangFragment;
import com.sholeh.marketplacenj.mfragment.homepage.HomepageFragment;
import com.sholeh.marketplacenj.util.Preferences;

public class Utama extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    AppBarLayout appBarLayout;
    Preferences preferences;
    String id_konsumen;
    boolean doubleBackToExitPressedOnce = false;
    Fragment fragment;

    final Fragment homeFragment = new HomepageFragment();
    final Fragment keranjangFragment = new KeranjangFragment();
    final Fragment fragmentProfil = new FragmentProfil();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        appBarLayout = findViewById(R.id.appBarToolbar);
        appBarLayout.setVisibility(View.GONE);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, homeFragment)
                    .commit();
        }

        fm.beginTransaction().add(R.id.nav_host_fragment, keranjangFragment, "2").hide(keranjangFragment).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragmentProfil, "1").hide(fragmentProfil).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

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

//
//
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

//    @Override
//    public void onClick(View v) {
//        Fragment fragment = null;
//
//        switch (v.getId()) {
//            case R.id.nav_home:
//                fragment = new HomepageFragment();
//                loadFragment(fragment);
//                break;
//            case R.id.nav_notifikasi:
//                fragment = new KeranjangFragment();
//                loadFragment(fragment);
//                break;
//
//            case R.id.fab_menu:
//                fragment = new FragmentFavorite();
//                loadFragment(fragment);
//                break;
//
//            case R.id.nav_transaksi:
//                fragment = new FragmentTransaksi();
//                loadFragment(fragment);
//                break;
//
//            case R.id.nav_profile:
//                boolean login = preferences.getSPSudahLogin();
//
//                if (login) {
//                    fragment = new FragmentProfil();
//                    loadFragment(fragment);
//                } else {
//                    startActivity(new Intent(this, LoginActivity.class));
//                    finish();
//                }
//
//
//                break;
//
//
//        }
//        loadFragment(fragment);
//
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


//    private boolean loadFragment(Fragment fragment) {
//        if (fragment != null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit();
//            return true;
//        }
//        return false;
//    }


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
        homepageFragment = new HomepageFragment();
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        FragmentManager fragmentManager;
        switch (item.getItemId()) {
            case R.id.navigation_store:


                if (homepageFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, homepageFragment, "fragHome").commit();
//                    Toast.makeText(getApplicationContext(), "Fragment Added",
//                            Toast.LENGTH_LONG).show();
                } else {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).add(R.id.container, homepageFragment, "fragHome").commit();
                }


            case R.id.navigation_favourite:
//                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, keranjangFragment).commit();
                Toast.makeText(this, "Ini Hanya View", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_camera:
//                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, fragmentProfil).commit();
                Toast.makeText(this, "Ini hanya View", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.navigation_cart:
                if (keranjangFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, keranjangFragment, "fragCart").commit();
//                    Toast.makeText(getApplicationContext(), "Fragment Added",
//                            Toast.LENGTH_LONG).show();
                } else {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).add(R.id.container, keranjangFragment, "fragCart").commit();
                }
//                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, keranjangFragment).commit();
                Toast.makeText(this, "Ini hanya View", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.navigation_account:
                if (fragmentProfil.isAdded()) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.container, fragmentProfil, "fragProf").commit();
                } else {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).add(R.id.container, fragmentProfil, "fragProf").commit();
                }
                return true;

        }

        return false;
//
    }

//    private void load(){
//        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener);
//    }
}

