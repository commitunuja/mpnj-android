package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.ServiceWrapper;
import com.sholeh.marketplacenj.adapter.CityAdapter;
import com.sholeh.marketplacenj.adapter.adapterspin;
import com.sholeh.marketplacenj.model.ValueReg;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.province.ItemProvince;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG ="RegisterActivity";

    EditText ed_nama, ed_username, ed_password, ed_alamat, ed_kodepos, ed_nomorHP, ed_email;
    Button simpank ;


    TextView tvx_status, tvx_createdat, tvx_updatedat;

    Spinner spinProvinsi, spinkota;
    adapterspin adapterspinner;
    ArrayList<String> arrayProv = new ArrayList<>();
    ArrayList<String> listID_prov = new ArrayList<>();
    ArrayList<String> arrayKota = new ArrayList<>();
    ArrayList<String> listID_Kota = new ArrayList<>();




    private CityAdapter adapter_city;
    private List<com.sholeh.marketplacenj.model.city.Result> ListCity = new ArrayList<com.sholeh.marketplacenj.model.city.Result>();

//    private ExpedisiAdapter adapter_expedisi;
//    private List<ItemExpedisi> listItemExpedisi = new ArrayList<ItemExpedisi>();

    private ProgressDialog progressDialog;

    String statusAktif = "Aktif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinProvinsi = findViewById(R.id.spin_provinsi);
        spinkota = findViewById(R.id.spin_kota);
        ed_nama = findViewById(R.id.edNama);
        ed_username = findViewById(R.id.edUsername);
        ed_password = findViewById(R.id.edPassword);
        ed_alamat = findViewById(R.id.edAlamat);
        ed_kodepos = findViewById(R.id.edKodePos);
        ed_nomorHP = findViewById(R.id.edNomorHP);
        ed_email = findViewById(R.id.edEmail);
        simpank = findViewById(R.id.btnSimpank);
        simpank.setOnClickListener(this);







        spinProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(RegisterActivity.this, listID_prov.get(position) + " selected", Toast.LENGTH_LONG).show();
