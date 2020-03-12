package com.sholeh.marketplacenj.activities.details;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.adapter.details.ViewPagerAdapter;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.api.APIInterface;
import com.sholeh.marketplacenj.model.api.APIKeranjang;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;
import com.sholeh.marketplacenj.test.KeranjangDetailActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView offer;
    RelativeLayout rightNav;
    ArrayList<Foto> tampil = new ArrayList<Foto>();
    ViewPagerAdapter viewPagerAdapter;

    LinearLayout linear1, linear2, linear3, linear4;
    TextView txt1, txt2, txt3, txt4, idkeranjang, nama, harga, kategori, diskripsi, jumlahproduk;

    LinearLayout right1, right2, right3;
    ImageView right1_imag, right2_imag, right3_imag, tambah, keranjang;

    String namaproduk, kategoriproduk, vdeskripsi, vid_produk;
    int vhargaproduk, vstok, vterjual;
    int jumlahlagi = 1;


    private ViewPager viewPager;
//    private ViewpagerProductDetailsAdapter viewpagerAdapter;


    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};

    RelativeLayout relative1, relative2, relative3, relative4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);

        relative1 = findViewById(R.id.relative1);
        relative2 = findViewById(R.id.relative2);
        relative3 = findViewById(R.id.relative3);
        relative4 = findViewById(R.id.relative4);

        relative1.setOnClickListener(this);
        relative2.setOnClickListener(this);
        relative3.setOnClickListener(this);
        relative4.setOnClickListener(this);

        right1.setOnClickListener(this);
        right2.setOnClickListener(this);
        right3.setOnClickListener(this);

        right1_imag = findViewById(R.id.right1_img);
        right2_imag = findViewById(R.id.right2_img);
        right3_imag = findViewById(R.id.right3_img);
        keranjang = findViewById(R.id.imgkeranjang);

        offer = (TextView) findViewById(R.id.txtdiskon);
//        offer.setText("\u20B9 63,999");
        offer.setPaintFlags(offer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        linear4 = (LinearLayout) findViewById(R.id.linear4);


        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        keranjang.setOnClickListener(this);

        rightNav = (RelativeLayout) findViewById(R.id.rightNav);
        viewPager = (ViewPager) findViewById(R.id.viewpager_product_detail);
        viewPagerAdapter = new ViewPagerAdapter(tampil);
        viewPager.setAdapter(viewPagerAdapter);

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });

        //api
        nama = findViewById(R.id.txtnama);
        harga = findViewById(R.id.txtharga);
        kategori = findViewById(R.id.txtkategoriproduk);
        diskripsi = findViewById(R.id.txtdiskripsi);
        tambah = findViewById(R.id.imgtambah);
        jumlahproduk = findViewById(R.id.txtjumlah);
        idkeranjang = findViewById(R.id.txtidkeranjang);
        tambah.setOnClickListener(this);


        vid_produk = getIntent().getStringExtra("id_produk");
        String.valueOf(vid_produk);
        idkeranjang.setText(String.valueOf(vid_produk));
        namaproduk = getIntent().getExtras().getString("nama_produk");
        vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        vstok = Integer.parseInt(getIntent().getStringExtra("stok"));
        vterjual = Integer.parseInt(getIntent().getStringExtra("terjual"));
        kategoriproduk = getIntent().getStringExtra("kategori");
//        vdiskon = Integer.parseInt(getIntent().getStringExtra("diskon"));
        vdeskripsi = getIntent().getExtras().getString("keterangan");


//        id.setText(String.valueOf(vid_produk));
        nama.setText(namaproduk);
        kategori.setText(kategoriproduk);
