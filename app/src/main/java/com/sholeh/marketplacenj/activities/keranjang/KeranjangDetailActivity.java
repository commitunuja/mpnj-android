package com.sholeh.marketplacenj.activities.keranjang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.adapter.keranjang.ExpandListScanAdapter;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.DataKeranjang;
import com.sholeh.marketplacenj.respon.ItemKeranjang;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.Preferences;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;

public class KeranjangDetailActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandListScanAdapter expanAdapter;
    private List<HeaderModel> listHeader;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    private TextView tvx_total;
    List<ChildModel> child;
    CheckBox cb_select_all;

    Preferences preferences;
    String id_konsumen;
    int hargaJual;
    private double setharga, hargaTotal;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        tvx_total = findViewById(R.id.total);


        listView = findViewById(R.id.expListhistori);
        cb_select_all = findViewById(R.id.cb_select_all);

        cb_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    expanAdapter.setupAllChecked(checkBox.isChecked());

                    if(cb_select_all.isChecked()){
                        getTotal();
                    }else {
                        tvx_total.setText("Rp0");
                    }
                }
            }
        });

//        expanAdapter.setOnAllCheckedBoxNeedChangeListener(new ExpandListScanAdapter.OnAllCheckedBoxNeedChangeListener() {
//            @Override
//            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
//                cb_select_all.setChecked(allParentIsChecked);
//            }
//        });

        getDetailKeranjang();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));



    }


    public void getDetailKeranjang() {
//        if (!NetworkUtility.isNetworkConnected(KeranjangDetailActivity.this)){
//            AppUtilits.displayMessage(KeranjangDetailActivity.this,  getString(R.string.network_not_connected));
//
//        }else {
        //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang("konsumen", id_konsumen);

        listHeader = new ArrayList<>();
        listChild = new HashMap<>();
        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, retrofit2.Response<ResDetailKeranjang> response) {

                //   Log.e(TAG, "response is "+ response.body() + "  ---- "+ new Gson().toJson(response.body()));
                //  Log.e(TAG, "  ss sixe 1 ");
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getDataKeranjang().size() > 0) {
                        response.body().getTotalHarganya();

                        listHeader.clear();
                        listChild.clear();
                        List<DataKeranjang> array = response.body().getDataKeranjang();
                        for (int i = 0; i < array.size(); i++) {
                            listHeader.add(new HeaderModel(response.body().getDataKeranjang().get(i).getIdToko(),
                                    response.body().getDataKeranjang().get(i).getNamaToko(), false));


                            child = new ArrayList<>();
                            List<ItemKeranjang> childLink = array.get(i).getItem();
                            for (int j = 0; j < childLink.size(); j++) {
                                String idKeranjang = childLink.get(j).getIdKeranjang();
                                String namaProduk = childLink.get(j).getNamaProduk();
                                hargaJual = Integer.parseInt(String.valueOf(childLink.get(j).getHargaJual()));
                                int diskon = Integer.parseInt((childLink.get(j).getDiskon()));
                                int jumlah = Integer.parseInt(String.valueOf(childLink.get(j).getJumlah()));
                                String foto = childLink.get(j).getFoto();
                                int stok = Integer.parseInt(String.valueOf(childLink.get(j).getStok()));


                                child.add(new ChildModel(idKeranjang, namaProduk, hargaJual, diskon, jumlah, foto, stok, false));

                            }
                            listChild.put(listHeader.get(i), child);

                        }
//                        Log.d("sholceng", new Gson().toJson(listChild));
                        expanAdapter = new ExpandListScanAdapter(KeranjangDetailActivity.this, listHeader, listChild);
                        listView.setAdapter(expanAdapter);
                        int count = expanAdapter.getGroupCount();
                        for (int i = 0; i < count; i++) {
                            listView.expandGroup(i);
                        }

                    } else {
                        AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.network_error));
                    }

//                    double totalnya = response.body().getTotalHarganya();
//                    st = new StringTokenizer(formatRupiah.format(totalnya), ",");
//                    String splitotal = st.nextToken().trim();
//                    tvx_total.setText(splitotal);


                } else {
                    AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.network_error));
                }

            }

            @Override
            public void onFailure(Call<ResDetailKeranjang> call, Throwable t) {
//                Toast.makeText(KeranjangDetailActivity.this, "e"+t, Toast.LENGTH_SHORT).show();
                //  Log.e(TAG, "  fail- add to cart item "+ t.toString());
//                AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.fail_toGetcart));

                Log.d("cekkk", String.valueOf(t));
                Toast.makeText(KeranjangDetailActivity.this, "cekk" + t, Toast.LENGTH_SHORT).show();
                listHeader.clear();
                listChild.clear();


            }
        });
//        }
    }


    public void getTotal() {
//        if (!NetworkUtility.isNetworkConnected(KeranjangDetailActivity.this)){
//            AppUtilits.displayMessage(KeranjangDetailActivity.this,  getString(R.string.network_not_connected));
//
//        }else {
        //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang("konsumen", id_konsumen);
        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, retrofit2.Response<ResDetailKeranjang> response) {

                double totalnya = response.body().getTotalHarganya();
                    st = new StringTokenizer(formatRupiah.format(totalnya), ",");
                    String splitotal = st.nextToken().trim();
                    tvx_total.setText(splitotal);

            }

            @Override
            public void onFailure(Call<ResDetailKeranjang> call, Throwable t) {
//                Toast.makeText(KeranjangDetailActivity.this, "e"+t, Toast.LENGTH_SHORT).show();
                //  Log.e(TAG, "  fail- add to cart item "+ t.toString());
//                AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.fail_toGetcart));

                Log.d("cekkk", String.valueOf(t));

            }
        });
//        }
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String qty = intent.getStringExtra("total");
            hargaTotal = Double.parseDouble(qty);
            st = new StringTokenizer(formatRupiah.format(hargaTotal), ",");
            String harganya = st.nextToken().trim();
            tvx_total.setText(harganya);


        }
    };
}