package com.sholeh.marketplacenj.activities.transaksi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.details.ViewPagerAdapter;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class MetodePembayaranActivity extends AppCompatActivity {

    String id_keranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);

        ArrayList<String> idYOlo = (ArrayList<String>) getIntent().getSerializableExtra("yolo");

        Toast.makeText(this,"idkeranjang "+idYOlo.get(0), Toast.LENGTH_SHORT).show();

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.ubahStatusKeranjang("1", (ArrayList<String>) idYOlo.get(0));

        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, Response<ResDetailKeranjang> response) {
                Toast.makeText(getApplicationContext(),"sukses", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResDetailKeranjang> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
