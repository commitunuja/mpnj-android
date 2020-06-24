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
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_store:
                fm.beginTransaction().hide(active).show(homeFragment).commit();
                active = homeFragment;
                return true;

            case R.id.navigation_favourite:
                Toast.makeText(this, "Ini Hanya View", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.navigation_camera:
                Toast.makeText(this, "Ini hanya View", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.navigation_cart:
                fm.beginTransaction().hide(active).show(keranjangFragment).commit();
                active = keranjangFragment;
                return true;

            case R.id.navigation_account:
                fm.beginTransaction().hide(active).show(fragmentProfil).commit();
                active = fragmentProfil;
                return true;

        }

        return false;
    }

}

