package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPassword extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolBarisi;

    EditText edPasslama, edNewPass, edConfirmPass;
    TextView tvbatal, tvSimpan;


    private String TAG ="New_Password";

    Preferences preferences;
    String id_konsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();


        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Ubah Password");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edPasslama = findViewById(R.id.etPassSekarang);
        edNewPass = findViewById(R.id.etNewPassword);
        edConfirmPass = findViewById(R.id.etConfirrmNewPassword);
        tvbatal = findViewById(R.id.tvBatal);
        tvSimpan = findViewById(R.id.tvSave);

        tvSimpan.setOnClickListener(this);
        tvbatal.setOnClickListener(this);





    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSave:
                sendNewPasswordReq();
                break;

            case R.id.tvBatal:
                finish();
                break;

                default:
                    break;
        }
    }


    public void sendNewPasswordReq() {

        if (!NetworkUtility.isNetworkConnected(UbahPassword.this)){
            AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.network_not_connected));

        }else {
//            if (!validasi()) return;
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResNewPassword> call = service.KonsumenUbahPassword(id_konsumen,edPasslama.getText().toString(), edNewPass.getText().toString() );

            call.enqueue(new Callback<ResNewPassword>() {
                @Override
                public void onResponse(Call<ResNewPassword> call, Response<ResNewPassword> response) {


                    if (response.body()!= null && response.isSuccessful()){ // true

                        if (response.body().getPesan().equalsIgnoreCase("Berhasil Diganti")){

////
                            Toast.makeText(UbahPassword.this, "Password Berhasil di Rubah", Toast.LENGTH_LONG).show();
                            finish();
////
////
                        }else {
                            Toast.makeText(UbahPassword.this, "Password Sekarang Salah", Toast.LENGTH_LONG).show();

//                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
                           }
                    }else {
                        Toast.makeText(UbahPassword.this, "Password Gagal di Rubahh"+response.body(), Toast.LENGTH_LONG).show();

//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));


                    }
                }

                @Override
                public void onFailure(Call<ResNewPassword> call, Throwable t) {
                    Toast.makeText(UbahPassword.this, "Password Gagal di Rubahh"+t, Toast.LENGTH_LONG).show();

                    //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
                }
            });




        }




    }

    private boolean validasi() {
        boolean valid = true;
        final String passwordBaru = edNewPass.getText().toString();
        final String konpassword_ = edConfirmPass.getText().toString();


        if (passwordBaru.isEmpty()) {
            edNewPass.setError("password wajib di isi");
            Toast.makeText(UbahPassword.this, "password wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (konpassword_.isEmpty()) {
            edConfirmPass.setError("konfirmasi password wajib di isi");
            Toast.makeText(UbahPassword.this, "konfirmasi password wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;

        } else if (!passwordBaru.equals(konpassword_)) {
            Toast.makeText(UbahPassword.this, "kata sandi tidak cocok", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (edNewPass.length() < 6) {
            Toast.makeText(UbahPassword.this, "password minimal 6 digit", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            edNewPass.setError(null);
        }

        return valid;
    }

}
