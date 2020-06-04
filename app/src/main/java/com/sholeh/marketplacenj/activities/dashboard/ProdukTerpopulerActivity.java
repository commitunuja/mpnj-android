package com.sholeh.marketplacenj.activities.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.dashboard.SearchAdapter;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukTerpopulerActivity extends AppCompatActivity {

    private RecyclerView rv_populer;
    private SearchAdapter produkAdapter;
    private List<Model> tvDataProduk;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_terpopuler);
        rv_populer = (RecyclerView) findViewById(R.id.rv_produkterpopuler);
        progressBar = findViewById(R.id.progressBar);
        produkAdapter = new SearchAdapter(ProdukTerpopulerActivity.this, tvDataProduk);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProdukTerpopulerActivity.this, 2);
        rv_populer.setLayoutManager(layoutManager);
        rv_populer.setItemAnimator(new DefaultItemAnimator());
        rv_populer.setNestedScrollingEnabled(false);
        rv_populer.setFocusableInTouchMode(false);
    }
}