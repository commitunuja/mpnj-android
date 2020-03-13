package com.sholeh.marketplacenj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity2;

import java.util.List;

public class DetailProdukActivity extends AppCompatActivity {

    private String TAG = "productDetails";
    ImageView imageDetailProduk, imgadd_tokeranjang;
    TextView nm_produk, hrg_produk, stok, terjual, deskripsi, id;
    String namaproduk, urltoimage, vdeskripsi, vid_produk;
    int vhargaproduk, vstok, vterjual;
    Toolbar toolBarisi;
    private Model tvDataProduk;
    private List<Model> tvDataProduks; // model / item
    private Menu mainmenu;


    // baru
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        imgadd_tokeranjang = findViewById(R.id.add_to_keranjang);
        id = findViewById(R.id.tv_id);

        vid_produk = getIntent().getStringExtra("id_produk");
        String.valueOf(vid_produk);
        namaproduk = getIntent().getExtras().getString("nama_produk");
        urltoimage = getIntent().getExtras().getString("foto_produk");
        vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        vstok = Integer.parseInt(getIntent().getStringExtra("stok"));
        vterjual = Integer.parseInt(getIntent().getStringExtra("terjual"));
        vdeskripsi = getIntent().getExtras().getString("keterangan");

        nm_produk.setText(namaproduk);
        hrg_produk.setText(String.valueOf(vhargaproduk));
        stok.setText(String.valueOf(vstok));
        terjual.setText(String.valueOf(vterjual));
        deskripsi.setText(vdeskripsi);
        Glide.with(getApplicationContext()).load(urltoimage).into(imageDetailProduk);

        imgadd_tokeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProdukActivity.this, KeranjangActivity.class);
                intent.putExtra("nama_produk", namaproduk);
                intent.putExtra("harga_jual", String.valueOf(vhargaproduk));
                intent.putExtra("foto_produk", urltoimage);
                intent.putExtra("id_produk", String.valueOf(vid_produk));
                Toast.makeText(DetailProdukActivity.this, "" + (vid_produk), Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mainmenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_keranjang) {
            Intent intent = new Intent (DetailProdukActivity.this, KeranjangDetailActivity2.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_to_keranjang:
               *//* SharePreferenceUtils.getInstance().saveString(CONSTANTS.QUOTE_ID, "");
                SharePreferenceUtils.getInstance().saveInt( CONSTANTS.CART_ITEM_COUNT,   SharePreferenceUtils.getInstance().getInteger(CONSTANTS.CART_ITEM_COUNT) +1);
                AppUtilits.UpdateCartCount(mainmenu); //dihapus karna berpengaruh ke method keranjang*//*
               keranjang(model);
                break;
            default:
                break;
        }
    }*/


    /*  intent.putExtra("id_produk",String.valueOf(model.getIdProduk()));*/
//        intent.putExtra("nama_produk", model.getNamaProduk());
//        intent.putExtra("foto_produk",CONSTANTS.BASE_URL + "assets/foto_produk/"+model.getFoto().get(0).getFotoProduk());
//        intent.putExtra("harga_jual", String.valueOf(model.getHargaJual()));
//        startActivity(intent);
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



