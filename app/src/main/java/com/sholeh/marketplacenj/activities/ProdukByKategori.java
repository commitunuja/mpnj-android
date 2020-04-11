package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.dashboard.Homepage;
import com.sholeh.marketplacenj.adapter.ProdukByKategoriAdapter;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukByKategori extends AppCompatActivity {
    String id_kategori;
    private RecyclerView recyclerViewProdukByKategori;
    private List<Model> dataProdukByKategori;
    private ProdukByKategoriAdapter produkByKategoriAdapter;
    private ProgressBar progressBarProdukByKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_by_kategori);

        id_kategori = getIntent().getStringExtra("id_kategori");

        recyclerViewProdukByKategori = (RecyclerView) findViewById(R.id.recyclerprodukbykategori);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProdukByKategori.this, 2);
        recyclerViewProdukByKategori.setLayoutManager(layoutManager);
        recyclerViewProdukByKategori.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProdukByKategori.setNestedScrollingEnabled(false);
        recyclerViewProdukByKategori.setFocusableInTouchMode(false);

        progressBarProdukByKategori = (ProgressBar) findViewById(R.id.progressBarProdukByKategori);

        getDataProdukByKategori();
    }

    private void getDataProdukByKategori() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getKategoriByid(id_kategori);

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                if (response.body() == null) {
                    Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini.", Toast.LENGTH_SHORT).show();
                } else {
                    progressBarProdukByKategori.setVisibility(View.GONE);
                    dataProdukByKategori = response.body();
                    produkByKategoriAdapter = new ProdukByKategoriAdapter(getBaseContext(), dataProdukByKategori);
                    recyclerViewProdukByKategori.setAdapter(produkByKategoriAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
