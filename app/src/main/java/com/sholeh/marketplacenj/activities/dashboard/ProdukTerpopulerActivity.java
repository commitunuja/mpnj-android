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

     RecyclerView rv_populer, rv_searchproduk;
     SearchAdapter produkAdapter;
     List<Model> tvDataProduk;
    private ProgressBar progressBar;

    SearchAdapter searchAdapter;
    List<Model> datapencarian;
//    EditText edpencarian;

    EditText search;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_terpopuler);
        rv_populer = (RecyclerView) findViewById(R.id.rv_produkterpopuler);
        progressBar = findViewById(R.id.progressBar);


        search = findViewById(R.id.etsearchterpopuler);


        produksearch();
        pencariandata();

        produkAdapter = new SearchAdapter(ProdukTerpopulerActivity.this, tvDataProduk);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProdukTerpopulerActivity.this, 2);
        rv_populer.setLayoutManager(layoutManager);
        rv_populer.setItemAnimator(new DefaultItemAnimator());
        rv_populer.setNestedScrollingEnabled(false);
        rv_populer.setFocusableInTouchMode(false);

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body().size() > 0) {
                    progressBar.setVisibility(View.GONE);
                    tvDataProduk = response.body();
                    produkAdapter = new SearchAdapter(getBaseContext(), tvDataProduk);
                    rv_populer.setAdapter(produkAdapter);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
}