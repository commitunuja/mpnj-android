package com.sholeh.marketplacenj.activities.keranjang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.activities.transaksi.MetodePembayaranActivity;
import com.sholeh.marketplacenj.adapter.checkout.ExpandAdapterCheckout;
import com.sholeh.marketplacenj.adapter.keranjang.ExpandListScanAdapter;
import com.sholeh.marketplacenj.model.checkout.ChildCheckout;
import com.sholeh.marketplacenj.model.checkout.HeaderCheckout;
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
import java.util.Arrays;
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
    private TextView tvx_total, tvx_checkout, tvx_idk;
    List<ChildModel> child;
    public CheckBox cb_select_all, cbchild;

    Preferences preferences;
    String id_konsumen, idkeranjang;
    int hargaJual;
    private double hargaTotal;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st;
    private boolean cbAll;
    List<String> list;
    ImageView imgBack;

    String CUSTOM_ACTION = "com.example.YOUR_ACTION";
    BroadcastReceiver myReceiver;

    StringBuilder id = new StringBuilder();
    String[] extras;
    ArrayList<List<String>> myArray = new ArrayList<>();

    ArrayList<String> arrayIdKeranjang;
    String idkk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        tvx_total = findViewById(R.id.total);
        tvx_checkout = findViewById(R.id.tvx_checkout);

        listView = findViewById(R.id.expListhistori);
        imgBack = findViewById(R.id.imgBackKeranjang);
        tvx_checkout.setOnClickListener(this);
        imgBack.setOnClickListener(this);


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
                String harganya = tvx_total.getText().toString();
                if (harganya.equalsIgnoreCase("Rp0")) {
                    Drawable d = getResources().getDrawable(R.drawable.button_rect);
                    tvx_checkout.setBackground(d);
                } else if (!harganya.equalsIgnoreCase("Rp0")) {
                    Drawable d = getResources().getDrawable(R.drawable.button_rect_transparant);
                    tvx_checkout.setBackground(d);
                }

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


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
        LocalBroadcastManager.getInstance(this).registerReceiver(Receivercheck,
                new IntentFilter("custom-cball"));
        LocalBroadcastManager.getInstance(this).registerReceiver(receiveridkeranjang,
                new IntentFilter("custom-idk"));
//
//        LocalBroadcastManager.getInstance(this).registerReceiver(rec,
//                new IntentFilter("custom"));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvx_checkout:
                String harganya = tvx_total.getText().toString();
                if (harganya.equalsIgnoreCase("Rp0")) {

                } else if (!harganya.equalsIgnoreCase("Rp0")) {


                    goChekout();


//                    startActivity(new Intent(this, CheckoutActivity.class));
//                    finish();


                }
                break;
            case R.id.imgBackKeranjang:
                finish();
                break;

            default:
                break;
        }

    }


    public void getDetailKeranjang() {

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang(id_konsumen);

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

        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang(id_konsumen);

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

    private BroadcastReceiver receiveridkeranjang = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            idkeranjang = intent.getStringExtra("idkeranjang");
            String[] nomor = idkeranjang.split("\\[");
            String[] nomor2 = nomor[1].split("]");
            String harIDK = "";

            for (int i = 0; i < nomor2.length; i++) {
                harIDK = harIDK + nomor2[i];
            }
            idkk = harIDK;
            String[] yolo = idkk.split(",");
            list = new ArrayList<String>();
            list = Arrays.asList(yolo);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }


    private void goChekout() {
        String id[] = {idkk};
        arrayIdKeranjang = new ArrayList<>();
        for (int a = 0; a < id.length; a++) {
            arrayIdKeranjang.add(id[a]);
        }

        updateStatusProduk();
        Intent goCheckout = new Intent(this, CheckoutActivity.class);
//        goCheckout.putStringArrayListExtra("idcheckout", (ArrayList<String>) list);
        startActivity(goCheckout);
    }


    public void updateStatusProduk() {
        Log.d("array idk", String.valueOf(list));

//        Toast.makeText(this, ""+arrayIdKeranjang, Toast.LENGTH_SHORT).show();

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.ubahStatusKeranjang(id_konsumen, list);


        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, retrofit2.Response<ResDetailKeranjang> response) {

                //   Log.e(TAG, "response is "+ response.body() + "  ---- "+ new Gson().toJson(response.body()));
                //  Log.e(TAG, "  ss sixe 1 ");
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getDataKeranjang().size() > 0) {
//                        Toast.makeText(KeranjangDetailActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    } else {
//                        AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.network_error));
                    }
                } else {
//                    AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.network_error));
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
//
    }

}