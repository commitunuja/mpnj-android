package com.sholeh.marketplacenj.activities.kurir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AddAlamat;
import com.sholeh.marketplacenj.adapter.adapterspin;
import com.sholeh.marketplacenj.model.cost.ItemCost;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.respon.RestCost;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.ServiceGenerator;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_pengiriman);

        spinnerKurir.add("JNE");
        spinnerKurir.add("J&T");

//        vid_produk = getIntent().getStringExtra("id_produk");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerKurir);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPilihKurir  = findViewById(R.id.spin_pilihKurir);
        spinPilihKurir.setAdapter(adapter);

        spinPilihKurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              hitungOngkir();
//                if (spinProvinsi.getSelectedItem().equals("Pilih Provinsi")) {
//
//
//                } else {
//                    getCity(listID_prov.get(position));
//                    namaProvinsi = spinProvinsi.getSelectedItem().toString();
//
//
//                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void hitungOngkir(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemCost> callOngkir= service.hitungOngkir("501","city","574","subdistrict",1700,"jne");
        callOngkir.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {

                Log.d("ongkirr", String.valueOf(response));



//                    Toast.makeText(OpsiPengirimanActivity.this, "rr"+response.body().getPesan(), Toast.LENGTH_SHORT).show();

//                        AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));

            }

            @Override
            public void onFailure(Call<ItemCost> call, Throwable t) {
//                    Log.e(TAG, " failure " + t.toString());
                Toast.makeText(OpsiPengirimanActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
            }
        });
    }



//    public void getProvince() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIInterface service = retrofit.create(APIInterface.class);
//        Call<ItemProvince> call = service.getProvince();
//
//        call.enqueue(new Callback<ItemProvince>() {
//            @Override
//            public void onResponse(Call<ItemProvince> call, Response<ItemProvince> response) {
//
//                Log.v("wow", "json : " + new Gson().toJson(response));
//
//                if (response.isSuccessful()) {
//
//                    int count_data = response.body().getRajaongkir().getResults().size();
//                    for (int a = 0; a <= count_data - 1; a++) {
//
//                        arrayProv.add(response.body().getRajaongkir().getResults().get(a).getProvince());
//                        listID_prov.add(response.body().getRajaongkir().getResults().get(a).getProvinceId());
//
//
//                    }
//                    adapterspinner = new adapterspin(AddAlamat.this, R.layout.support_simple_spinner_dropdown_item);
//                    adapterspinner.addAll(arrayProv);
//                    adapterspinner.add("Pilih Provinsi");
//                    spinProvinsi.setAdapter(adapterspinner);
//                    spinProvinsi.setSelection(adapterspinner.getCount());
//                } else {
//                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(AddAlamat.this, error, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ItemProvince> call, Throwable t) {
//
//                Toast.makeText(AddAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}