package com.sholeh.marketplacenj.activities.checkout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.JsonObject;
//import com.kaopiz.kprogresshud.KProgressHUD;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.activities.alamat.PilihAlamatCheckout;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.activities.transaksi.KonfirmasiPembayaranActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvxtolbar, tvxUbahAlamat, tvxSetAlamat, tvxPilihBank, tvx_idKecPembeli, tvxtotalCheckout, tvxSubtotalProd, tvxsubPengiriman, tvxValsubOngkir1, tvxValsubOngkir2, tvxBayar;
    Preferences preferences;
    String id_konsumen, cekOngkir;
    private List<HeaderCheckout> listHeader;
    private HashMap<HeaderCheckout, List<ChildCheckout>> listChild;
    List<ChildCheckout> child;
    int hargaJual;
    private ExpandableListView listView;
    private ExpandAdapterCheckout expanAdapter;
    ImageView imgBack;

    boolean valKlikPilihAlamat = false;

    private double hargaTotal;
    private double hargaPengiriman = 0;
    private double totalbayar = 0;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, stsub, sttotal;
    String arrayIdKeranjang;
    String idkk;
    String mykk;
    ArrayList<String> arraymykk;
    List<String> list;
    ArrayList<String> idK;
    private Rajaongkir rajaongkircost;
    String harganya;
    public String getidKec;
    private Intent i;
    //    List<ResDetailKeranjang> resDetailKeranjangs;
    ResDetailKeranjang resDetailKeranjang;
    ProgressBar pbCheckout;
    String ongkir, resetKurir;

    private KProgressHUD progressHUD;
    LinearLayout lnEmpty;

    String alamatCheckout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        progressHUD = KProgressHUD.create(this);
        lnEmpty = findViewById(R.id.lnKosong);

        tvxBayar = findViewById(R.id.tvxBayar);

        tvxtolbar = findViewById(R.id.txt_toolbarKeranjang);
        tvxUbahAlamat = findViewById(R.id.tvxubahAlamat);
        tvxSetAlamat = findViewById(R.id.tvx_setAlamat);
        imgBack = findViewById(R.id.imgBackKeranjang);
        tvxtotalCheckout = findViewById(R.id.totalchechkout);
        tvxsubPengiriman = findViewById(R.id.tvx_subtotalPengiriman);
        tvxValsubOngkir1 = findViewById(R.id.tvx_valsubOngkir1);
        tvxValsubOngkir2 = findViewById(R.id.tvx_valsubOngkir2);
        tvxSubtotalProd = findViewById(R.id.tvx_subtotalProduk);
        tvx_idKecPembeli = findViewById(R.id.tvx_idKecPembeli);
        tvxBayar = findViewById(R.id.tvxBayar);

//        tvxPilihBank = findViewById(R.id.tv_pilihbank);
        tvxtolbar.setText("Checkout");
        listView = findViewById(R.id.expand_checkout);

        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

//        alamatCheckout = getIntent().getStringExtra("alamat_lengkap");
//        Log.d("cek address", String.valueOf(alamatCheckout));


        Intent i = getIntent();
//        ongkir = i.getStringExtra("ongkir"); // from opsi pengiriman
        resetKurir = i.getStringExtra("reset_kurir");
//        Toast.makeText(this, "ongkir "+ongkir, Toast.LENGTH_SHORT).show();
        idK = i.getStringArrayListExtra("idcheckout"); //from keranjang detail
        arrayIdKeranjang = String.valueOf(i.getStringArrayListExtra("idcheckout"));

//        Toast.makeText(this, "idk"+idK+ " id"+arrayIdKeranjang, Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, ""+arrayIdKeranjang, Toast.LENGTH_SHORT).show();

        String[] nomor = arrayIdKeranjang.split("\\[");
        String[] nomor2 = nomor[1].split("]");
        String harIDK = "";

        for (int j = 0; j < nomor2.length; j++) {
            harIDK = harIDK + nomor2[j];
        }
        idkk = harIDK;
        String[] yolo = idkk.split(",");
        list = new ArrayList<String>();
        list = Arrays.asList(yolo);
//        Log.d("YOLO", String.valueOf(arrayIdKeranjang));
//        Toast.makeText(this, String.valueOf(arrayIdKeranjang), Toast.LENGTH_SHORT).show();

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

        tvxUbahAlamat.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        tvxBayar.setOnClickListener(this);
//        tvxPilihBank.setOnClickListener(this);
        getDetailKeranjang();

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true; // mencegah parent klik
            }
        });

