package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlamat extends AppCompatActivity {

    public String alamatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);

        alamatId = getIntent().getExtras().getString("alamat_id");


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