//        offer.setText("Rp" + vdiskon);
        harga.setText(String.valueOf(vhargaproduk));
        diskripsi.setText(vdeskripsi);
        kategori.setText(kategoriproduk);
        jumlahproduk.setText(String.valueOf(jumlahlagi));


        //        Top Ten  Recyclerview Code is here

        top_ten_crecyclerview = (RecyclerView) findViewById(R.id.top_ten_recyclerview);

        topTenModelClasses = new ArrayList<>();


        for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i], title1[i], type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter2 = new RecycleAdapteTopTenHome(ProductDetailActivity.this, topTenModelClasses);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);


        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);
        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
        top_ten_crecyclerview.setAdapter(mAdapter2);

        getProdukId();

    }

    public void getProdukId() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<JsonObject> call = service.getProdukId(vid_produk);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject;
                    jsonObject = new JSONObject(String.valueOf(response.body()));
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("YOLO", "getNama          -->  " + jsonArray.getJSONObject(0).getString("id_produk"));
                    for (int i = 0; i < jsonArray.getJSONObject(0).getJSONArray("foto").length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(0).getJSONArray("foto").getJSONObject(i);
                        Foto foto = new Foto(c.getString("id_foto_poroduk"), c.getString("foto_produk"));
                        tampil.add(foto);
                    }
                    viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), tampil);
                    viewPager.setAdapter(viewPagerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgtambah:
                String produk_id = idkeranjang.getText().toString();
                String harga_jual = harga.getText().toString();
                String jumlah = (String) jumlahproduk.getText();


//                Toast.makeText(ProductDetailActivity.this, harga_jual, Toast.LENGTH_SHORT).show();

                APIKeranjang apiKeranjang = CONSTANTS.getClient().create(APIKeranjang.class);
                Call<List<Model>> sendData = apiKeranjang.simpanKeranjang(produk_id, "1", "konsumen", jumlah, harga_jual);
                Log.d("YOLO", String.valueOf(sendData));
                sendData.enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        Log.d("RETRO", "respone :" + response.body());
//                            List<Model> kode = response.body();

                        Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        Log.d("RETRO", "Falure :" + "Gagal Mengirim Request" + t);
                    }
                });
                break;
            case R.id.imgkeranjang:
                Intent intent = new Intent(this, KeranjangDetailActivity2.class);
                startActivity(intent);
                break;


            case R.id.linear1:

                linear1.setBackgroundResource(R.drawable.red_rect_normal);
                linear2.setBackgroundResource(R.drawable.storage_gray_rect);
                linear3.setBackgroundResource(R.drawable.storage_gray_rect);
                linear4.setBackgroundResource(R.drawable.storage_gray_rect);
                txt1.setTextColor(Color.parseColor("#d44334"));
                txt2.setTextColor(Color.parseColor("#d0d0d0"));
                txt3.setTextColor(Color.parseColor("#d0d0d0"));
                txt4.setTextColor(Color.parseColor("#d0d0d0"));


                break;

            case R.id.linear2:

                linear1.setBackgroundResource(R.drawable.storage_gray_rect);
                linear2.setBackgroundResource(R.drawable.red_rect_normal);
                linear3.setBackgroundResource(R.drawable.storage_gray_rect);
                linear4.setBackgroundResource(R.drawable.storage_gray_rect);
                txt1.setTextColor(Color.parseColor("#d0d0d0"));
                txt2.setTextColor(Color.parseColor("#d44334"));
                txt3.setTextColor(Color.parseColor("#d0d0d0"));
                txt4.setTextColor(Color.parseColor("#d0d0d0"));

                break;

            case R.id.linear3:

                linear1.setBackgroundResource(R.drawable.storage_gray_rect);
                linear2.setBackgroundResource(R.drawable.storage_gray_rect);
                linear3.setBackgroundResource(R.drawable.red_rect_normal);
                linear4.setBackgroundResource(R.drawable.storage_gray_rect);
                txt1.setTextColor(Color.parseColor("#d0d0d0"));
                txt2.setTextColor(Color.parseColor("#d0d0d0"));
                txt3.setTextColor(Color.parseColor("#d44334"));
                txt4.setTextColor(Color.parseColor("#d0d0d0"));

                break;

            case R.id.linear4:


                linear1.setBackgroundResource(R.drawable.storage_gray_rect);
                linear2.setBackgroundResource(R.drawable.storage_gray_rect);
                linear3.setBackgroundResource(R.drawable.storage_gray_rect);
                linear4.setBackgroundResource(R.drawable.red_rect_normal);
                txt1.setTextColor(Color.parseColor("#d0d0d0"));
                txt2.setTextColor(Color.parseColor("#d0d0d0"));
                txt3.setTextColor(Color.parseColor("#d0d0d0"));
                txt4.setTextColor(Color.parseColor("#d44334"));

                break;


            case R.id.right1:
                right1_imag.setVisibility(View.VISIBLE);
                right2_imag.setVisibility(View.GONE);
                right3_imag.setVisibility(View.GONE);
                break;

            case R.id.right2:
                right2_imag.setVisibility(View.VISIBLE);
                right1_imag.setVisibility(View.GONE);
                right3_imag.setVisibility(View.GONE);
                break;

            case R.id.right3:
                right3_imag.setVisibility(View.VISIBLE);
                right2_imag.setVisibility(View.GONE);
                right1_imag.setVisibility(View.GONE);
                break;

            case R.id.relative1:
                viewPager.setCurrentItem(0);
                break;

            case R.id.relative2:

                viewPager.setCurrentItem(1);
                break;


            case R.id.relative3:

                viewPager.setCurrentItem(2);
                break;


            case R.id.relative4:

                viewPager.setCurrentItem(3);
                break;

        }
    }
}