//        i = getIntent();
//        getidKec = tvx_idKecPembeli.getText().toString();
//        i.putExtra("idkecamatan", getidKec);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageOngkir,
                new IntentFilter("custom-ongkir"));

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessagebayar,
                new IntentFilter("custom-total"));

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageValidasiOpsi,
                new IntentFilter("custom-validasiopsi"));

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageValidasiOpsi,
                new IntentFilter("custom-validasiopsi2"));

    }

    private void ProgresDialog() {
        progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Proses...")
                .setCancellable(false);
        progressHUD.show();
    }

    public ResDetailKeranjang getbs() {
        return resDetailKeranjang;
    }

    public ArrayList<String> listIdKeranjang() {
        return idK;
    }

    public String resetKurir() {
        return resetKurir;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvxubahAlamat:
//                Toast.makeText(this, "cek "+cekOngkir, Toast.LENGTH_SHORT).show();
                goPilihAlamat();
//                valKlikPilihAlamat = true;

//                finish();
                break;

            case R.id.imgBackKeranjang:
                onBack();
                finish();
                break;

            case R.id.tvxBayar:

//                pbCheckout.setVisibility(View.VISIBLE);
                simpanTransaksi();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    private void goPilihAlamat() {
//        String id[] = {idkk};
//        arrayIdKeranjang = new ArrayList<>();
//        for (int a = 0; a < id.length; a++) {
//            arrayIdKeranjang.add(id[a]);
//        }

//        updateStatusProduk();
//        startActivity(new Intent(this, PilihAlamatCheckout.class));
        Intent goPilihAlamat = new Intent(this, PilihAlamatCheckout.class);
//        goPilihAlamat.putStringArrayListExtra("idcheckout", arrayIdKeranjang);
        goPilihAlamat.putStringArrayListExtra("idcheckout", idK);
        goPilihAlamat.putExtra("cekongkir", cekOngkir);
        startActivity(goPilihAlamat);


    }

    public void onBack() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Apakah Anda yakin ingin membatalkan checkout?")
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        batalChekout();
                    }
                }).setNegativeButton("Tidak", null).show();
    }

    private void simpanTransaksi() {
        String subval1 = tvxValsubOngkir1.getText().toString();
        if (subval1.equals("Rp0")) {
            Toast.makeText(this, "Lengkapi Pengiriman Produk Anda", Toast.LENGTH_SHORT).show();
        } else {
        ProgresDialog();
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<JsonObject> call = service.simpanTransaksi(id_konsumen, totalbayar, list);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("simpantransaksi", String.valueOf(response));
//                Toast.makeText(CheckoutActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject;
                    jsonObject = new JSONObject(String.valueOf(response.body()));
                    Integer id_transaksi = (Integer) jsonObject.get("id_transaksi");
                    Integer kodetransaksi = (Integer) jsonObject.get("kode_transaksi");
                    String total_bayar = (String) jsonObject.get("total_bayar");
                    String tgl_pemesanan = (String) jsonObject.get("tanggal_pemesanan");
                    String batasPembayaran = (String) jsonObject.get("batas_pembayaran");


//                    String totalbayar = (String) jsonObject.get("total_bayar");
                    Log.d("kodetransaksi", String.valueOf(id_transaksi)+"/t"+kodetransaksi+"/t"+totalbayar+"/t"+tgl_pemesanan+"/t"+batasPembayaran);
                    progressHUD.dismiss();
                    Intent intent = new Intent(CheckoutActivity.this, KonfirmasiPembayaranActivity.class);
                    Bundle b = new Bundle();
                    b.putDouble("totalbayar", totalbayar);

                    b.putInt("id_transaksi", id_transaksi);
                    b.putInt("kodetransaksi", kodetransaksi);
                    b.putString("total", total_bayar);
                    b.putString("tanggal_pemesanan", tgl_pemesanan);
                    b.putString("batas_pembayaran", batasPembayaran);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressHUD.dismiss();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("simpantransaksi", String.valueOf(t));
                progressHUD.dismiss();
            }
        });
        }
    }

    public void batalChekout() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<JsonObject> call = service.batalCheckout(id_konsumen);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("batalc", String.valueOf(response.body() + response.message()));

//                if (response.isSuccessful()) {

                Intent intent = new Intent(CheckoutActivity.this, KeranjangDetailActivity.class);
                startActivity(intent);
                finish();
//                } else {
//                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(CheckoutActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

