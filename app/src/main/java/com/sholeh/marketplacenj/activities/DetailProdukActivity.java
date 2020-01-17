package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.ProdukAdapter;
import com.sholeh.marketplacenj.model.Model;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProdukActivity extends AppCompatActivity {


    ImageView imageDetailProduk;
    TextView nm_produk, hrg_produk;
    String namaproduk, urltoimage;
    int hargaproduk;
    Toolbar toolBarisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailproduk);

        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Detail Barang");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageDetailProduk = findViewById(R.id.image_detail);
        nm_produk = findViewById(R.id.tv_detailnamaprod);
        hrg_produk = findViewById(R.id.tv_detailHarga);

        namaproduk = getIntent().getExtras().getString("nama_produk");
        nm_produk.setText(namaproduk);
        urltoimage = getIntent().getExtras().getString("foto_produk");
        Glide.with(getApplicationContext()).load(urltoimage).into(imageDetailProduk);
        hargaproduk =  Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        hrg_produk.setText(String.valueOf(hargaproduk));



//        Glide.with(this)
//                .load(CONSTANTS.BASE_URL + "assets/foto_produk/" +i.getStringExtra(GAMBAR)).into(imageDetailProduk);

//        Glide.with(this)
//                .load(CONSTANTS.BASE_URL + "assets/foto_produk/" +i.getStringExtra(GAMBAR)).into(imageDetailProduk)
//                .placeholder(R.drawable.load)
//                .into(imageView);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }




}
