package com.sholeh.marketplacenj.activities.pelapak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.adapter.profilpelapak.ProfilPelapakAdapter;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfilPelapakActivity extends AppCompatActivity {


    private TextView namapelapak, alamatpelapak, toolbar;
    ImageView fotopelapak, back;
    RecyclerView recprodukpelapak;
    String id_pelapak, pelapak, foto_pelapak;
    private ArrayList<TopTenModelClass> topTenModelClasses1;
    int pelapakId;
    private RecycleAdapteTopTenHome mAdapter3;


    private ProfilPelapakAdapter profilPelapakAdapter;
    private List<Model> tvDataProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pelapak);

        toolbar = findViewById(R.id.tvTitleglobal);
        back = findViewById(R.id.imgBackglobal);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilPelapakActivity.this, ProductDetailActivity.class);
                startActivity(intent);
            }
        });

        id_pelapak = getIntent().getStringExtra("id_user");
        pelapak = getIntent().getStringExtra("namapelapak");
        foto_pelapak = getIntent().getStringExtra("foto_pelapak");
        recprodukpelapak = findViewById(R.id.recyclerviewprodukpelapak);
//        iduser = getIntent().getStringExtra("id_user");
        toolbar.setText(pelapak);

        init();
        getDataProdukPelapak();
//        getIdPelapak();
    }

    private void getDataProdukPelapak() {
        profilPelapakAdapter = new ProfilPelapakAdapter(ProfilPelapakActivity.this, tvDataProduk);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProfilPelapakActivity.this, 2);
        recprodukpelapak.setLayoutManager(layoutManager);
        recprodukpelapak.setItemAnimator(new DefaultItemAnimator());
        recprodukpelapak.setNestedScrollingEnabled(false);
        recprodukpelapak.setFocusableInTouchMode(false);

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProdukPelapak(id_pelapak);

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                tvDataProduk = response.body();
                profilPelapakAdapter = new ProfilPelapakAdapter(getBaseContext(), tvDataProduk);
                recprodukpelapak.setAdapter(profilPelapakAdapter);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarhomepage);
        namapelapak = findViewById(R.id.tvnamapelapak);
        alamatpelapak = findViewById(R.id.tvnamapelapak);
        fotopelapak = findViewById(R.id.imgpelapak);


        Glide.with(getBaseContext())
                .load(foto_pelapak)
                .into(fotopelapak);

        namapelapak.setText(pelapak);

//
//        if (getSupportActionBar() != null) {
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
    }
}
