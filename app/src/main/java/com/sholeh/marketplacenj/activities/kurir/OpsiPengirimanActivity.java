package com.sholeh.marketplacenj.activities.kurir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.model.cost.Cost;
import com.sholeh.marketplacenj.model.cost.ItemCost;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpsiPengirimanActivity extends AppCompatActivity {

    Spinner spinPilihKurir;
    List<String> spinnerKurir = new ArrayList<String>();

    RadioGroup radioGroupKurir;
    ProgressBar progressBar;
    String origin, destination, originType, destinationType, weight, idkec_pembeli;
    //    List<Cost_> cost_s = new ArrayList<>();
    List<Cost> costs = new ArrayList<>();
    TextView tvSave, tvidkecPembeli;
    Integer ongkir;
    String idkab_toko;
    ImageView clear;
    ArrayList<String> arrayIdKeranjang;
    ArrayList<String> arrayIdByParent;
    int idx;
    String idkk;
    String newidKecCheckoout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_pengiriman);

        tvidkecPembeli = findViewById(R.id.tvxidkecpembeli);
//        setTheme(android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
//        getWindow().setLayout((int)(width*.8), (int)(height*.6)); //ATUR

        Intent i = getIntent();
        arrayIdKeranjang = i.getStringArrayListExtra("idcheckout");
        arrayIdByParent = i.getStringArrayListExtra("idByParent");
        Log.d("YOLO", String.valueOf(arrayIdKeranjang));

        spinnerKurir.add("JNE");
        spinnerKurir.add("JNT");
        spinnerKurir.add("POS");
        spinnerKurir.add("TIKI");
        spinnerKurir.add("SICEPAT");
        spinnerKurir.add("NINJA");

        tvSave = findViewById(R.id.tvSave);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanKurir();
            }
        });

        progressBar = findViewById(R.id.progressBar1);

        radioGroupKurir = findViewById(R.id.kurir);
        radioGroupKurir.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioId = radioGroupKurir.getCheckedRadioButtonId();
                View radioBtn = radioGroupKurir.findViewById(radioId);
                idx = radioGroupKurir.indexOfChild(radioBtn);
//                Toast.makeText(OpsiPengirimanActivity.this, String.valueOf(costs.get(idx).getCost().get(0).getValue()), Toast.LENGTH_LONG).show();
//                ongkir = costs.get(idx).getCost().get(0).getValue();
            }
        });


//        if(newidKecCheckoout !=null){
//            tvidkecPembeli.setText(String.valueOf(newidKecCheckoout));
//        }else{
//            tvidkecPembeli.setText(String.valueOf(destination));
//        }


        destination = getIntent().getStringExtra("idkec_pembeli");
        Log.d("idkec pembeli", String.valueOf(destination));
//        Toast.makeText(this, "idkec"+idkec_pembeli, Toast.LENGTH_SHORT).show();
        tvidkecPembeli.setText(String.valueOf(destination));
        origin = getIntent().getStringExtra("idkab_toko");
        weight = getIntent().getStringExtra("weight");
        Log.d("desntiasi", destination);
        Log.d("origin", origin);
        Log.d("weight", weight);


        clear = findViewById(R.id.img_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lagi = new Intent(OpsiPengirimanActivity.this, CheckoutActivity.class);
                lagi.putExtra("ongkir", String.valueOf(ongkir));
                lagi.putStringArrayListExtra("idcheckout", arrayIdKeranjang);
                startActivity(lagi);
                finish();
//                Toast.makeText(OpsiPengirimanActivity.this, "Pilih Opsi Pengiriman Produk Anda", Toast.LENGTH_SHORT).show();

            }
        });

        idkec_pembeli = getIntent().getStringExtra("idkec_pembeli");
        idkab_toko = getIntent().getStringExtra("idkab_toko");

