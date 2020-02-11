package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceWrapper;
import com.sholeh.marketplacenj.adapter.adapterspin;
import com.sholeh.marketplacenj.respon.RegRegristasi;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "RegisterActivity";

    EditText ed_nama, ed_username, ed_password, ed_konfirmasiPass, ed_alamat, ed_kodepos, ed_nomorHP, ed_email;
    Button simpank;
    Spinner spinProvinsi, spinkota;
    adapterspin adapterspinner;
    ArrayList<String> arrayProv = new ArrayList<>();
    ArrayList<String> listID_prov = new ArrayList<>();
    ArrayList<String> arrayKota = new ArrayList<>();
    ArrayList<String> listID_Kota = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinProvinsi = findViewById(R.id.spin_provinsi);
        spinkota = findViewById(R.id.spin_kota);
        ed_nama = findViewById(R.id.edNama);
        ed_username = findViewById(R.id.edUsername);
        ed_password = findViewById(R.id.edPassword);
        ed_konfirmasiPass = findViewById(R.id.edKonfirmasiPassword);
        ed_alamat = findViewById(R.id.edAlamat);
        ed_kodepos = findViewById(R.id.edKodePos);
        ed_nomorHP = findViewById(R.id.edNomorHP);
        ed_email = findViewById(R.id.edEmail);
        simpank = findViewById(R.id.btnSimpank);
        simpank.setOnClickListener(this);

        spinProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinProvinsi.getSelectedItem().equals("Pilih Provinsi")) {


                } else {

                    getCity(listID_prov.get(position));

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinkota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getProvince();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

                        arrayKota.add(response.body().getRajaongkir().getResults().get(a).getType()+" "+response.body().getRajaongkir().getResults().get(a).getCityName());
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


    public void sendRegristasi() {

        if (!NetworkUtility.isNetworkConnected(RegisterActivity.this)) {
            AppUtilits.displayMessage(RegisterActivity.this, getString(R.string.network_not_connected));

        }else if (spinProvinsi.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Provinsi")) {
            Toast.makeText(this, "Provinsi Belum  di Tentukan", Toast.LENGTH_SHORT).show();

        } else if (spinProvinsi.getSelectedItemPosition() < 0 || spinkota.getSelectedItemPosition() < 0 || spinkota.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Kota")) {
            Toast.makeText(this, "Kota Belum  di Tentukan", Toast.LENGTH_SHORT).show();
        } else {
            final String namalengkap_ = ed_nama.getText().toString();
            final String username_ = ed_username.getText().toString();
            final String password_ = ed_password.getText().toString();
            final String konpassword_ = ed_konfirmasiPass.getText().toString();
            final String idprov_ = listID_prov.get(spinProvinsi.getSelectedItemPosition());
            final String idkota_ = listID_Kota.get(spinkota.getSelectedItemPosition());
            final String alamat_ = ed_alamat.getText().toString();
            final String kodepos_ = ed_kodepos.getText().toString();
            final String nomorHp_ = ed_nomorHP.getText().toString();
            final String email_ = ed_email.getText().toString();
            final String statusA_ = "aktif";

//            if (!validasi()) return;
            ServiceWrapper serviceWrapper = new ServiceWrapper(null);
            Call<RegRegristasi> callNewREgistration = serviceWrapper.newUserRegistrationCall(
                    namalengkap_, username_, password_, idprov_, idkota_, alamat_, kodepos_, nomorHp_, email_, statusA_);
            callNewREgistration.enqueue(new Callback<RegRegristasi>() {
                @Override
                public void onResponse(Call<RegRegristasi> call, Response<RegRegristasi> response) {
                    Toast.makeText(RegisterActivity.this, "res"+response, Toast.LENGTH_SHORT).show();

                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {
                            for (int a = 0; a < response.body().getData().size() ; a++) {
                                SharedPreferences preferences = getSharedPreferences("App", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = preferences.edit();
                                edit.putString("id_konsumen", String.valueOf(response.body().getData().get(a).getIdKonsumen()));
                                edit.putString("nama_lengkap", String.valueOf(response.body().getData().get(a).getNamaLengkap()));

                                edit.putBoolean("bg",true);
                                edit.commit();
                                Toast.makeText(RegisterActivity.this, "id "+String.valueOf(response.body().getData().get(a).getIdKonsumen()), Toast.LENGTH_SHORT).show();
                                // start home activity
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                //intent.putExtra("userid", "sdfsd");
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
                            }


                        } else {
                            Toast.makeText(RegisterActivity.this, "r"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                            AppUtilits.displayMessage(RegisterActivity.this,  response.body().getPesan());
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "rr"+response.body().getPesan(), Toast.LENGTH_SHORT).show();

//                        AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                    }
                }

                @Override
                public void onFailure(Call<RegRegristasi> call, Throwable t) {
                    Log.e(TAG, " failure " + t.toString());
                    Toast.makeText(RegisterActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                }
            });

        }
    }

    private boolean validasi() {
        boolean valid = true;
        final String namalengkap_ = ed_nama.getText().toString();
        final String username_ = ed_username.getText().toString();
        final String password_ = ed_password.getText().toString();
        final String konpassword_ = ed_konfirmasiPass.getText().toString();
        final String alamat_ = ed_alamat.getText().toString();
        final String kodepos_ = ed_kodepos.getText().toString();
        final String nomorHp_ = ed_nomorHP.getText().toString();
        final String email_ = ed_email.getText().toString();
        final String statusA_ = "aktif";


        if (namalengkap_.isEmpty()) {
            ed_nama.setError("isi nama lengkap anda");
            Toast.makeText(RegisterActivity.this, "isi nama lengkap anda", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (namalengkap_.length() < 2) {
            Toast.makeText(RegisterActivity.this, "nama lengkap setidaknya minimal 2", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_nama.setError(null);
        }

        if (username_.isEmpty()) {
            ed_username.setError("username wajib di isi");
            Toast.makeText(RegisterActivity.this, "username wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_username.setError(null);
        }

        if (password_.isEmpty()) {
            ed_password.setError("password wajib di isi");
            Toast.makeText(RegisterActivity.this, "password wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (konpassword_.isEmpty()) {
            ed_password.setError("konfirmasi password wajib di isi");
            Toast.makeText(RegisterActivity.this, "konfirmasi password wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;

        } else if (!ed_password.getText().toString().equals(ed_konfirmasiPass.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "kata sandi tidak cocok", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (password_.length() < 7) {
            Toast.makeText(RegisterActivity.this, "password minimal 6 digit", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_password.setError(null);
        }
        if (alamat_.isEmpty()) {
            ed_alamat.setError("alamat wajib di isi");
            Toast.makeText(RegisterActivity.this, "alamat wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_alamat.setError(null);
        }

        if (kodepos_.isEmpty()) {
            ed_kodepos.setError("kode pos wajib di isi");
            Toast.makeText(RegisterActivity.this, "kode pos wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_kodepos.setError(null);
        }

        if (nomorHp_.isEmpty()) {
            ed_nomorHP.setError("nomor hp wajib di isi");
            Toast.makeText(RegisterActivity.this, "nomor hp wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_nomorHP.setError(null);
        }

        if (email_.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
            ed_email.setError("email tidak valid");
            Toast.makeText(RegisterActivity.this, "email tidak valid", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_email.setError(null);
        }
        return valid;
    }


}
