package com.sholeh.marketplacenj.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukAllActivity extends AppCompatActivity {

     RecyclerView rv_populer, rv_searchproduk;
     SearchAdapter produkAdapter;
     List<Model> tvDataProduk;
    private ProgressBar progressBar;

    SearchAdapter searchAdapter;
    List<Model> datapencarian;
//    EditText edpencarian;

    EditText search;
    String status, allproduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_all);
        rv_populer = (RecyclerView) findViewById(R.id.rv_produkterpopuler);
        progressBar = findViewById(R.id.progressBar);
        Intent i = getIntent();
        allproduk = i.getStringExtra("all");
        search = findViewById(R.id.etsearchterpopuler);
        if (allproduk.equalsIgnoreCase("alldiskon")){
             produkDiskon();
        }else if (allproduk.equalsIgnoreCase("allterbaru")){
            produkTerbaru();
        }else if (allproduk.equalsIgnoreCase("allterlaris")){
             produkTerlaris();
        }else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini.", Toast.LENGTH_LONG).show();
        }

        pencariandata();
    }

    private void pencariandata() {

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                rv_populer.setVisibility(View.VISIBLE);
                if (allproduk.equalsIgnoreCase("alldiskon")){
                    produkDiskon();
                }else if (allproduk.equalsIgnoreCase("allterbaru")){

                }else if (allproduk.equalsIgnoreCase("allterlaris")){
                    produkTerlaris();
                }else{
                    Toast.makeText(getBaseContext(), "Tidak ada", Toast.LENGTH_LONG).show();
                }

//                ProdukTerbaruActivity.this.filterQuery(s.toString());
//                status = "yes";


            }
        });
    }


    public void filterQuery(String text) {
        ArrayList<Model> filter = new ArrayList<>();
        for (Model s : this.datapencarian) {
            if (s.getNamaProduk().toLowerCase().contains(text) || s.getKeterangan().toLowerCase().contains(text)) {
                filter.add(s);
            }
        }
        this.searchAdapter.setFilter(filter);
    }

    private void produkDiskon() {
        searchAdapter = new SearchAdapter(ProdukAllActivity.this, datapencarian);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProdukAllActivity.this, 2);
        rv_populer.setLayoutManager(layoutManager);
        rv_populer.setItemAnimator(new DefaultItemAnimator());
        rv_populer.setNestedScrollingEnabled(false);
        rv_populer.setFocusableInTouchMode(false);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProdukDiskon();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        datapencarian = response.body();
                        searchAdapter = new SearchAdapter(getBaseContext(), datapencarian);
                        rv_populer.setAdapter(searchAdapter);
                        progressBar.setVisibility(View.GONE);
                    }else {

                     progressBar.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplication(), "Tidak ada data pada kategori ini", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getApplication(), "Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void produkTerbaru() {
        searchAdapter = new SearchAdapter(ProdukAllActivity.this, datapencarian);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProdukAllActivity.this, 2);
        rv_populer.setLayoutManager(layoutManager);
        rv_populer.setItemAnimator(new DefaultItemAnimator());
        rv_populer.setNestedScrollingEnabled(false);
        rv_populer.setFocusableInTouchMode(false);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        datapencarian = response.body();
                        searchAdapter = new SearchAdapter(getBaseContext(), datapencarian);
                        rv_populer.setAdapter(searchAdapter);
                        progressBar.setVisibility(View.GONE);
                    }else {

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplication(), "Tidak ada data pada kategori ini", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getApplication(), "Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void produkTerlaris() {
        searchAdapter = new SearchAdapter(ProdukAllActivity.this, datapencarian);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProdukAllActivity.this, 2);
        rv_populer.setLayoutManager(layoutManager);
        rv_populer.setItemAnimator(new DefaultItemAnimator());
        rv_populer.setNestedScrollingEnabled(false);
        rv_populer.setFocusableInTouchMode(false);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProdukTerpopuler();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        datapencarian = response.body();
                        searchAdapter = new SearchAdapter(getBaseContext(), datapencarian);
                        rv_populer.setAdapter(searchAdapter);
                        progressBar.setVisibility(View.GONE);
                    }else {

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplication(), "Tidak ada data pada kategori ini", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getApplication(), "Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
            }
        });
    }

}