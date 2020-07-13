package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;

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


//        Glide.with(this)
//                .load(foto)
//                .into(iproduk);


    }
}