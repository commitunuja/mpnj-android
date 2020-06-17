package com.sholeh.marketplacenj.activities.checkout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.adapter.checkout.ExpandAdapterCheckout;
import com.sholeh.marketplacenj.model.checkout.ChildCheckout;
import com.sholeh.marketplacenj.model.checkout.HeaderCheckout;
import com.sholeh.marketplacenj.model.cost.Rajaongkir;
import com.sholeh.marketplacenj.respon.DataKeranjang;
import com.sholeh.marketplacenj.respon.ItemKeranjang;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvxtolbar, tvxUbahAlamat, tvxSetAlamat, tvxPilihBank, tvx_idKecPembeli;
    Preferences preferences;
    String id_konsumen;
    private List<HeaderCheckout> listHeader;
    private HashMap<HeaderCheckout, List<ChildCheckout>> listChild;
    List<ChildCheckout> child;
    int hargaJual;
    private ExpandableListView listView;
    private ExpandAdapterCheckout expanAdapter;
    ImageView imgBack;

    ArrayList<String> arrayIdKeranjang;

    private Rajaongkir rajaongkircost;

    public String getidKec;
    private Intent i;
//    List<ResDetailKeranjang> resDetailKeranjangs;
    ResDetailKeranjang resDetailKeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        tvxtolbar = findViewById(R.id.txt_toolbarKeranjang);
        tvxUbahAlamat = findViewById(R.id.tvxubahAlamat);
        tvxSetAlamat = findViewById(R.id.tvx_setAlamat);
        imgBack = findViewById(R.id.imgBackKeranjang);
        tvx_idKecPembeli = findViewById(R.id.tvx_idKecPembeli);
//        tvxPilihBank = findViewById(R.id.tv_pilihbank);
        tvxtolbar.setText("Checkout");
        listView = findViewById(R.id.expand_checkout);

        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();


        Intent i = getIntent();
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

        tvxUbahAlamat.setOnClickListener(this);
        imgBack.setOnClickListener(this);
//        tvxPilihBank.setOnClickListener(this);
        getDetailKeranjang();

//        i = getIntent();
//        getidKec = tvx_idKecPembeli.getText().toString();
//        i.putExtra("idkecamatan", getidKec);


    }

    public ResDetailKeranjang getbs(){
        return resDetailKeranjang;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvxubahAlamat:
                startActivity(new Intent(this, AlamatActivity.class));
                break;

            case R.id.imgBackKeranjang:
                onBack();
                break;

//            case R.id.tv_pilihbank:
//                startActivity(new Intent(this, MetodePembayaranActivity.class));
//
//                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
       onBack();
    }

    public void onBack(){
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Apakah Anda yakin ingin membatalkan checkout?")
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Tidak", null).show();
    }



    public void getDetailKeranjang() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.getDataTransaksi(id_konsumen);

        listHeader = new ArrayList<>();
        listChild = new HashMap<>();
        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, retrofit2.Response<ResDetailKeranjang> response) {
                Log.d("cekkk", String.valueOf(response));

//                String destination = rajaongkir.getQuery().getDestination();
//                Toast.makeText(this, ""+destination, Toast.LENGTH_SHORT).show();


                //   Log.e(TAG, "response is "+ response.body() + "  ---- "+ new Gson().toJson(response.body()));
                //  Log.e(TAG, "  ss sixe 1 ");
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getDataKeranjang().size() > 0) {
                        response.body().getTotalHarganya();
                       tvxSetAlamat.setText(String.valueOf(response.body().getPembeli().getAlamatUtama()));
                       String idKecPembeli = String.valueOf(response.body().getPembeli().getIdKecamatan());
                       tvx_idKecPembeli.setText(idKecPembeli);

                        listHeader.clear();
                        listChild.clear();
                        List<DataKeranjang> array = response.body().getDataKeranjang();
                        resDetailKeranjang = response.body();

                        for (int i = 0; i < array.size(); i++) {
                            listHeader.add(new HeaderCheckout(
                                    response.body().getDataKeranjang().get(i).getIdToko(),
                                    response.body().getDataKeranjang().get(i).getNamaToko(),
                                    response.body().getDataKeranjang().get(i).getIdKabupaten()));


//                            Toast.makeText(CheckoutActivity.this, ""+response.body().getDataKeranjang().get(0).getNamaKota(), Toast.LENGTH_SHORT).show();


                            child = new ArrayList<>();
                            List<ItemKeranjang> childLink = array.get(i).getItem();
                            for (int j = 0; j < childLink.size(); j++) {
                                String idKeranjang = childLink.get(j).getIdKeranjang();
                                String namaProduk = childLink.get(j).getNamaProduk();
                                hargaJual = Integer.parseInt(String.valueOf(childLink.get(j).getHargaJual()));
                                int diskon = Integer.parseInt((childLink.get(j).getDiskon()));
                                int jumlah = Integer.parseInt(String.valueOf(childLink.get(j).getJumlah()));
                                String foto = childLink.get(j).getFoto();

                                child.add(new ChildCheckout(idKeranjang, namaProduk, hargaJual, diskon, jumlah, foto));
                            }
                            listChild.put(listHeader.get(i), child);
                        }
                        expanAdapter = new ExpandAdapterCheckout(CheckoutActivity.this, listHeader, listChild);
                        listView.setAdapter(expanAdapter);


                        int count = expanAdapter.getGroupCount();
                        for (int i = 0; i < count; i++) {
                            listView.expandGroup(i);
                        }

                    } else {
                        Toast.makeText(CheckoutActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
//                        AppUtilits.displayMessage(CheckoutActivity.this, getString(R.string.network_error));
                    }
                } else {
                    Toast.makeText(CheckoutActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
//                    AppUtilits.displayMessage(CheckoutActivity.this, getString(R.string.network_error));
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



}
