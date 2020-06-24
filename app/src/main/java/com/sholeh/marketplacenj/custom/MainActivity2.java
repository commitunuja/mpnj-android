package com.sholeh.marketplacenj.custom;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.dashboard.SearchAdapter;
import com.sholeh.marketplacenj.model.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity
        implements MaterialSearchBar.OnSearchActionListener {
    MaterialSearchBar searchBar;

    String status;
     SearchAdapter searchAdapter;
     List<Model> datapencarian2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.inflateMenu(R.menu.main);
        searchBar.setText("Hello World!");
        Log.d("LOG_TAG", getClass().getSimpleName() + ": text " + searchBar.getText());
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {
//                MainActivity2.this.filterQuery(s.toString());
                status = "yes";

            }

        });


//        final FloatingActionButton searchButton = findViewById(R.id.searchButton);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchBar.openSearch();
//                Toast.makeText(MainActivity2.this, "sukses", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    public void filterQuery(String text) {
//        ArrayList<Model> filterdNames = new ArrayList<>();
//        for (Model s : this.datapencarian2) {
//            if (s.getNamaProduk().toLowerCase().contains(text)) {
//                filterdNames.add(s);
//            }
//        }
////        this.searchAdapter.setFilter2(filterdNames);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    @Override
//    public void onSearchStateChanged(boolean enabled) {
//    }
//
//    @Override
//    public void onSearchConfirmed(CharSequence text) {
//
//    }
//
//    @Override
//    public void onButtonClicked(int buttonCode) {
////        switch (buttonCode) {
////            case MaterialSearchBar.BUTTON_NAVIGATION:
////                drawer.openDrawer(GravityCompat.START);
////                break;
////            case MaterialSearchBar.BUTTON_SPEECH:
////                break;
////            case MaterialSearchBar.BUTTON_BACK:
//////                searchBar.closeSearch();
////                break;
////        }
//    }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}
