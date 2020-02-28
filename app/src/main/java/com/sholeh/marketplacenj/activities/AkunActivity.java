package com.sholeh.marketplacenj.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkunActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_username, ed_nama, ed_nohp, ed_email;
    Button btn_UpdateProfil;

    private ResProfil tvDataProfil;
    Preferences preferences;
    String id_konsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();


        ed_username = findViewById(R.id.edUsername);
        ed_nama = findViewById(R.id.edNama);
        ed_nohp = findViewById(R.id.edNohp);
        ed_email = findViewById(R.id.edEmail);

        btn_UpdateProfil = findViewById(R.id.btnUpdateProfil);
        btn_UpdateProfil.setOnClickListener(this);
        getDataProfi();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdateProfil:
               updateProfil();
                break;

                default:
                    break;
        }
    }

    public void getDataProfi(){

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);

        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {


                tvDataProfil = response.body();

                String userName = tvDataProfil.getData().getUsername();
                String namaLengkap = tvDataProfil.getData().getNamaLengkap();
                String noHP = tvDataProfil.getData().getNomorHp();
                String email = tvDataProfil.getData().getEmail();
                ed_username.setText(userName);
                ed_nama.setText(namaLengkap);
                ed_nohp.setText(noHP);
                ed_email.setText(email);

            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                Toast.makeText(AkunActivity.this, "no connection"+t, Toast.LENGTH_LONG).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }


    public void updateProfil() {

//        if (!NetworkUtility.isNetworkConnected(UbahPassword.this)){
//            AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.network_not_connected));
//
//        }else {
//            if (!validasi()) return;
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResRegristasi> call = service.updateKonsumen(id_konsumen,ed_nama.getText().toString(), ed_nohp.getText().toString(), ed_email.getText().toString(),"aktif" );

            call.enqueue(new Callback<ResRegristasi>() {
                @Override
                public void onResponse(Call<ResRegristasi> call, Response<ResRegristasi> response) {


                    if (response.body()!= null && response.isSuccessful()){ // true
                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")){
                            Toast.makeText(AkunActivity.this, "Berhasil diperbarui", Toast.LENGTH_LONG).show();
                            finish();
////
////
                        }else {
                            Toast.makeText(AkunActivity.this, "Gagal diperbarui", Toast.LENGTH_LONG).show();

//                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
                        }
                    }else {
                        Toast.makeText(AkunActivity.this, "Gagal diperbarui", Toast.LENGTH_LONG).show();

//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));

                    }
                }

                @Override
                public void onFailure(Call<ResRegristasi> call, Throwable t) {
                    Toast.makeText(AkunActivity.this, "Gagal diperbarui"+t, Toast.LENGTH_LONG).show();

                    //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
                }
            });
//        }
    }
}