//        Toast.makeText(this, "idkec "+idkec_pembeli+ " idkab"+idkab_toko, Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerKurir);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPilihKurir = findViewById(R.id.spin_pilihKurir);
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
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageNewIdKec,
//                new IntentFilter("customnewidkec-checkout"));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(OpsiPengirimanActivity.this, "Silahkan Pilih Opsi Pengiriman Produk Anda ", Toast.LENGTH_SHORT).show();
        // Do Here what ever you want do on back press;
    }

    private void simpanKurir() {
        String arrIdKeranjang = String.valueOf(arrayIdByParent);
        String[] nomor = arrIdKeranjang.split("\\[");
        String[] nomor2 = nomor[1].split("]");
        String harIDK = "";

        for (int j = 0; j < nomor2.length; j++) {
            harIDK = harIDK + nomor2[j];
        }
        idkk = harIDK;
        String[] yolo = idkk.split(",");
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(yolo);
//        Toast.makeText(this, "k"+spinPilihKurir.getSelectedItem().toString()+" service"+costs.get(idx).getService()+" cost"+ costs.get(idx).getCost().get(0).getValue() +" etd"+costs.get(idx).getCost().get(0).getEtd()+ " list"+list, Toast.LENGTH_SHORT).show();
        if (radioGroupKurir.getCheckedRadioButtonId() == -1) {
            Toast.makeText(OpsiPengirimanActivity.this, "Silahkan Pilih Opsi Pengiriman Produk Anda ", Toast.LENGTH_SHORT).show();
            // no radio buttons are checked
        } else {
//            Toast.makeText(OpsiPengirimanActivity.this, "2", Toast.LENGTH_SHORT).show();
            // one of the radio buttons is checked
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<JsonObject> call = service.simpan_kurir(spinPilihKurir.getSelectedItem().toString(), costs.get(idx).getService(), costs.get(idx).getCost().get(0).getValue(), costs.get(idx).getCost().get(0).getEtd(), list);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                Log.d("YOLO", String.valueOf(response.body()));
                    Intent i = new Intent(OpsiPengirimanActivity.this, CheckoutActivity.class);
                    i.putStringArrayListExtra("idcheckout", arrayIdKeranjang);
                    startActivity(i);
                    finish();

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("error", String.valueOf(t));
//                Toast.makeText(OpsiPengirimanActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                }
            });
        }
//
    }

    public void hitungOngkir(final String kurir) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        String newdestination = tvidkecPembeli.getText().toString();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemCost> callOngkir = service.hitungOngkir(origin, "city", destination, "subdistrict", Integer.parseInt(weight), kurir);
        callOngkir.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {
//                cost = (Cost) response.body().getRajaongkir().getResults().get(0).getCosts().get(0).getCost();
                costs.clear();
                for (int i = 0; i < response.body().getRajaongkir().getResults().get(0).getCosts().size(); i++) {
                    RadioButton radioButtonView = new RadioButton(OpsiPengirimanActivity.this);
                    radioButtonView.setText(
                            response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getService() + " - " +
                                    response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getCost().get(0).getValue() + " - " +
                                    response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getCost().get(0).getEtd() + " hari");
                    radioGroupKurir.addView(radioButtonView);

//                    Cost_ cost_ = response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getCost().get(0);
                    List<Cost> cost = response.body().getRajaongkir().getResults().get(0).getCosts();
                    costs.add(new Cost(cost.get(i).getService(), cost.get(i).getDescription(), cost.get(i).getCost()));
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ItemCost> call, Throwable t) {
//                    Log.e(TAG, " failure " + t.toString());
                Toast.makeText(OpsiPengirimanActivity.this, "rrr" + t, Toast.LENGTH_SHORT).show();


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
            }
        });
    }

//    public BroadcastReceiver mMessageNewIdKec = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            newidKecCheckoout = intent.getStringExtra("newidkec");
//            Toast.makeText(context, "idkec"+newidKecCheckoout, Toast.LENGTH_SHORT).show();
//            tvidkecPembeli.setText("sd "+newidKecCheckoout);
//        }
//    };

}