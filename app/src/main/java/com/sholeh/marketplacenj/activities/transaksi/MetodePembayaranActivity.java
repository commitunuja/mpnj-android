package com.sholeh.marketplacenj.activities.transaksi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.transaksi.MetodePembayaran;
import com.sholeh.marketplacenj.model.transaksi.Pembayaran;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void getMetodePembayaran() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<Pembayaran> call = service.metodepembayaran(String.valueOf(1592840324));

        call.enqueue(new Callback<Pembayaran>() {
            @Override
            public void onResponse(Call<Pembayaran> call, Response<Pembayaran> response) {
                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.body() == null) {

                } else {
                    List<MetodePembayaran> metodePembayarans = response.body().getData();
//                    metodePembayarans.get(0).getTotalBayar();


                    Toast.makeText(getBaseContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
                    nomor.setText(metodePembayarans.get(0).getKodeTransaksi().toString());
                    subtotal.setText(metodePembayarans.get(0).getTotalBayar().toString());
                }
            }

            @Override
            public void onFailure(Call<Pembayaran> call, Throwable t) {
//                Toast.makeText(KeranjangDetailActivity.this, "e"+t, Toast.LENGTH_SHORT).show();
                //  Log.e(TAG, "  fail- add to cart item "+ t.toString());
//                AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.fail_toGetcart));
                Log.d("cekkk", String.valueOf(t));
            }
        });
//        }
    }
}