//
////                tampilDataDesa(listID_kec.get(position));
                if (spinProvinsi.getSelectedItem().equals("Pilih Provinsi")){

                }else{

                    getCity(listID_prov.get(position));

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getProvince();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSimpank:
                sendRegristasi();
                break;

            default:
                break;
        }
    }

    public void getProvince() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemProvince> call = service.getProvince();

        call.enqueue(new Callback<ItemProvince>() {
            @Override
            public void onResponse(Call<ItemProvince> call, Response<ItemProvince> response) {

                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int a = 0; a <= count_data - 1; a++) {

                        arrayProv.add(response.body().getRajaongkir().getResults().get(a).getProvince());
                        listID_prov.add(response.body().getRajaongkir().getResults().get(a).getProvinceId());


                    }
                    adapterspinner = new adapterspin(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayProv);
                    adapterspinner.add("Pilih Provinsi");
                    spinProvinsi.setAdapter(adapterspinner);
                    spinProvinsi.setSelection(adapterspinner.getCount());
                } else {
                    String error = "Error Retrive Data from Server !!!";
                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemProvince> call, Throwable t) {
//                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getCity(String id_province) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemCity> call = service.getCity(id_province);

        call.enqueue(new Callback<ItemCity>() {
            @Override
            public void onResponse(Call<ItemCity> call, Response<ItemCity> response) {


                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int a = 0; a <= count_data - 1; a++) {
                        com.sholeh.marketplacenj.model.city.Result itemProvince = new com.sholeh.marketplacenj.model.city.Result(
                                response.body().getRajaongkir().getResults().get(a).getCityId(),
                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
                                response.body().getRajaongkir().getResults().get(a).getProvince(),
                                response.body().getRajaongkir().getResults().get(a).getType(),
                                response.body().getRajaongkir().getResults().get(a).getCityName(),
                                response.body().getRajaongkir().getResults().get(a).getPostalCode()


                        );

                        arrayKota.add(response.body().getRajaongkir().getResults().get(a).getCityName());
                        listID_Kota.add(response.body().getRajaongkir().getResults().get(a).getCityId());

                    }

                    adapterspinner = new adapterspin(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayKota);
                    adapterspinner.add("Pilih Kota");
                    spinkota.setAdapter(adapterspinner);
                    spinkota.setSelection(adapterspinner.getCount());

                } else {
                    String error = "Error Retrive Data from Server !!!";
                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemCity> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void simpan_konsumen(){
//        final String namalengkap_ = ed_nama.getText ().toString ();
//        final String username_ = ed_username.getText ().toString ();
//        final String password_ = ed_password.getText ().toString ();
//        final String idprov_ = (listID_prov.get(spinProvinsi.getSelectedItemPosition()));
//        final String idkota_ = (listID_Kota.get(spinkota.getSelectedItemPosition()));
//        final String alamat_ = ed_alamat.getText().toString();
//        final String kodepos_ = ed_kodepos.getText().toString();
//        final String nomorHp_ = ed_nomorHP.getText().toString();
//        final String email_ = ed_email.getText().toString();
//        final String statusA_ = "aktif";
//
//
//        APIInterface api = ServiceGenerator.getRetrofit().create(APIInterface.class);
//
//        Call<ValueReg> simpank = api.registerKonsumenCall(namalengkap_, username_, idprov_, idkota_, alamat_, kodepos_, nomorHp_, email_, statusA_);// get
//        simpank.enqueue ( new Callback<ValueReg> () {
//            @Override
//            public void onResponse(Call<ValueReg> call, Response<ValueReg> response) {
//
////                String message = response.body ().getMessage ();
//                Toast.makeText ( RegisterActivity.this, "res: "+ response.toString(), Toast.LENGTH_SHORT ).show ();
//            }
//
//            @Override
//            public void onFailure(Call<ValueReg> call, Throwable t) {
//                Toast.makeText(RegisterActivity.this, "error: "+t, Toast.LENGTH_SHORT).show();
//            }
//        } );
    }

    public void sendRegristasi(){ //// v 2.9 4-:50
        final String namalengkap_ = ed_nama.getText ().toString ();
        final String username_ = ed_username.getText ().toString ();
        final String password_ = ed_password.getText ().toString ();
        final String idprov_ = (listID_prov.get(spinProvinsi.getSelectedItemPosition()));
        final String idkota_ = (listID_Kota.get(spinkota.getSelectedItemPosition()));
        final String alamat_ = ed_alamat.getText().toString();
        final String kodepos_ = ed_kodepos.getText().toString();
        final String nomorHp_ = ed_nomorHP.getText().toString();
        final String email_ = ed_email.getText().toString();
        final String statusA_ = "aktif";


        ServiceWrapper serviceWrapper = new ServiceWrapper(null);


        Call<ValueReg> callNewREgistration= serviceWrapper.newUserRegistrationCall(
                namalengkap_, username_, password_, idprov_, idkota_, alamat_, kodepos_, nomorHp_, email_, statusA_);


//        Log.d("kocor","nama: "+namalengkap_+" user"+username_+" pass"+ password_+" id prov: "+idprov_+ "idkota "+idkota_+" alamat"+alamat_+" kodepos"+kodepos_+ " hp"+nomorHp_+ " email"+email_+ " status"+statusA_);
        Log.d("kocor", serviceWrapper.newUserRegistrationCall(namalengkap_, username_, password_, idprov_, idkota_, alamat_, kodepos_, nomorHp_, email_, statusA_).toString());
//        Toast.makeText(this, ""+ serviceWrapper.newUserRegistrationCall(namalengkap_, username_, password_, idprov_, idkota_, alamat_, kodepos_, nomorHp_, email_, statusA_).toString(), Toast.LENGTH_SHORT).show();
        callNewREgistration.enqueue(new Callback<ValueReg>() {
            @Override
            public void onResponse(Call<ValueReg> call, Response<ValueReg> response) {

                if (response.body()!= null && response.isSuccessful()){
                    if (response.body().getPesan().equalsIgnoreCase("Sukses!")){
                        Toast.makeText(RegisterActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                        // start home activity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        //intent.putExtra("userid", "sdfsd");
                        startActivity(intent);
                        finish();


                    }else{
                        Toast.makeText(RegisterActivity.this, "gagaal"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
//
                }else{
                    Toast.makeText(RegisterActivity.this, "gagal", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<ValueReg> call, Throwable t) {
                  Log.e(TAG, " failure "+ t.toString());

                Toast.makeText(RegisterActivity.this, "f"+t, Toast.LENGTH_SHORT).show();
            }
        });


    }

}
