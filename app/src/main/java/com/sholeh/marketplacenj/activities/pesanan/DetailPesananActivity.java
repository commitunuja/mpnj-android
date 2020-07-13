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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class DetailPesananActivity extends AppCompatActivity {

    String namaproduk, namatoko, harga, foto;
    TextView vnamaproduk, waktu, totalhargaproduk, total, vharga, toko, status, alamat;
    ImageView iproduk;
    private List<ItemDetailPesanan> dataPesanans;
    RecyclerView recyclerView;
    private HashMap<DataPesanan, List<Item>> item;
    String kode;
    DetailPesananAdapter recyclerdetailpesanan;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);


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
        Intent i = getIntent();
        kode = i.getStringExtra("kode");

        harga = i.getStringExtra("harga");
        foto = i.getStringExtra("foto");
    public void getData() {
        String id_konsumen;
        Preferences preferences = new Preferences(getContext());
        id_konsumen = preferences.getIdKonsumen();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

//        vnamaproduk = findViewById(R.id.tv_nama_produk_detail);
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
                            total.setText("Rp " +response.body().getData().get(i).getTotalBayar());
                            alamat.setText(response.body().getData().get(i).getTujuan());

                    recyclerdetailpesanan = new DetailPesananAdapter(getContext(), dataPesanans);
                    recyclerView.setAdapter(recyclerdetailpesanan);

    }
}