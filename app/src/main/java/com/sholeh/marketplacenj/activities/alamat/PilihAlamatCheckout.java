package com.sholeh.marketplacenj.activities.alamat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.activities.AddAlamat;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.adapter.alamat.AddressCheckoutAdapter;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihAlamatCheckout extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolBarisi;
    private FloatingActionButton fab_addAlamat;
    private String TAG = "PilihAlamatCheckout";

    Preferences preferences;
    String cekOngkir;


    private AddressCheckoutAdapter alamatAdapter;
    private ArrayList<AlamatModel> modellist = new ArrayList<>();
    RecyclerView recyclerAlamat;

    private KProgressHUD progressDialogHud;
    LinearLayout lnKosong;

    ResDetailKeranjang resDetailKeranjang;
    ArrayList<String> arrayIdKeranjang;
    String idkk;
    String id_konsumen, idkeranjang;
    List<String> list;
    ArrayList<String> idK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        progressDialogHud = KProgressHUD.create(PilihAlamatCheckout.this);
        lnKosong = findViewById(R.id.lnKosong);

        fab_addAlamat = findViewById(R.id.fab_alamat);
        recyclerAlamat = findViewById(R.id.recycler_alamat);
        fab_addAlamat.setOnClickListener(this);

        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Pilih Alamat");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager mLayoutManger3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerAlamat.setLayoutManager(mLayoutManger3);
        recyclerAlamat.setItemAnimator(new DefaultItemAnimator());
        alamatAdapter = new AddressCheckoutAdapter(PilihAlamatCheckout.this, modellist);
        recyclerAlamat.setAdapter(alamatAdapter);



        Intent i = getIntent();
        idK = i.getStringArrayListExtra("idcheckout");
        cekOngkir = i.getStringExtra("cekongkir");
//        Toast.makeText(this, "c "+cekOngkir, Toast.LENGTH_SHORT).show();
//        idK = getIntent().getStringExtra("id_checkout");
//        Toast.makeText(this, ""+idK, Toast.LENGTH_SHORT).show();
        getAlamat();

//        LocalBroadcastManager.getInstance(this).registerReceiver(receiveridkeranjang,
//                new IntentFilter("custom-idk"));

    }

    private void ProgresDialog(){
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Loading...")
                .setCancellable(false);
        progressDialogHud.show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public String getOngkir() {

        return cekOngkir;
    }
    public ArrayList<String> listIdKeranjang() {
        return idK;
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
        if (!NetworkUtility.isNetworkConnected(PilihAlamatCheckout.this)){
            AppUtilits.displayMessage(PilihAlamatCheckout.this,  getString(R.string.network_not_connected));
        }else {
            ProgresDialog();

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
                                            response.body().getData().getDaftarAlamat().get(i).getKecamatanId(),
                                            response.body().getData().getDaftarAlamat().get(i).getNamaKecamatan(),
                                            response.body().getData().getDaftarAlamat().get(i).getNamaKota(),
                                            response.body().getData().getDaftarAlamat().get(i).getNamaProvinsi(),
                                            response.body().getData().getDaftarAlamat().get(i).getKodePos(),
                                            response.body().getData().getDaftarAlamat().get(i).getStatus()));
                                }

                                alamatAdapter.notifyDataSetChanged();
                            recyclerAlamat.setVisibility(View.VISIBLE);
//                            lnKosong.setVisibility(View.GONE);
                                progressDialogHud.dismiss();

                            }  else {
                            Toast.makeText(PilihAlamatCheckout.this, "Data Belum Ada", Toast.LENGTH_SHORT).show();
                            recyclerAlamat.setVisibility(View.GONE);
//                            ln_kosong.setVisibility(View.VISIBLE);
                            progressDialogHud.dismiss();
                        }

                        } else {
                            AppUtilits.displayMessage(PilihAlamatCheckout.this, response.body().getPesan() );
                            recyclerAlamat.setVisibility(View.GONE);
                            lnKosong.setVisibility(View.VISIBLE);
                            progressDialogHud.dismiss();
                        }
                    } else {
                        AppUtilits.displayMessage(PilihAlamatCheckout.this, getString(R.string.network_error));
                        recyclerAlamat.setVisibility(View.GONE);
                        lnKosong.setVisibility(View.VISIBLE);
                        progressDialogHud.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ResProfil> call, Throwable t) {
                    AppUtilits.displayMessage(PilihAlamatCheckout.this, getString(R.string.fail_togetaddress));
                    recyclerAlamat.setVisibility(View.GONE);
                    lnKosong.setVisibility(View.VISIBLE);
                    progressDialogHud.dismiss();

                }
            });


        }
    }

//    private BroadcastReceiver receiveridkeranjang = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            idkeranjang = intent.getStringExtra("idkeranjang");
//            Log.d("idk pilihalamat", String.valueOf(idkeranjang));
//            String[] nomor = idkeranjang.split("\\[");
//            String[] nomor2 = nomor[1].split("]");
//            String harIDK = "";
//
//            for (int i = 0; i < nomor2.length; i++) {
//                harIDK = harIDK + nomor2[i];
//            }
//            idkk = harIDK;
//            String[] yolo = idkk.split(",");
//            list = new ArrayList<String>();
//            list = Arrays.asList(yolo);
//        }
//    };





}
