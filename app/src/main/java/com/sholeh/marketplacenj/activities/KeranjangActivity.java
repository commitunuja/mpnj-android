package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.NewModel;

public class KeranjangActivity extends AppCompatActivity {
    private String TAG = "productDetails";
    public static final String EXTRA_PRODUK = "extra_produk";
    String namaprodukkeranjang, urltoimagekeranjang;
    int vid_produk, vhargaproduk;
    ProgressBar progressBar;
    private Model tvDataProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

//        NewModel model = getIntent().getParcelableExtra(EXTRA_PRODUK);
//        Log.d("yolo", String.valueOf(model));
//        Toast.makeText(this, String.valueOf(model), Toast.LENGTH_SHORT).show();
        TextView nama = findViewById(R.id.tv_name_product);
        TextView harga = findViewById(R.id.tv_hargakeranjang);
        ImageView foto = findViewById(R.id.posterkeranjang);

        namaprodukkeranjang = getIntent().getExtras().getString("nama_produk");
        urltoimagekeranjang = getIntent().getExtras().getString("foto_produk");
        vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        Toast.makeText(this, namaprodukkeranjang, Toast.LENGTH_SHORT).show();
        nama.setText(namaprodukkeranjang);
        harga.setText(String.valueOf(vhargaproduk));
        Glide.with(getApplicationContext()).load(urltoimagekeranjang).into(foto);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(height*.5),(int)(width*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
       /* vid_produk = Integer.parseInt(getIntent().getStringExtra("id_produk"));
        namaprodukkeranjang = getIntent().getExtras().getString("nama_produk");
        urltoimagekeranjang = getIntent().getExtras().getString("foto_produk");
        vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));

        title.setText(namaprodukkeranjang);
        harga.setText(String.valueOf(vhargaproduk));
        Glide.with(this)
                .load(urltoimagekeranjang)
                .into(foto);*/
   /*    showLoading(true);
        if (model != null){
            title.setText(model.getNamaProduk());
            harga.setText(String.valueOf(model.getHargaJual()));
            Glide.with(this)
                    .load(model.getFoto())
                    .into(foto);
       }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }*/

   /* private void showLoading(boolean b) {
     *//*   if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }*/
    }
}
