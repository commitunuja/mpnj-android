package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.activities.alamat.PilihAlamatCheckout;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.model.subdistrict.ItemKec;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.bank.adapterspin;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAlamat extends AppCompatActivity implements View.OnClickListener {

    EditText ed_nama, ed_nomorHP, ed_alamat, ed_kodepos;
    Button simpanAlamat;

    Spinner spinProvinsi, spinkota, spinKec;
    String namaProvinsi, namaKota, namaKecamatan, userId;
    adapterspin adapterspinner;
    ArrayList<String> arrayProv = new ArrayList<>();
    ArrayList<String> listID_prov = new ArrayList<>();
    ArrayList<String> arrayKota = new ArrayList<>();
    ArrayList<String> listID_Kota = new ArrayList<>();

    ArrayList<String> arrayKec = new ArrayList<>();
    ArrayList<String> listID_Kec = new ArrayList<>();

    Preferences preferences;
    String valalamat, cekOngkir, nilaiIntent;
    ArrayList<String> idK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addalamat);
        preferences = new Preferences(getApplication());
        userId = preferences.getIdKonsumen();

        spinProvinsi = findViewById(R.id.spin_provinsi);
        spinkota = findViewById(R.id.spin_kota);
        spinKec = findViewById(R.id.spin_kec);
        ed_nama = findViewById(R.id.edNama);
        ed_alamat = findViewById(R.id.edAlamat);
        ed_kodepos = findViewById(R.id.edKodePos);
        ed_nomorHP = findViewById(R.id.edNomorHP);

        simpanAlamat = findViewById(R.id.btnSimpanAlamat);
        simpanAlamat.setOnClickListener(this);
        Intent i = getIntent();
        valalamat = i.getStringExtra("alamat");
        idK = i.getStringArrayListExtra("idcheckout");
        cekOngkir = i.getStringExtra("cekongkir");
        nilaiIntent = i.getStringExtra("icheckout");
