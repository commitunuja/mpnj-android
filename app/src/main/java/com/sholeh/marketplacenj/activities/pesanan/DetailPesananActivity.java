package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.pesanan.DetailPesananAdapter;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.model.pesanan.Item;
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.DetailPesanan;
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.ItemDetailPesanan;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class DetailPesananActivity extends AppCompatActivity implements View.OnClickListener {

    String statusorder, namatoko, harga, foto, fotokirimwa, bitmpath;
    TextView vnamaproduk, waktu, totalhargaproduk, total, vharga, toko, status, alamat, totalhargadetail,lacak, tulis, kurir, tongkir, tanya;
    ImageView iproduk, imgcoba;
    private List<ItemDetailPesanan> dataPesanans;
    RecyclerView recyclerView;
    private HashMap<DataPesanan, List<Item>> item;
    String kode, pack;
    DetailPesananAdapter recyclerdetailpesanan;
    RecyclerView.LayoutManager layoutManager;
    Bitmap bitmap;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer stringTokenizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        lacak = findViewById(R.id.tvlacak);
        tulis = findViewById(R.id.tvtulis);
        tanya = findViewById(R.id.tv_tanya);
        totalhargadetail = findViewById(R.id.totalhargadetail);
        vnamaproduk = findViewById(R.id.tv_nama_produk_detail);
        vharga = findViewById(R.id.tv_harga_produk_detail);
        iproduk = findViewById(R.id.img_produk_detail);
        recyclerView = findViewById(R.id.recycler_pesanan_detail);
        toko = findViewById(R.id.tv_toko);
        waktu = findViewById(R.id.tv_waktupembelian);
        status = findViewById(R.id.tv_status_order_detail);
        totalhargaproduk = findViewById(R.id.tv_totalhargaproduk);
        total = findViewById(R.id.tv_total_detail_bayar);
        alamat = findViewById(R.id.tv_alamat_kirim);
        kurir = findViewById(R.id.tv_kurir);
        tongkir = findViewById(R.id.tv_ongkir);
        Intent i = getIntent();
        kode = i.getStringExtra("kode");
        tanya.setOnClickListener(this);
        tulis.setOnClickListener(this);
        lacak.setOnClickListener(this);

        getData();

    }


    public void getData() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<DetailPesanan> call = service.getDataDetailPesanan(String.valueOf(kode));

        dataPesanans = new ArrayList<>();
        item = new HashMap<>();
        call.enqueue(new Callback<DetailPesanan>() {
            @Override
            public void onResponse(Call<DetailPesanan> call, retrofit2.Response<DetailPesanan> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {

                        dataPesanans.clear();
                        item.clear();
                        List<ItemDetailPesanan> array = response.body().getData();
                        for (int i = 0; i < array.size(); i++) {

                            dataPesanans.add(new ItemDetailPesanan(response.body().getData().get(i).getNamaToko(),
                                    response.body().getData().get(i).getNamaProduk(),
                                    response.body().getData().get(i).getJumlah(),
                                    response.body().getData().get(i).getTotalBayar(),
                                    response.body().getData().get(i).getHarga(),
                                    response.body().getData().get(i).getFotoProduk(),
                                    response.body().getData().get(i).getStatusOrder(),
                                    response.body().getData().get(i).getBayarSebelum(),
                                    response.body().getData().get(i).getIdTransaksiDetail(),
                                    response.body().getData().get(i).getKurir(),
                                    response.body().getData().get(i).getOngkir(),
                                    response.body().getData().get(i).getWaktuPesan()));



                            toko.setText(response.body().getData().get(i).getNamaToko());
                            waktu.setText(response.body().getData().get(i).getWaktuPesan());
                            status.setText(response.body().getData().get(i).getStatusOrder());
                            kurir.setText(response.body().getData().get(i).getKurir().toString());

                            int ongkir = Integer.parseInt((response.body().getData().get(i).getOngkir()));
                            stringTokenizer = new StringTokenizer(formatRupiah.format(ongkir), ",");
                            String hargaongkir = stringTokenizer.nextToken().trim();
                            tongkir.setText(hargaongkir);

                            statusorder = response.body().getData().get(i).getStatusOrder();
                            int total1 = Integer.parseInt(response.body().getData().get(i).getTotalBayar());
                            stringTokenizer = new StringTokenizer(formatRupiah.format(total1), ",");
                            String totalbayar = stringTokenizer.nextToken().trim();
                            total.setText(totalbayar);
                            alamat.setText(response.body().getData().get(i).getTujuan());

                            int ongkirlagi = Integer.parseInt(response.body().getData().get(i).getOngkir());
                            int harga2 = Integer.parseInt(response.body().getData().get(i).getTotalBayar());
                            int harga = harga2-ongkirlagi;
                            stringTokenizer = new StringTokenizer(formatRupiah.format(harga), ",");
                            String harga1 = stringTokenizer.nextToken().trim();
                            totalhargadetail.setText(harga1);

                            if (statusorder.equals("Telah Sampai")) {
                                tanya.setVisibility(View.VISIBLE);
                                tulis.setVisibility(View.VISIBLE);
                            } else if (statusorder.equals("Dikirim")) {
                                tanya.setVisibility(View.VISIBLE);
                                tulis.setVisibility(View.VISIBLE);
                                lacak.setVisibility(View.VISIBLE);
                            }else if(statusorder.equals("Dibatalkan")){
                                tanya.setVisibility(View.VISIBLE);
                                lacak.setVisibility(View.GONE);
                                tulis.setVisibility(View.GONE);
                            } else {
                                tanya.setVisibility(View.VISIBLE);
                                tulis.setVisibility(View.GONE);

                            }
                        }

                    } else {

                    }
                    recyclerdetailpesanan = new DetailPesananAdapter(getContext(), dataPesanans);
                    recyclerView.setAdapter(recyclerdetailpesanan);
                }
            }

            @Override
            public void onFailure(Call<DetailPesanan> call, Throwable t) {

            }
        });

    }

    public void openWA(View view) {
        String number = "+6281236706224";
//        String text = "coba lun";
        String url = "https://api.whatsapp.com/send?phone="+number;
//        String url = "https://api.whatsapp.com/send?phone=+&text=Halo%20mau%20order%20bajunya%20yang%20warna%20biru%20gan";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }
//
//
//    public void onClickWhatsApp(View view) {
//
//        PackageManager pm=getPackageManager();
//        try {
//
//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.setType("text/plain");
//            String text = "YOUR TEXT HERE";
//
//
//            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//            //Check if package exists or not. If not then code
//            //in catch block will be called
//            waIntent.setPackage("com.whatsapp");
//
//            waIntent.putExtra(Intent.EXTRA_TEXT, text);
//            startActivity(Intent.createChooser(waIntent, "Share with"));
//
//        } catch (PackageManager.NameNotFoundException e) {
//            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
//                    .show();
//        }
//
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tanya:
             openWA(v);
             break;
            case R.id.tvtulis:
                status.setText("Selesai");
                break;
            case R.id.tvlacak:
                Intent intent = new Intent(DetailPesananActivity.this, TrackingActivity.class);
                intent.putExtra("kode", kode);
                startActivity(intent);
        }
    }
}