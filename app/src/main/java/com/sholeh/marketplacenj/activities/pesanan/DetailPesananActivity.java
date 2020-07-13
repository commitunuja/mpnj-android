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


        Intent i = getIntent();
        namaproduk = i.getStringExtra("namaproduk");
        namatoko = i.getStringExtra("namatoko");
        harga = i.getStringExtra("harga");
        foto = i.getStringExtra("foto");

//        vnamaproduk = findViewById(R.id.tv_nama_produk_detail);
//        vharga = findViewById(R.id.tv_harga_produk_detail);
//        iproduk = findViewById(R.id.img_produk_detail);

//        vnamaproduk.setText(""+ namaproduk);
//        vharga.setText(""+harga);

//        Glide.with(this)
//                .load(foto)
//                .into(iproduk);


    }
}