package com.sholeh.marketplacenj.activities.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.dashboard.ProdukAdapter;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ProdukAdapter produkAdapter;
    List<Model> tvDataProduk;
    ArrayList<TopTenModelClass> topTenModelClasses;
//    RecyclerView top_ten_crecyclerview;
    RecycleAdapteTopTenHome mAdapter2;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        query  = getIntent().getExtras().getString("query");

        final RecyclerView recyclerView = findViewById(R.id.rvsearch);

        produkAdapter = new ProdukAdapter(SearchActivity.this, tvDataProduk);
        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        top_ten_crecyclerview.setLayoutManager(mLayoutManager4);


        produkAdapter = new ProdukAdapter(getBaseContext(), tvDataProduk);
        recyclerView.setAdapter(produkAdapter);

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>(){
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                Log.d("YOLO","coba" + response.body());
                tvDataProduk = response.body();
                produkAdapter = new ProdukAdapter(getBaseContext(), tvDataProduk);
                recyclerView.setAdapter(produkAdapter);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "ini"+valueOf(t), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
