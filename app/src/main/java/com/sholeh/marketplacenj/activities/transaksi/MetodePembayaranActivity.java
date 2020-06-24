package com.sholeh.marketplacenj.activities.transaksi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.details.ViewPagerAdapter;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class MetodePembayaranActivity extends AppCompatActivity {

    String total;
    TextView tvxtotalbayar;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, stsub, sttotal;

    Preferences preferences;
    String id_konsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();


        tvxtotalbayar = findViewById(R.id.tv_totalbayar);
//        total = getIntent().getStringExtra("totalbayar");
//        total = getIntent().getExtras().getString("totalbayar");

        Bundle b = getIntent().getExtras();
        double result = b.getDouble("Intent");
        sttotal = new StringTokenizer(formatRupiah.format(result), ",");
        String harganya = sttotal.nextToken().trim();
        tvxtotalbayar.setText(harganya);

    }
}
