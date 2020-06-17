package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.adapter.KecAdapter;
import com.sholeh.marketplacenj.model.subdistrict.ItemKec;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.KotaAdapter;
import com.sholeh.marketplacenj.adapter.ProvinsiAdapter;
import com.sholeh.marketplacenj.adapter.adapterspin;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.model.province.Result;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.respon.ResDetailAlamat;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailAlamat extends AppCompatActivity implements View.OnClickListener {

    public String alamatId, userType, namaProvinsi, namaKota, userId;
    EditText edNama, edNoHP, edKodePos, edAlamatLengkap;
    TextView tvx_hapus, tvx_simpan, edProv, edKota, edKec, edIdProv, edIdKota, edIdKec;
    Toolbar toolBarisi;


    adapterspin adapterspinner;
    ArrayList<String> arrayProv = new ArrayList<>();
    ArrayList<String> listID_prov = new ArrayList<>();
    ArrayList<String> arrayKota = new ArrayList<>();
    ArrayList<String> listID_Kota = new ArrayList<>();


    private AlertDialog.Builder alert;
    private AlertDialog ad;
    private EditText searchList;
    private ListView mListView;

    private ProvinsiAdapter adapter_province;
    private List<Result> ListProvince = new ArrayList<Result>();

    private KotaAdapter adapter_city;
    private List<com.sholeh.marketplacenj.model.city.Result> ListCity = new ArrayList<com.sholeh.marketplacenj.model.city.Result>();

    private KecAdapter adapter_kec;
    private List<com.sholeh.marketplacenj.model.subdistrict.Result> ListKec = new ArrayList<com.sholeh.marketplacenj.model.subdistrict.Result>();



    private ProgressDialog progressDialog;
    Preferences preferences;
    String id_konsumen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        edNama = findViewById(R.id.ed_nama);
        edNoHP = findViewById(R.id.ed_nohp);
        edIdProv = findViewById(R.id.ed_Idprov);
        edProv = findViewById(R.id.ed_prov);
        edIdKota = findViewById(R.id.ed_Idkota);
        edKota = findViewById(R.id.ed_kota);
        edIdKec = findViewById(R.id.ed_IdKec);
        edKec = findViewById(R.id.ed_kec);

        edKodePos = findViewById(R.id.ed_kodepos);
        edAlamatLengkap = findViewById(R.id.ed_alamat);
        tvx_simpan = findViewById(R.id.tvSave);
        tvx_hapus = findViewById(R.id.tvHapus);

        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Detail Alamat");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alamatId = getIntent().getExtras().getString("alamat_id");

        edProv.setOnClickListener(this);
        edKota.setOnClickListener(this);
        edKec.setOnClickListener(this);
        tvx_simpan.setOnClickListener(this);
        tvx_hapus.setOnClickListener(this);

        detailAlamat();

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_prov:
                popUpProvinsi(edProv, edKota);
                break;

            case R.id.ed_kota:
                try {
                    if (edProv.getTag().equals("")) {
                        edProv.setError("Silahkan Pilih Provinsi");
                    } else {

                        popUpKota(edKota, edProv);
                    }

                } catch (NullPointerException e) {
                    edProv.setError("Silahkan Pilih Provinsi");
                }
                break;

            case R.id.ed_kec:
                try {
                    if (edKota.getTag().equals("")) {
                        edKota.setError("Silahkan Pilih Kota");
                    } else {

                        popUpKec(edKec, edKota);
                    }

                } catch (NullPointerException e) {
                    edKota.setError("Silahkan Pilih Kota");
                }
                break;

            case R.id.tvSave:
                ubahAlamat();
                break;

            case R.id.tvHapus:
                deleteAlamat();

                break;

            default:
                break;
        }

    }


    public void detailAlamat() {
        if (!NetworkUtility.isNetworkConnected(DetailAlamat.this)) {
            AppUtilits.displayMessage(DetailAlamat.this, getString(R.string.network_not_connected));
        } else {

            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResDetailAlamat> call = service.getDetailAlamat(alamatId);
            call.enqueue(new Callback<ResDetailAlamat>() {
                @Override
                public void onResponse(Call<ResDetailAlamat> call, Response<ResDetailAlamat> response) {

//                    tvDetailAlamat = response.body();
//                    String nama = response.body().getData().getN;


                    if (response.body() != null && response.isSuccessful()) {
//
                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {

                            if (response.body().getData().size() > 0) {
//                                    arrayKota.clear();
//                                    listID_Kota.clear();
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    edNama.setText(response.body().getData().get(i).getNama());
                                    edNoHP.setText(response.body().getData().get(i).getNomorTelepon());
                                    edProv.setText(response.body().getData().get(i).getNamaProvinsi());
                                    edIdProv.setText(String.valueOf(response.body().getData().get(i).getProvinsiId()));
                                    edKota.setText(response.body().getData().get(i).getNamaKota());
                                    edIdKota.setText(String.valueOf(response.body().getData().get(i).getCityId()));
                                    edIdKec.setText(String.valueOf(response.body().getData().get(i).getKecamatanId()));
                                    edKec.setText(String.valueOf(response.body().getData().get(i).getNamaKecamatan()));
                                    edKodePos.setText(response.body().getData().get(i).getKodePos());
                                    edAlamatLengkap.setText(response.body().getData().get(i).getAlamatLengkap());


                                }

                            }

                        } else {
                            AppUtilits.displayMessage(DetailAlamat.this, response.body().getPesan());
                        }
                    } else {
                        AppUtilits.displayMessage(DetailAlamat.this, getString(R.string.network_error));
                    }

                }

                @Override
                public void onFailure(Call<ResDetailAlamat> call, Throwable t) {
                    AppUtilits.displayMessage(DetailAlamat.this, getString(R.string.fail_togetaddress));
                }
            });


        }

    }

    public void popUpProvinsi(final TextView etProvince, final TextView etCity) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View alertLayout = inflater.inflate(R.layout.custom_dialog_search, null);

        alert = new AlertDialog.Builder(DetailAlamat.this);
        alert.setTitle("List Provinsi");
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        searchList = alertLayout.findViewById(R.id.searchItem);
        searchList.addTextChangedListener(new MyTextWatcherProvince(searchList));
        searchList.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        mListView = alertLayout.findViewById(R.id.listItem);

        ListProvince.clear();
        adapter_province = new ProvinsiAdapter(DetailAlamat.this, ListProvince);
        mListView.setClickable(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = mListView.getItemAtPosition(i);
                Result cn = (Result) o;

                etProvince.setError(null);
                etProvince.setText(cn.getProvince());
                etProvince.setTag(cn.getProvinceId());

//                Toast.makeText(DetailAlamat.this, "id :"+cn.getProvinceId()+" nama"+cn.getProvince(), Toast.LENGTH_SHORT).show();

                etCity.setText("Pilih Kota");
                etCity.setTag("Pilih Kota");

                ad.dismiss();
            }
        });

        progressDialog = new ProgressDialog(DetailAlamat.this);
        progressDialog.setMessage("Silahkan Tunggu...");
        progressDialog.show();

        getProvince();

    }

    private class MyTextWatcherProvince implements TextWatcher {

        private View view;

        private MyTextWatcherProvince(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence s, int i, int before, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.searchItem:
                    adapter_province.filter(editable.toString());
                    break;
            }
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

                progressDialog.dismiss();
                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int a = 0; a <= count_data - 1; a++) {
                        Result itemProvince = new Result(
                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
                                response.body().getRajaongkir().getResults().get(a).getProvince()
                        );

                        ListProvince.add(itemProvince);
                        mListView.setAdapter(adapter_province);
                    }

                    adapter_province.setList(ListProvince);
                    adapter_province.filter("");

                } else {
                    String error = "Error Retrive Data from Server !!!";
                    Toast.makeText(DetailAlamat.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemProvince> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void popUpKota(final TextView etCity, final TextView etProvince) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View alertLayout = inflater.inflate(R.layout.custom_dialog_search, null);

        alert = new AlertDialog.Builder(DetailAlamat.this);
        alert.setTitle("List Kota");
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        searchList = alertLayout.findViewById(R.id.searchItem);
        searchList.addTextChangedListener(new MyTextWatcherCity(searchList));
        searchList.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        mListView = alertLayout.findViewById(R.id.listItem);

        ListCity.clear();
        adapter_city = new KotaAdapter(DetailAlamat.this, ListCity);
        mListView.setClickable(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = mListView.getItemAtPosition(i);
                com.sholeh.marketplacenj.model.city.Result cn = (com.sholeh.marketplacenj.model.city.Result) o;

                etCity.setError(null);
                etCity.setText(cn.getType() + " " + cn.getCityName());
                etCity.setTag(cn.getCityId());

                edKec.setText("Pilih Kecamatan");
                edKec.setTag("Pilih Kecamtan");


                ad.dismiss();
            }
        });

        progressDialog = new ProgressDialog(DetailAlamat.this);
        progressDialog.setMessage("Silahkan Tunggu...");
        progressDialog.show();

        getCity(etProvince.getTag().toString());

    }

    private class MyTextWatcherCity implements TextWatcher {

        private View view;

        private MyTextWatcherCity(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence s, int i, int before, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.searchItem:
                    adapter_city.filter(editable.toString());
                    break;
            }
        }
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

                progressDialog.dismiss();
                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int a = 0; a <= count_data - 1; a++) {
                        com.sholeh.marketplacenj.model.city.Result itemCity= new com.sholeh.marketplacenj.model.city.Result(
                                response.body().getRajaongkir().getResults().get(a).getCityId(),
                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
                                response.body().getRajaongkir().getResults().get(a).getProvince(),
                                response.body().getRajaongkir().getResults().get(a).getType(),
                                response.body().getRajaongkir().getResults().get(a).getCityName(),
                                response.body().getRajaongkir().getResults().get(a).getPostalCode()
                        );

                        ListCity.add(itemCity);
                        mListView.setAdapter(adapter_city);
                    }

                    adapter_city.setList(ListCity);
                    adapter_city.filter("");

                } else {
                    String error = "Error Retrive Data from Server !!!";
                    Toast.makeText(DetailAlamat.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemCity> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

                progressDialog.dismiss();
                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int a = 0; a <= count_data - 1; a++) {
                        com.sholeh.marketplacenj.model.subdistrict.Result itemkec= new com.sholeh.marketplacenj.model.subdistrict.Result(
                                response.body().getRajaongkir().getResults().get(a).getSubdistrictId(),
                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
                                response.body().getRajaongkir().getResults().get(a).getProvince(),
                                response.body().getRajaongkir().getResults().get(a).getCityId(),
                                response.body().getRajaongkir().getResults().get(a).getCity(),
                                response.body().getRajaongkir().getResults().get(a).getType(),
                                response.body().getRajaongkir().getResults().get(a).getSubdistrictName()
                        );

                        ListKec.add(itemkec);
                        mListView.setAdapter(adapter_kec);
                    }

                    adapter_kec.setList(ListKec);
                    adapter_kec.filter("");

                } else {
                    String error = "Error Retrive Data from Server !!!";
                    Toast.makeText(DetailAlamat.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemKec> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIInterface service = retrofit.create(APIInterface.class);
//        Call<ItemKec> call = service.getKec(id_kota);
//
//        call.enqueue(new Callback<ItemKec>() {
//            @Override
//            public void onResponse(Call<ItemKec> call, Response<ItemKec> response) {
//
//
//                Log.v("wow", "json : " + new Gson().toJson(response));
//
//                if (response.isSuccessful()) {
//
//                    int count_data = response.body().getRajaongkir().getResults().size();
//                    for (int a = 0; a <= count_data - 1; a++) {
//                        com.sholeh.marketplacenj.model.subdistrict.Result itemKec = new com.sholeh.marketplacenj.model.subdistrict.Result(
//                                response.body().getRajaongkir().getResults().get(a).getSubdistrictId(),
//                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
//                                response.body().getRajaongkir().getResults().get(a).getProvince(),
//                                response.body().getRajaongkir().getResults().get(a).getCityId(),
//                                response.body().getRajaongkir().getResults().get(a).getCity(),
//                                response.body().getRajaongkir().getResults().get(a).getType(),
//                                response.body().getRajaongkir().getResults().get(a).getSubdistrictName()
//                        );
//
//                        ListKec.add(itemKec);
//                        mListView.setAdapter(adapter_kec);
//
//                    }
//
//                    adapter_kec.setList(ListKec);
//                    adapter_kec.filter("");
//
//                } else {
//                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(DetailAlamat.this, error, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ItemKec> call, Throwable t) {
//
//                Toast.makeText(DetailAlamat.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void popUpKec(final TextView etKec, final TextView etKota) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View alertLayout = inflater.inflate(R.layout.custom_dialog_search, null);

        alert = new AlertDialog.Builder(DetailAlamat.this);
        alert.setTitle("List Kecamatan");
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        searchList = alertLayout.findViewById(R.id.searchItem);
        searchList.addTextChangedListener(new MyTextWatcherKec(searchList));
        searchList.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        mListView = alertLayout.findViewById(R.id.listItem);

        ListKec.clear();
        adapter_kec = new KecAdapter(DetailAlamat.this, ListKec);
        mListView.setClickable(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = mListView.getItemAtPosition(i);
                com.sholeh.marketplacenj.model.subdistrict.Result cn = (com.sholeh.marketplacenj.model.subdistrict.Result) o;

                etKec.setError(null);
                etKec.setText(cn.getSubdistrictName());
                etKec.setTag(cn.getSubdistrictId());

                ad.dismiss();
            }
        });

        progressDialog = new ProgressDialog(DetailAlamat.this);
        progressDialog.setMessage("Silahkan Tunggu...");
        progressDialog.show();

        getKec(etKota.getTag().toString());

    }

    private class MyTextWatcherKec implements TextWatcher {

        private View view;

        private MyTextWatcherKec(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence s, int i, int before, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.searchItem:
                    adapter_kec.filter(editable.toString());
                    break;
            }
        }
    }


    public void ubahAlamat() {

//        if (!NetworkUtility.isNetworkConnected(UbahPassword.this)){
//            AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.network_not_connected));
//
//        }else {
        final String namalengkap_ = edNama.getText().toString();
        final String nomorHp_ = edNoHP.getText().toString();
        final String idprov_ = edIdProv.getText().toString();
        final String namaProv_ = edProv.getText().toString();
        final String idkota_ = edIdKota.getText().toString();
        final String namakota_ = edKota.getText().toString();
        final String idkec_ = edIdKec.getText().toString();
        final String namakec_ = edKec.getText().toString();
        final String alamat_ = edAlamatLengkap.getText().toString();
        final String kodepos_ = edKodePos.getText().toString();
        // userid


//            if (!validasi()) return;
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResAlamat> call = service.KonsumenUbahAlamat(alamatId, namalengkap_, nomorHp_, idprov_, namaProv_, idkota_, namakota_,idkec_, namakec_, kodepos_, alamat_, id_konsumen);

        call.enqueue(new Callback<ResAlamat>() {
            @Override
            public void onResponse(Call<ResAlamat> call, Response<ResAlamat> response) {

                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {
//
                        Toast.makeText(DetailAlamat.this, "Alamat berhasil di perbarui", Toast.LENGTH_LONG).show();
                        finish();
//
//
                    } else {
                        Toast.makeText(DetailAlamat.this, "Alamat gagal di perbarui" + response.body().getPesan(), Toast.LENGTH_LONG).show();

//                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
                    }
                } else {
                    Toast.makeText(DetailAlamat.this, "Alamat gagal di perbarui" + response.body(), Toast.LENGTH_LONG).show();

//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));


                }
            }

            @Override
            public void onFailure(Call<ResAlamat> call, Throwable t) {
                Toast.makeText(DetailAlamat.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }

        });


    }

    public void deleteAlamat() {

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

        Call<ResAlamat> call = service.hapusItemAlamat(alamatId);
        call.enqueue(new Callback<ResAlamat>() {
            @Override
            public void onResponse(Call<ResAlamat> call, Response<ResAlamat> response) {

                Toast.makeText(DetailAlamat.this, "res"+response, Toast.LENGTH_SHORT).show();
                Log.d("hapus", String.valueOf(response));

                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {
//
                        Toast.makeText(DetailAlamat.this, "Alamat berhasil di hapus", Toast.LENGTH_LONG).show();
                        finish();
//
//
                    } else {
                        Toast.makeText(DetailAlamat.this, "Alamat gagal di hapus" + response.body().getPesan(), Toast.LENGTH_LONG).show();

//                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
                    }
                } else {
                    Toast.makeText(DetailAlamat.this, "Alamat gagal di hapus" + response.body(), Toast.LENGTH_LONG).show();

//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));

                }
            }

            @Override
            public void onFailure(Call<ResAlamat> call, Throwable t) {

                Toast.makeText(DetailAlamat.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }


}


