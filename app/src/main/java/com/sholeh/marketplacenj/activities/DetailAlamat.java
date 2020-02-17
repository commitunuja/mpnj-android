package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.respon.ResDetailAlamat;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlamat extends AppCompatActivity {

    public String alamatId, userType;
    EditText edNama, edNoHP, edProv, edKota, edKec, edKodePos, edAlamatLengkap;
    TextView tvx_hapus, tvx_simpan;
    Toolbar toolBarisi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);

        edNama = findViewById(R.id.ed_nama);
        edNoHP = findViewById(R.id.ed_nohp);
        edProv = findViewById(R.id.ed_prov);
        edKota = findViewById(R.id.ed_kota);
        edKec = findViewById(R.id.ed_kec);
        edKodePos = findViewById(R.id.ed_kodepos);
        edAlamatLengkap = findViewById(R.id.ed_alamat);
        tvx_simpan = findViewById(R.id.tvSave);
        tvx_hapus = findViewById(R.id.tvHapus);

        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Detail Alamat");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alamatId = getIntent().getExtras().getString("alamat_id");

        detailAlamat();


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    public void detailAlamat(){
//        if (!NetworkUtility.isNetworkConnected(AlamatActivity.this)){
//            AppUtilits.displayMessage(AlamatActivity.this,  getString(R.string.network_not_connected));
//        }else {

            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResDetailAlamat> call = service.getDetailAlamat(alamatId);
            call.enqueue(new Callback<ResDetailAlamat>() {
                @Override
                public void onResponse(Call<ResDetailAlamat> call, Response<ResDetailAlamat> response) {

//                    tvDetailAlamat = response.body();
//                    String nama = response.body().getData().getN;


                    if (response.body() != null && response.isSuccessful()) {
//
                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {

                            if (response.body().getData().size() > 0) {
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    edNama.setText(response.body().getData().get(i).getNama());
                                    edNoHP.setText(response.body().getData().get(i).getNomorTelepon());
                                    edProv.setText(response.body().getData().get(i).getNamaProvinsi());
                                    edKota.setText(response.body().getData().get(i).getNamaKota());
                                    edKodePos.setText(response.body().getData().get(i).getKodePos());
                                    edAlamatLengkap.setText(response.body().getData().get(i).getAlamatLengkap());


                                }

                            }

                        } else {
                            AppUtilits.displayMessage(DetailAlamat.this, response.body().getPesan() );
                        }
                    } else {
                        AppUtilits.displayMessage(DetailAlamat.this, getString(R.string.network_error));
                    }

                }

                @Override
                public void onFailure(Call<ResDetailAlamat> call, Throwable t) {
                    AppUtilits.displayMessage(DetailAlamat.this, getString(R.string.fail_togetaddress));
                }
            });


//        }

    }

    public void  ubahAlamat() {

//        if (!NetworkUtility.isNetworkConnected(UbahPassword.this)){
//            AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.network_not_connected));
//
//        }else {
////            if (!validasi()) return;
//            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//            Call<ResNewPassword> call = service.KonsumenUbahPassword(id_konsumen, edNewPass.getText().toString() );
//
//            call.enqueue(new Callback<ResNewPassword>() {
//                @Override
//                public void onResponse(Call<ResNewPassword> call, Response<ResNewPassword> response) {
//
//                    Log.d(TAG, "re"+ response.toString());
//                    if (response.body()!= null && response.isSuccessful()){
//                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")){
////
//                            Toast.makeText(UbahPassword.this, "Password Berhasil di Rubah", Toast.LENGTH_LONG).show();
//                            finish();
////
////
//                        }else {
//                            Toast.makeText(UbahPassword.this, "Password Gagal di Rubah"+response.body().getPesan(), Toast.LENGTH_LONG).show();
//
////                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
//                        }
//                    }else {
//                        Toast.makeText(UbahPassword.this, "Password Gagal di Rubahh"+response.body(), Toast.LENGTH_LONG).show();
//
////                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResNewPassword> call, Throwable t) {
//                    Toast.makeText(UbahPassword.this, "Password Gagal di Rubahh"+t, Toast.LENGTH_LONG).show();
//
//                    //  Log.e(TAG, " failure "+ t.toString());
////                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
//                }
//            });
//
//
//
//
//        }




    }
}
