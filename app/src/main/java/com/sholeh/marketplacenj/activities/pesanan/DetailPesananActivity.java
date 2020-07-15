package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.sholeh.marketplacenj.util.Preferences;
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

public class DetailPesananActivity extends AppCompatActivity {

    String namaproduk, namatoko, harga, foto;
    TextView vnamaproduk, waktu, totalhargaproduk, total, vharga, toko, status, alamat, totalhargadetail, kurir;
    ImageView iproduk;
    private List<ItemDetailPesanan> dataPesanans;
    RecyclerView recyclerView;
    private HashMap<DataPesanan, List<Item>> item;
    String kode;
    DetailPesananAdapter recyclerdetailpesanan;
    RecyclerView.LayoutManager layoutManager;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer stringTokenizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

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
        alamat = findViewById(R.id.tv_alamat_detail_pesanan);
        kurir = findViewById(R.id.tv_kurir);
        Intent i = getIntent();
        kode = i.getStringExtra("kode");


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

                            int total1 = Integer.parseInt(response.body().getData().get(i).getTotalBayar());
                            stringTokenizer = new StringTokenizer(formatRupiah.format(total1),",");
                            String totalbayar = stringTokenizer.nextToken().trim();
                            total.setText(totalbayar);
                            alamat.setText(response.body().getData().get(i).getTujuan());
                            int harga = Integer.parseInt(response.body().getData().get(i).getHarga());
                            stringTokenizer = new StringTokenizer(formatRupiah.format(harga),",");
                            String harga1 = stringTokenizer.nextToken().trim();
                            totalhargadetail.setText(harga1);


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
}