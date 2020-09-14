package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kaopiz.kprogresshud.KProgressHUD;
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

    private KProgressHUD progressDialogHud;
    LinearLayout ln_kosong;
    TextView tvx_title;
    ImageView imgtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        imgtoolbar = findViewById(R.id.imgtoolbarF);
        tvx_title = findViewById(R.id.title);
        tvx_title.setText("Alamat Saya");
        tvx_title.setTextColor(getResources().getColor(R.color.white));
        tvx_title.setVisibility(View.VISIBLE);
        imgtoolbar.setOnClickListener(this);

        progressDialogHud = KProgressHUD.create(AlamatActivity.this);
        ln_kosong = findViewById(R.id.lnKosong);

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



    }
    @Override
    public void onResume()
    {
        getAlamat();

        super.onResume();
        // Load data and do stuff
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            YourItem passedItem = data.getExtras().get("passed_item");
//            // deal with the item yourself
//
//        }
//    }

    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Loading...")
                .setCancellable(false);
        progressDialogHud.show();
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
                Intent pindah = new Intent(this, AddAlamat.class);
                pindah.putExtra("alamat", "activity");
                startActivity(pindah);
//                finish();
//                Toast.makeText(this, "kll", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.imgtoolbarF:
                finish();
                break;


            default:
                break;
        }
    }


    public void getAlamat() {
        if (!NetworkUtility.isNetworkConnected(AlamatActivity.this)) {
//            AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.network_not_connected));
        } else {
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResProfil> call = service.getDataProfil(id_konsumen);
            call.enqueue(new Callback<ResProfil>() {
                @Override
                public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {

                    if (response.body() != null && response.isSuccessful()) {

                        if (response.body().getPesan().equalsIgnoreCase("Sukses!!")) {


                            Log.d("cekalamat", String.valueOf(response.body().getData().getDaftarAlamat().size()));
                            if (response.body().getData().getDaftarAlamat().size() > 0) {
                                modellist.clear();
                                for (int i = 0; i < response.body().getData().getDaftarAlamat().size(); i++) {
                                    modellist.add(new AlamatModel(response.body().getData().getDaftarAlamat().get(i).getIdAlamat(),
                                            response.body().getData().getDaftarAlamat().get(i).getNama(),
                                            response.body().getData().getDaftarAlamat().get(i).getNomorTelepon(),
                                            response.body().getData().getDaftarAlamat().get(i).getAlamatLengkap(),
                                            response.body().getData().getDaftarAlamat().get(i).getKecamatanId(),
                                            response.body().getData().getDaftarAlamat().get(i).getNamaKecamatan(),
                                            response.body().getData().getDaftarAlamat().get(i).getNamaKota(),
                                            response.body().getData().getDaftarAlamat().get(i).getNamaProvinsi(),
                                            response.body().getData().getDaftarAlamat().get(i).getKodePos(),
                                            response.body().getData().getDaftarAlamat().get(i).getStatus()));
                                }

                                alamatAdapter.notifyDataSetChanged();
                                recyclerAlamat.setVisibility(View.VISIBLE);
//                            ln_kosong.setVisibility(View.GONE);
                                progressDialogHud.dismiss();


                            } else {
//                                Toast.makeText(AlamatActivity.this, "Data Belum Ada", Toast.LENGTH_SHORT).show();
                                recyclerAlamat.setVisibility(View.GONE);
//                            ln_kosong.setVisibility(View.VISIBLE);
                                progressDialogHud.dismiss();
                            }
                        } else {
//                            AppUtilits.displayMessage(AlamatActivity.this, response.body().getPesan() );
                            recyclerAlamat.setVisibility(View.GONE);
                            ln_kosong.setVisibility(View.VISIBLE);
                            progressDialogHud.dismiss();
                        }
                    } else {
//                        AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.network_error));
                        recyclerAlamat.setVisibility(View.GONE);
                        ln_kosong.setVisibility(View.VISIBLE);
                        progressDialogHud.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ResProfil> call, Throwable t) {
//                    AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.fail_togetaddress));
                    recyclerAlamat.setVisibility(View.GONE);
                    ln_kosong.setVisibility(View.VISIBLE);
                    progressDialogHud.dismiss();


                }
            });
        }
    }
}
