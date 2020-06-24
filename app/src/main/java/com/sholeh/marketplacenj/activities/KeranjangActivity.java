package com.sholeh.marketplacenj.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.respon.ResKeranjang;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.api.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangActivity extends AppCompatActivity {
    EditText quantityProductPage;
    private String TAG = "productDetails";
    public static final String EXTRA_PRODUK = "extra_produk";
    String namaprodukkeranjang, urltoimagekeranjang, vid_produk;
    int vhargaproduk, idproduk;
    ProgressBar progressBar;
    private Model tvDataProduk;
    private int quantity = 1;
    Button tambahkeranjang, belilagi;
    TextView harga, nama, idkeranjang;
    private ImageView foto;
//    private APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        nama = findViewById(R.id.tv_name_product);
        harga = findViewById(R.id.tv_hargakeranjang);
        foto = findViewById(R.id.posterkeranjang);
        quantityProductPage = findViewById(R.id.quantityProductPage);
        tambahkeranjang = findViewById(R.id.btn_tbhkeranjang);
        belilagi = findViewById(R.id.btn_belilagi);
        idkeranjang = findViewById(R.id.tv_idkeranjang);

        vid_produk = getIntent().getStringExtra("id_produk");
        idkeranjang.setText(String.valueOf(vid_produk));


        namaprodukkeranjang = getIntent().getExtras().getString("nama_produk");
        urltoimagekeranjang = getIntent().getExtras().getString("foto_produk");
        nama.setText(namaprodukkeranjang);
        Glide.with(getApplicationContext()).load(urltoimagekeranjang).into(foto);


        tambahkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jumlah = quantityProductPage.getText().toString();
                String produk_id = idkeranjang.getText().toString();
                String harga_jual = harga.getText().toString();
                Toast.makeText(KeranjangActivity.this, harga_jual, Toast.LENGTH_SHORT).show();

                APIInterface apiKeranjang = CONSTANTS.getClient().create(APIInterface.class);
                Call<ResKeranjang> sendData = apiKeranjang.simpanKeranjang(produk_id, "1", "konsumen", "N",jumlah, harga_jual);
//                Log.d("YOLO", String.valueOf(sendData));
                sendData.enqueue(new Callback<ResKeranjang>() {
                    @Override
                    public void onResponse(Call<ResKeranjang> call, Response<ResKeranjang> response) {

//                        Log.d("RETRO", "respone :" + response.body());
//                            List<Model> kode = response.body();

                        Toast.makeText(KeranjangActivity.this, "ress"+response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResKeranjang> call, Throwable t) {
                        Log.d("RETRO", "Falure :" + "Gagal Mengirim Request");
                    }
                });

            }

        });

        belilagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(KeranjangActivity.this, KeranjangDetailActivity.class);
//                startActivity(intent);
            }
        });

        initialize();
    }

    private void initialize() {
        quantityProductPage.setText("1");
        quantityProductPage.addTextChangedListener(productcount);
//        productprice.setText("₹ " + Float.toString(model.getCardprice()));
    }

    @SuppressLint("SetTextI18n")
    public void decrement(View view) {

        TextView harga = findViewById(R.id.tv_hargakeranjang);
        vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));


        if (quantity > 1) {
            quantity--;
            quantityProductPage.setText(String.valueOf(quantity));

            harga.setText(String.valueOf(vhargaproduk * quantity));

        }
    }

    public void increment(View view) {
        TextView harga = findViewById(R.id.tv_hargakeranjang);
        vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));

        if (quantity < 500) {
            quantity++;
            quantityProductPage.setText(String.valueOf(quantity));
            harga.setText(String.valueOf(vhargaproduk * quantity));

        } else {
            Toast.makeText(this, "Product Count Must be less than 500", Toast.LENGTH_SHORT).show();
        }
    }

    TextWatcher productcount = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (quantityProductPage.getText().toString().equals("")) {
                quantityProductPage.setText("0");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            //none
            if (Integer.parseInt(quantityProductPage.getText().toString()) >= 500) {
                Toast.makeText(KeranjangActivity.this, "Product Count Must be less than 500", Toast.LENGTH_SHORT).show();
            }
        }

    };


}
