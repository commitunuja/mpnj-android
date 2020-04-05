package com.sholeh.marketplacenj.activities.keranjang;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class KeranjangDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ExpandableListView listView;
    private ExpandListScanAdapter expanAdapter;
    private List<HeaderModel> listHeader;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    private TextView tvx_total, tvx_checkout;
    List<ChildModel> child;
    public CheckBox cb_select_all;

    Preferences preferences;
    String id_konsumen;
    int hargaJual;
    String setharga;
    private double hargaTotal;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st;
    private boolean cbAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        tvx_total = findViewById(R.id.total);
        tvx_checkout = findViewById(R.id.tvx_checkout);
        listView = findViewById(R.id.expListhistori);
        tvx_checkout.setOnClickListener(this);

        getDetailKeranjang();


        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true; // mencegah parent klik
            }
        });

        cb_select_all = findViewById(R.id.cb_select_all);
        cb_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    expanAdapter.setupAllChecked(checkBox.isChecked());

                    if (cb_select_all.isChecked()) {
                        getTotal();
                    } else {
                        tvx_total.setText("Rp0");
                        getDetailKeranjang();
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

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
        LocalBroadcastManager.getInstance(this).registerReceiver(Receivercheck,
                new IntentFilter("custom-cball"));


//         String cbAll =getIntent().getStringExtra("yourBoolName");
//        Toast.makeText(this, ""+cbAll, Toast.LENGTH_SHORT).show();


//        expanAdapter.setOnAllCheckedBoxNeedChangeListener(new ExpandListScanAdapter.OnAllCheckedBoxNeedChangeListener() {
//            @Override
//            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
//                Toast.makeText(KeranjangDetailActivity.this, ""+allParentIsChecked, Toast.LENGTH_SHORT).show();
//                cb_select_all.setChecked(allParentIsChecked);
//            }
//        });

//        expanAdapter.setOnGoodsCheckedChangeListener(new ExpandListScanAdapter.OnGoodsCheckedChangeListener() {
//            @SuppressLint("StringFormatMatches")
//            @Override
//            public void onGoodsCheckedChange(double totalHarga) {
////                st = new StringTokenizer(formatRupiah.format(totalHarga), ",");
////                String harganya = st.nextToken().trim();
////                tvx_total.setText(harganya);
//            }
//        }) ;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvx_checkout:
                Toast.makeText(this, "klik", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }

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

//                        double totalnya = response.body().getTotalHarganya();
//                        st = new StringTokenizer(formatRupiah.format(totalnya), ",");
//                        String splitotal = st.nextToken().trim();
//                        tvx_total.setText(splitotal);

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
                                    response.body().getDataKeranjang().get(i).getNamaToko(), true));

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


                                child.add(new ChildModel(idKeranjang, namaProduk, hargaJual, diskon, jumlah, foto, stok, true));
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

                        double totalnya = response.body().getTotalHarganya();
                        st = new StringTokenizer(formatRupiah.format(totalnya), ",");
                        String splitotal = st.nextToken().trim();
                        tvx_total.setText(splitotal);

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
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String qty = intent.getStringExtra("total");

            hargaTotal = Double.parseDouble(qty);
            st = new StringTokenizer(formatRupiah.format(hargaTotal), ",");
            String harganya = st.nextToken().trim();
            tvx_total.setText(harganya);

            if (harganya.equalsIgnoreCase("Rp0")) {
                Drawable d = getResources().getDrawable(R.drawable.button_rect_transparant);
                tvx_checkout.setBackground(d);
            } else {
                Drawable d = getResources().getDrawable(R.drawable.button_rect);
                tvx_checkout.setBackground(d);
            }


        }
    };

    public BroadcastReceiver Receivercheck = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String valParent = intent.getStringExtra("valParent");
//            Toast.makeText(context, "val "+valParent, Toast.LENGTH_SHORT).show();
            if (valParent.equalsIgnoreCase("false")) {
                cb_select_all.setChecked(false);
            } else {
                cb_select_all.setChecked(true);
            }


        }
    };


}