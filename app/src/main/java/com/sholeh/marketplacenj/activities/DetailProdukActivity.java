package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;

public class DetailProdukActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG ="productDetails";
    ImageView imageDetailProduk, imgadd_tokeranjang;
    TextView nm_produk, hrg_produk, stok, terjual, deskripsi;
    String namaproduk, urltoimage, vdeskripsi;
    int vhargaproduk, vstok, vterjual, vid_produk;
    Toolbar toolBarisi;

    private Menu mainmenu;



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
        imgadd_tokeranjang = findViewById(R.id.add_to_keranjang);
        imgadd_tokeranjang.setOnClickListener(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mainmenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.menu_keranjang){
            Intent intent = new Intent(this, KeranjangActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_to_keranjang:
//                SharePreferenceUtils.getInstance().saveString(CONSTANTS.QUOTE_ID, "");
//                SharePreferenceUtils.getInstance().saveInt( CONSTANTS.CART_ITEM_COUNT,   SharePreferenceUtils.getInstance().getInteger(CONSTANTS.CART_ITEM_COUNT) +1);
//                AppUtilits.UpdateCartCount(mainmenu);
                break;

                default:
                    break;
        }
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
