package com.sholeh.marketplacenj.activities.kurir;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AddAlamat;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.adapter.adapterspin;
import com.sholeh.marketplacenj.model.cost.Cost;
import com.sholeh.marketplacenj.model.cost.Cost_;
import com.sholeh.marketplacenj.model.cost.ItemCost;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.respon.RestCost;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import org.json.JSONArray;

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
    String origin, destination, originType, destinationType, weight;
//    List<Cost_> cost_s = new ArrayList<>();
    List<Cost> costs = new ArrayList<>();
    TextView tvSave;
    Integer ongkir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_pengiriman);

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
                Intent i = new Intent(OpsiPengirimanActivity.this, CheckoutActivity.class);
                i.putExtra("ongkir", String.valueOf(ongkir));
                startActivity(i);
            }
        });

        progressBar = findViewById(R.id.progressBar1);

        radioGroupKurir = findViewById(R.id.kurir);
        radioGroupKurir.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioId = radioGroupKurir.getCheckedRadioButtonId();
                View radioBtn = radioGroupKurir.findViewById(radioId);
                int idx = radioGroupKurir.indexOfChild(radioBtn);
//                Toast.makeText(OpsiPengirimanActivity.this, String.valueOf(costs.get(idx).getCost().get(idx).getValue()), Toast.LENGTH_LONG).show();
                ongkir = costs.get(idx).getCost().get(0).getValue();
            }
        });

        destination = getIntent().getStringExtra("idkec_pembeli");
        origin = getIntent().getStringExtra("idkab_toko");
        weight = getIntent().getStringExtra("weight");

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

    public void hitungOngkir(final String kurir){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemCost> callOngkir= service.hitungOngkir(origin,"city",destination,"subdistrict",Integer.parseInt(weight),kurir);
        callOngkir.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {
//                cost = (Cost) response.body().getRajaongkir().getResults().get(0).getCosts().get(0).getCost();
                costs.clear();
                for(int i =0; i < response.body().getRajaongkir().getResults().get(0).getCosts().size(); i++)
                {
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
                Toast.makeText(OpsiPengirimanActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
            }
        });
    }

}