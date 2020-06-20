package com.sholeh.marketplacenj.activities.kurir;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.model.cost.ItemCost;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpsiPengirimanActivity extends AppCompatActivity {

    Spinner spinPilihKurir;
    List<String> spinnerKurir =  new ArrayList<String>();

    RadioGroup radioGroupKurir;
    ProgressBar progressBar;
    String idkec_pembeli, idkab_toko;
    ImageView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_pengiriman);
//        setTheme(android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

        spinnerKurir.add("JNE");
        spinnerKurir.add("JNT");
        spinnerKurir.add("POS");
        spinnerKurir.add("TIKI");
        spinnerKurir.add("SICEPAT");
        spinnerKurir.add("NINJA");

        progressBar = findViewById(R.id.progressBar1);

        radioGroupKurir = findViewById(R.id.kurir);

        clear = findViewById(R.id.img_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lagi = new Intent(OpsiPengirimanActivity.this, CheckoutActivity.class);
                startActivity(lagi);

            }
        });

        idkec_pembeli = getIntent().getStringExtra("idkec_pembeli");
        idkab_toko = getIntent().getStringExtra("idkab_toko");

        Toast.makeText(this, "idkec "+idkec_pembeli+ " idkab"+idkab_toko, Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerKurir);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPilihKurir  = findViewById(R.id.spin_pilihKurir);
        spinPilihKurir.setAdapter(adapter);

        spinPilihKurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                progressBar.setVisibility(View.VISIBLE);
                radioGroupKurir.removeAllViews();
                String kurir = spinPilihKurir.getSelectedItem().toString().toLowerCase();
                hitungOngkir(kurir);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void hitungOngkir(String kurir){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemCost> callOngkir= service.hitungOngkir("501","city","574","subdistrict",1700,kurir);
        callOngkir.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {

                for(int i =0; i < response.body().getRajaongkir().getResults().get(0).getCosts().size();i++)
                {
                    RadioButton radioButtonView = new RadioButton(OpsiPengirimanActivity.this);
                    radioButtonView.setText(
                            response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getService() + " - " +
                            response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getCost().get(0).getValue() + " - " +
                                    response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getCost().get(0).getEtd() + " hari");
                    radioGroupKurir.addView(radioButtonView);
                }

                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ItemCost> call, Throwable t) {
//                    Log.e(TAG, " failure " + t.toString());
                Toast.makeText(OpsiPengirimanActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
            }
        });
    }

}