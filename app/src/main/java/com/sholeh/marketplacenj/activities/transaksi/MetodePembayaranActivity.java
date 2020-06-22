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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class MetodePembayaranActivity extends AppCompatActivity {

    String id_keranjang, total;
    TextView subtotal, ongkir, waktu, batas, nomor;
    List<MetodePembayaran> metodePembayaran;
    List<Pembayaran> pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);

        subtotal = findViewById(R.id.tv_totalbayar);
        ongkir = findViewById(R.id.tv_ongkir);
        waktu = findViewById(R.id.tv_waktu);
        batas = findViewById(R.id.tv_batas);
        nomor = findViewById(R.id.tv_nomortransaksi);

        Intent i = getIntent();
        total = i.getStringExtra("total");

//        subtotal.setText(total);
        getMetodePembayaran();
    }
}
