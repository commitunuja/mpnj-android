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
    TextView nm_produk, hrg_produk, stok, terjual, deskripsi;
    String namaproduk, urltoimage, vdeskripsi;
    int vhargaproduk, vstok, vterjual, vid_produk;
    Toolbar toolBarisi;


// baru
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
        hrg_produk = findViewById(R.id.tv_detailHargaJual);
        stok = findViewById(R.id.tv_detailstok);
        terjual = findViewById(R.id.tv_detailterjual);
        deskripsi = findViewById(R.id.tv_detaildeskripsi);

        vid_produk =  Integer.parseInt(getIntent().getStringExtra("id_produk"));
        namaproduk = getIntent().getExtras().getString("nama_produk");
        urltoimage = getIntent().getExtras().getString("foto_produk");
        vhargaproduk =  Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        vstok =  Integer.parseInt(getIntent().getStringExtra("stok"));
        vterjual =  Integer.parseInt(getIntent().getStringExtra("terjual"));
        vdeskripsi = getIntent().getExtras().getString("keterangan");


        nm_produk.setText(namaproduk);
        hrg_produk.setText(String.valueOf(vhargaproduk));
        stok.setText(String.valueOf(vstok));
        terjual.setText(String.valueOf(vterjual));
        deskripsi.setText(vdeskripsi);
        Glide.with(getApplicationContext()).load(urltoimage).into(imageDetailProduk);



    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

//    private void loadDetail(Integer ProdukId) {
//
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<Model>> call = service.getIdProdukDetail(ProdukId);
//
//        call.enqueue(new Callback<List<User>>() {
//
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (response.code() == 200) {
//                    users.addAll(response.body());
//                    adapter.setData(users);
//                }
//                Toast.makeText(getApplicationContext(), "status: " + response.code() + " list size: " + users.size(), Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Log.e("e", t.toString());
//            }
//        });




}