//        Toast.makeText(this, ""+alamat, Toast.LENGTH_SHORT).show();

        spinProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinProvinsi.getSelectedItem().equals("Pilih Provinsi")) {


                } else {
                    getCity(listID_prov.get(position));
                    namaProvinsi = spinProvinsi.getSelectedItem().toString();


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinkota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinkota.getSelectedItem().equals("Pilih Kota")) {


                } else {
                    getKec(listID_Kota.get(position));
                    namaKota = spinkota.getSelectedItem().toString();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinKec.getSelectedItem().equals("Pilih Kecamatan")) {


                } else {
                    namaKecamatan = spinKec.getSelectedItem().toString();


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
        switch (v.getId()) {
            case R.id.btnSimpanAlamat:
                sendAlamat();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    public void onBack() {
        if (valalamat.equalsIgnoreCase("activity")) {
            finish();
        } else {
            Intent intent = new Intent(AddAlamat.this, PilihAlamatCheckout.class);
            intent.putExtra("reset_kurir", "Silahkan Pilih Pengiriman Produk Anda");
            intent.putExtra("icheckout", nilaiIntent);
            intent.putStringArrayListExtra("idcheckout", idK);
            startActivity(intent);
            finish();
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
                    adapterspinner = new adapterspin(AddAlamat.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayProv);
                    adapterspinner.add("Pilih Provinsi");
                    spinProvinsi.setAdapter(adapterspinner);
                    spinProvinsi.setSelection(adapterspinner.getCount());
                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
                    Toast.makeText(AddAlamat.this, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemProvince> call, Throwable t) {

                Toast.makeText(AddAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

                Log.d("kec", String.valueOf(response));

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

                        arrayKota.add(response.body().getRajaongkir().getResults().get(a).getType() + " " + response.body().getRajaongkir().getResults().get(a).getCityName());
                        listID_Kota.add(response.body().getRajaongkir().getResults().get(a).getCityId());

                    }

                    adapterspinner = new adapterspin(AddAlamat.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayKota);
                    adapterspinner.add("Pilih Kota");
                    spinkota.setAdapter(adapterspinner);
                    spinkota.setSelection(adapterspinner.getCount());

                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
                    Toast.makeText(AddAlamat.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemCity> call, Throwable t) {

                Toast.makeText(AddAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getKec(String id_kota) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<ItemKec> call = service.getKec(id_kota);

        call.enqueue(new Callback<ItemKec>() {
            @Override
            public void onResponse(Call<ItemKec> call, Response<ItemKec> response) {


                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int a = 0; a <= count_data - 1; a++) {
                        com.sholeh.marketplacenj.model.subdistrict.Result itemKec = new com.sholeh.marketplacenj.model.subdistrict.Result(
                                response.body().getRajaongkir().getResults().get(a).getSubdistrictId(),
                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
                                response.body().getRajaongkir().getResults().get(a).getProvince(),
                                response.body().getRajaongkir().getResults().get(a).getCityId(),
                                response.body().getRajaongkir().getResults().get(a).getCity(),
                                response.body().getRajaongkir().getResults().get(a).getType(),
                                response.body().getRajaongkir().getResults().get(a).getSubdistrictName()


                        );


                        arrayKec.add(response.body().getRajaongkir().getResults().get(a).getSubdistrictName());
                        listID_Kec.add(response.body().getRajaongkir().getResults().get(a).getSubdistrictId());

                    }

                    adapterspinner = new adapterspin(AddAlamat.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayKec);
                    adapterspinner.add("Pilih Kecamatan");
                    spinKec.setAdapter(adapterspinner);
                    spinKec.setSelection(adapterspinner.getCount());

                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
                    Toast.makeText(AddAlamat.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemKec> call, Throwable t) {

                Toast.makeText(AddAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sendAlamat() {
        if (!NetworkUtility.isNetworkConnected(AddAlamat.this)) {
//            AppUtilits.displayMessage(AddAlamat.this, getString(R.string.network_not_connected));
        } else if (spinProvinsi.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Provinsi")) {
            Toast.makeText(this, "Provinsi Belum  di Tentukan", Toast.LENGTH_SHORT).show();
        } else if (spinProvinsi.getSelectedItemPosition() < 0 || spinkota.getSelectedItemPosition() < 0 || spinkota.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Kota")) {
            Toast.makeText(this, "Kota Belum  di Tentukan", Toast.LENGTH_SHORT).show();
        } else if (spinKec.getSelectedItemPosition() < 0 || spinKec.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Kecamatan")) {
            Toast.makeText(this, "Kecamatan Belum  di Tentukan", Toast.LENGTH_SHORT).show();
        } else {
            final String namalengkap_ = ed_nama.getText().toString();
            final String nomorHp_ = ed_nomorHP.getText().toString();
            final String idprov_ = listID_prov.get(spinProvinsi.getSelectedItemPosition());
            final String namaProv_ = spinProvinsi.getSelectedItem().toString();
            final String idkota_ = listID_Kota.get(spinkota.getSelectedItemPosition());
            final String namaKota_ = spinkota.getSelectedItem().toString();
            final String idkec_ = listID_Kec.get(spinKec.getSelectedItemPosition());
            final String namaKec_ = spinKec.getSelectedItem().toString();
            final String kodepos_ = ed_kodepos.getText().toString();
            final String alamat_ = ed_alamat.getText().toString();
            // userid

//
//
//
//            final String email_ = ed_email.getText().toString();
//            final String statusA_ = "aktif";
//
            if (!validasi()) return;
//            ServiceWrapper serviceWrapper = new ServiceWrapper(null);
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResAlamat> callNewAlamat = service.addAlamatCall(
                    namalengkap_, nomorHp_, idprov_, namaProv_, idkota_, namaKota_, idkec_, namaKec_, kodepos_, alamat_, userId);
            callNewAlamat.enqueue(new Callback<ResAlamat>() {
                @Override
                public void onResponse(Call<ResAlamat> call, Response<ResAlamat> response) {
                    Log.d("addalamat", String.valueOf(response));
                    if (response.body() != null && response.isSuccessful()) {

                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {
                            Toast.makeText(AddAlamat.this, "Berhasil Menambah Alamat", Toast.LENGTH_SHORT).show();

                            if (valalamat.equalsIgnoreCase("activity")) {
                                Intent intent = new Intent(AddAlamat.this, AlamatActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(AddAlamat.this, PilihAlamatCheckout.class);
                                intent.putExtra("reset_kurir", "Silahkan Pilih Pengiriman Produk Anda");
                                intent.putExtra("icheckout", nilaiIntent);
                                intent.putStringArrayListExtra("idcheckout", idK);
                                startActivity(intent);
                                finish();
                            }

                        } else {
                            Log.d("addalamat", String.valueOf(response));
//                            AppUtilits.displayMessage(getApplication(), getString(R.string.failed_request));
//                            Toast.makeText(AddAlamat.this, "r"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                            AppUtilits.displayMessage(RegisterActivity.this,  response.body().getPesan());
                        }
                    } else {
                        Log.d("addalamat", String.valueOf(response));
//                        Toast.makeText(AddAlamat.this, "rr"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                        AppUtilits.displayMessage(getApplication(), getString(R.string.network_error));
//                        AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                    }
                }

                @Override
                public void onFailure(Call<ResAlamat> call, Throwable t) {
                    Log.d("addalamat", String.valueOf(t));
//                    AppUtilits.displayMessage(getApplication(), getString(R.string.network_error));
//                    Log.e(TAG, " failure " + t.toString());
//                    Toast.makeText(AddAlamat.this, "rrr"+t, Toast.LENGTH_SHORT).show();


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                }
            });
        }
    }


    private boolean validasi() {
        boolean valid = true;
        final String namalengkap_ = ed_nama.getText().toString();
        final String nomorHp_ = ed_nomorHP.getText().toString();
        final String kodepos_ = ed_kodepos.getText().toString();
        final String alamat_ = ed_alamat.getText().toString();

        if (namalengkap_.isEmpty()) {
            ed_nama.setError("nama lengkap tidak boleh kosong");
//            Toast.makeText(AddAlamat.this, "isi nama lengkap anda", Toast.LENGTH_SHORT).show();
            valid = false;
//        } else if (namalengkap_.length() < 2) {
//            Toast.makeText(AddAlamat.this, "nama lengkap setidaknya minimal 2", Toast.LENGTH_SHORT).show();
//            valid = false;
        } else {
            ed_nama.setError(null);
        }
        if (kodepos_.isEmpty()) {
            ed_kodepos.setError("kode pos wajib di isi");
            Toast.makeText(AddAlamat.this, "kode pos wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_kodepos.setError(null);
        }
        if (nomorHp_.isEmpty()) {
            ed_nomorHP.setError("nomor hp wajib di isi");
            Toast.makeText(AddAlamat.this, "nomor hp wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (nomorHp_.length() < 12) {
            ed_nomorHP.setError("nomor hp tidak valid");
            Toast.makeText(AddAlamat.this, "nomor hp tidak valid", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_nomorHP.setError(null);
        }
        return valid;
    }
}