//                Toast.makeText(CheckoutActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetailKeranjang() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.getDataTransaksi(id_konsumen, list);

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
                        listHeader.clear();
                        listChild.clear();
                        alamatCheckout = String.valueOf(response.body().getPembeli().getAlamatUtama());
                        tvxSetAlamat.setText(alamatCheckout);
                        String idKecPembeli = String.valueOf(response.body().getPembeli().getIdKecamatan());
                        tvx_idKecPembeli.setText(idKecPembeli);
//                        Log.d("alamatutama", String.valueOf(response.body().getPembeli().getAlamatUtama())+" \n"+idKecPembeli);


                        List<DataKeranjang> array = response.body().getDataKeranjang();
                        resDetailKeranjang = response.body();

                        for (int i = 0; i < array.size(); i++) {
                            listHeader.add(new HeaderCheckout(
                                    response.body().getDataKeranjang().get(i).getIdToko(),
                                    response.body().getDataKeranjang().get(i).getNamaToko(),
                                    response.body().getDataKeranjang().get(i).getIdKabupaten(),
                                    response.body().getDataKeranjang().get(i).getKurir(),
                                    response.body().getDataKeranjang().get(i).getService(),
                                    response.body().getDataKeranjang().get(i).getOngkir(),
                                    response.body().getDataKeranjang().get(i).getEtd()));

                            cekOngkir = response.body().getDataKeranjang().get(i).getKurir();


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
                        Toast.makeText(CheckoutActivity.this, "" + response.body(), Toast.LENGTH_SHORT).show();
//                        AppUtilits.displayMessage(CheckoutActivity.this, getString(R.string.network_error));
                    }
                } else {
                    Toast.makeText(CheckoutActivity.this, "" + response.body(), Toast.LENGTH_SHORT).show();
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


    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String qty = intent.getStringExtra("total");
            hargaTotal = Double.parseDouble(qty);
            st = new StringTokenizer(formatRupiah.format(hargaTotal), ",");
            String harganya = st.nextToken().trim();
            tvxSubtotalProd.setText(harganya);
        }
    };

    public BroadcastReceiver mMessageOngkir = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String qty = intent.getStringExtra("ongkir");
            hargaPengiriman = Double.parseDouble(qty);
            stsub = new StringTokenizer(formatRupiah.format(hargaPengiriman), ",");
            String harganya = stsub.nextToken().trim();
            tvxsubPengiriman.setText(harganya);
        }
    };

    public BroadcastReceiver mMessagebayar = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String qty = intent.getStringExtra("totalbayar");
            totalbayar = Double.parseDouble(qty);
            sttotal = new StringTokenizer(formatRupiah.format(totalbayar), ",");
            String harganya = sttotal.nextToken().trim();
            tvxtotalCheckout.setText(harganya);
        }
    };

    public BroadcastReceiver mMessageValidasiOpsi = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String myOngkir = intent.getStringExtra("validasiopsi");
            tvxValsubOngkir1.setText(String.valueOf(myOngkir));
//            String[] nomor = myOngkir.split("\\[");
//            String[] nomor2 = nomor[1].split("]");
//            String harIDK = "";
//
//            for (int i = 0; i < nomor2.length; i++) {
//                harIDK = harIDK + nomor2[i];
//            }
//            mykk = harIDK;
//            String[] yolo = mykk.split(",");
//            list = new ArrayList<String>();
//            list = Arrays.asList(yolo);

//            Toast.makeText(context, ""+idkk, Toast.LENGTH_SHORT).show();
//            Log.d("myongkir", myOngkir);
//            Toast.makeText(context, ""+myOngkir, Toast.LENGTH_SHORT).show();

//            if (myOngkir.equals("[0]")){
//                Toast.makeText(context, "ada", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(context, "tidak ada", Toast.LENGTH_SHORT).show();
//            }
//            String idkecCheckout = intent.getStringExtra("idkeccheckout");
//            Toast.makeText(context, "idkec"+idkecCheckout, Toast.LENGTH_SHORT).show();


//            Intent intentAgain = new Intent("customnewidkec-checkout");
//            intentAgain.putExtra("newidkec",idkecCheckout);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intentAgain);

//            tvxSetAlamat.setText(newGetAlamatCheckoout);
        }
    };

    public BroadcastReceiver mMessageValidasiOpsi2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String myOngkir = intent.getStringExtra("validasiopsi2");
            tvxValsubOngkir2.setText(String.valueOf(myOngkir));
        }
    };
}
