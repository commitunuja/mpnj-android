package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.AlamatAdapter;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolBarisi;
    private FloatingActionButton fab_addAlamat;
    private String TAG = "AlamatActivity";

    Preferences preferences;
    String id_konsumen;


    private AlamatAdapter alamatAdapter;
    private ArrayList<AlamatModel> modellist = new ArrayList<>();
    RecyclerView recyclerAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        fab_addAlamat = findViewById(R.id.fab_alamat);
        recyclerAlamat = findViewById(R.id.recycler_alamat);
        fab_addAlamat.setOnClickListener(this);

        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Alamat Saya");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager mLayoutManger3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerAlamat.setLayoutManager(mLayoutManger3);
        recyclerAlamat.setItemAnimator(new DefaultItemAnimator());
        alamatAdapter = new AlamatAdapter(AlamatActivity.this, modellist);
        recyclerAlamat.setAdapter(alamatAdapter);

        getAlamat();

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_alamat:
              startActivity(new Intent(this, AddAlamat.class));
                break;


            default:
                break;
        }
    }


    public void getAlamat() {
        if (!NetworkUtility.isNetworkConnected(AlamatActivity.this)){
            AppUtilits.displayMessage(AlamatActivity.this,  getString(R.string.network_not_connected));
        }else {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);
        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getPesan().equalsIgnoreCase("Sukses!!")) {

                        if (response.body().getData().getDaftarAlamat().size() > 0) {

                            modellist.clear();
                            for (int i = 0; i < response.body().getData().getDaftarAlamat().size(); i++) {

                                modellist.add(new AlamatModel(response.body().getData().getDaftarAlamat().get(i).getIdAlamat(),
                                        response.body().getData().getDaftarAlamat().get(i).getNama(),
                                        response.body().getData().getDaftarAlamat().get(i).getNomorTelepon(),
                                        response.body().getData().getDaftarAlamat().get(i).getAlamatLengkap(),
                                        response.body().getData().getDaftarAlamat().get(i).getNamaKecamatan(),
                                        response.body().getData().getDaftarAlamat().get(i).getNamaKota(),
                                        response.body().getData().getDaftarAlamat().get(i).getNamaProvinsi(),
                                        response.body().getData().getDaftarAlamat().get(i).getKodePos(),
                                        response.body().getData().getDaftarAlamat().get(i).getStatus()));
                            }

                            alamatAdapter.notifyDataSetChanged();

                        }
                    } else {
                            AppUtilits.displayMessage(AlamatActivity.this, response.body().getPesan() );
                    }
                } else {
                        AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.network_error));
                }

            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                    AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.fail_togetaddress));


            }
        });


        }
    }
}
