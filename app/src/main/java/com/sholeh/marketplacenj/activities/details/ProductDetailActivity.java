package com.sholeh.marketplacenj.activities.details;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.LoginActivity;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.activities.pelapak.ProfilPelapakActivity;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.adapter.details.ViewPagerAdapter;
import com.sholeh.marketplacenj.custom.MainActivity2;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;
import com.sholeh.marketplacenj.respon.ResKeranjang;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {


    RelativeLayout rightNav;
    ArrayList<Foto> tampil = new ArrayList<Foto>();
    ViewPagerAdapter viewPagerAdapter;

    LinearLayout linear1, linear2, linear3, linear4;
    TextView txt1, txt2, txt3, txt4, idkeranjang, nama, harga, kategori, jumlahproduk, offer, namapelapak, readmore;
    JustifiedTextView diskripsi;

    LinearLayout right1, right2, right3;
    ImageView right1_imag, right2_imag, right3_imag, tambah, keranjang;

    String namaproduk, kategoriproduk, vdeskripsi, vid_produk, pelapak, foto_pelapak, id_pelapak, fotoproduk;

    int vhargaproduk;
    int vstok;
    int vterjual;
    Double vdiskon;
    ImageView fotopelapak;
    private ViewPager viewPager;

    //    private ViewpagerProductDetailsAdapter viewpagerAdapter;
    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};

    RelativeLayout relative1, relative2, relative3, relative4;

    Preferences preferences;
    String id_konsumen, iduser;
    StringTokenizer st1, st2;
    private List<Model> tvDataProduks;
    private Model tvDataProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        namapelapak = findViewById(R.id.tv_nama_pelapak);
        fotopelapak = findViewById(R.id.img_foto_pelapak);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);

     /*   relative1 = findViewById(R.id.relative1);
        relative2 = findViewById(R.id.relative2);
        relative3 = findViewById(R.id.relative3);
        relative4 = findViewById(R.id.relative4);

        relative1.setOnClickListener(this);
        relative2.setOnClickListener(this);
        relative3.setOnClickListener(this);
        relative4.setOnClickListener(this);*/

        right1.setOnClickListener(this);
        right2.setOnClickListener(this);
        right3.setOnClickListener(this);

        right1_imag = findViewById(R.id.right1_img);
        right2_imag = findViewById(R.id.right2_img);
        right3_imag = findViewById(R.id.right3_img);
        keranjang = findViewById(R.id.imgkeranjang);
        readmore = findViewById(R.id.tv_readmore);

        offer = findViewById(R.id.txtdiskon);
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
        fotopelapak.setOnClickListener(this);
        readmore.setOnClickListener(this);

        rightNav = (RelativeLayout) findViewById(R.id.rightNav);
        viewPager = (ViewPager) findViewById(R.id.viewpager_product_detail);

        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = 1100;
        viewPager.requestLayout();
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
        idkeranjang = findViewById(R.id.txtidkerenjang);

        tambah.setOnClickListener(this);


        vid_produk = getIntent().getStringExtra("id_produk");
        namaproduk = getIntent().getExtras().getString("nama_produk");
        vhargaproduk = parseInt(getIntent().getStringExtra("harga_jual"));
        vstok = parseInt(getIntent().getStringExtra("stok"));
        vterjual = parseInt(getIntent().getStringExtra("terjual"));
        kategoriproduk = getIntent().getStringExtra("kategori");
        vdiskon = Double.valueOf(parseInt(getIntent().getStringExtra("diskon")));
        fotoproduk = getIntent().getStringExtra("foto_produk");

        vdeskripsi = getIntent().getExtras().getString("keterangan");

        nama.setText(namaproduk);
        double h = vdiskon / 100 * vhargaproduk;
        double p = vhargaproduk - h;
        String dStr = String.valueOf(p);
        String value = dStr.matches("\\d+\\.\\d*[1-9]\\d*") ? dStr : dStr.substring(0, dStr.indexOf("."));


        kategori.setText(kategoriproduk);
        if (kategoriproduk.equals("Sepatu")) {
            Toast.makeText(this, kategoriproduk, Toast.LENGTH_SHORT).show();
//            linear5.removeAllViews();
        }
        if (vdiskon == 0) {
            harga.setText("Rp " + vhargaproduk);
            offer.setText("");

        } else {
            offer.setText("Rp " + vhargaproduk);

            harga.setText("Rp " + value);

        }


        diskripsi.setText(Html.fromHtml(vdeskripsi));
        kategori.setText(kategoriproduk);


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
                    jsonObject = new JSONObject(valueOf(response.body()));
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("YOLO", "getNama          -->  " + jsonArray.getJSONObject(0).getString("id_produk"));
                    for (int i = 0; i < jsonArray.getJSONObject(0).getJSONArray("foto").length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(0).getJSONArray("foto").getJSONObject(i);
                        Foto foto = new Foto(c.getString("id_foto_poroduk"), c.getString("foto_produk"));
                        tampil.add(foto);
                    }
//                    Pelapak pelapak = new Pelapak();

                    foto_pelapak = jsonArray.getJSONObject(0).getJSONObject("pelapak").getString("foto_pelapak");
//                    iduser =
                    Glide.with(getBaseContext())
                            .load(foto_pelapak)
                            .into(fotopelapak);
                    pelapak = jsonArray.getJSONObject(0).getJSONObject("pelapak").getString("nama_toko");
                    namapelapak.setText(pelapak);
                    id_pelapak = jsonArray.getJSONObject(0).getJSONObject("pelapak").getString("id_pelapak");
                    Log.d("CEK", "error" + fotopelapak);
                    viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), tampil);
                    viewPager.setAdapter(viewPagerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        boolean login = preferences.getSPSudahLogin();

        switch (v.getId()) {
            case R.id.imgtambah:
                if (login) {
                    addKeranjang();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }

                break;
            case R.id.imgkeranjang:
                if (login) {
                    Intent intent = new Intent(this, KeranjangDetailActivity.class);
                    startActivity(intent);

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }

                break;
            case R.id.img_foto_pelapak:
                if (login) {
                    Intent intent = new Intent(this, ProfilPelapakActivity.class);
                    intent.putExtra("id_user", id_pelapak);
                    intent.putExtra("namapelapak", (pelapak));
                    intent.putExtra("foto_pelapak", foto_pelapak);

                    Toast.makeText(this, "" + foto_pelapak, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;
            case R.id.tv_readmore:
                Intent intent2 = new Intent(this, DiskripsiActivity.class);
                intent2.putExtra("nama_produk", namaproduk);
                intent2.putExtra("harga_jual", (String.valueOf(vhargaproduk)));
                intent2.putExtra("diskon", (String.valueOf(vdiskon)));
                intent2.putExtra("keterangan", vdeskripsi);
                intent2.putExtra("foto_produk", fotoproduk);
//                intent2.putExtra("foto_pelapak",);

//                Toast.makeText(this, "" + vdeskripsi, Toast.LENGTH_SHORT).show();
                startActivity(intent2);

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

         /*   case R.id.relative1:
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
                break;*/

        }
    }


    public void addKeranjang() {
//        if (!NetworkUtility.isNetworkConnected(RegisterActivity.this)) {
//            AppUtilits.displayMessage(RegisterActivity.this, getString(R.string.network_not_connected));
//
//        } else {


        if (vdiskon == 0) { // tidak ada diskon
            final String harga_jual = harga.getText().toString();
            st1 = new StringTokenizer(harga_jual, "Rp");
            String hargaJual = st1.nextToken().trim();

            APIInterface apiKeranjang = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResKeranjang> sendData = apiKeranjang.simpanKeranjang(vid_produk, id_konsumen,  String.valueOf(1), hargaJual);
            sendData.enqueue(new Callback<ResKeranjang>() {
                @Override
                public void onResponse(Call<ResKeranjang> call, Response<ResKeranjang> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getPesan().equalsIgnoreCase("sukses")) {
                            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.add_to_cart));

                        } else {
//                        Toast.makeText(ProductDetailActivity.this, "r"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                            AppUtilits.displayMessage(RegisterActivity.this,  response.body().getPesan());
                        }
                    } else {
//                    Toast.makeText(ProductDetailActivity.this, "rr"+response.body().getPesan(), Toast.LENGTH_SHORT).show();

//                        AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                    }
                }

                @Override
                public void onFailure(Call<ResKeranjang> call, Throwable t) {
//                    Log.e(TAG, " failure " + t.toString());
//                Toast.makeText(ProductDetailActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();
                    Log.d("ok", String.valueOf(t));


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                }
            });

        } else {
            final String harga_ = offer.getText().toString();
            st2 = new StringTokenizer(harga_, "Rp");
            String hargaJual = st2.nextToken().trim();

            APIInterface apiKeranjang = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResKeranjang> sendData = apiKeranjang.simpanKeranjang(vid_produk, id_konsumen, String.valueOf(1), hargaJual);
            sendData.enqueue(new Callback<ResKeranjang>() {
                @Override
                public void onResponse(Call<ResKeranjang> call, Response<ResKeranjang> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getPesan().equalsIgnoreCase("sukses")) {
                            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.add_to_cart));

                        } else {
                        Toast.makeText(ProductDetailActivity.this, "r"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                            AppUtilits.displayMessage(RegisterActivity.this,  response.body().getPesan());
                        }
                    } else {
                    Toast.makeText(ProductDetailActivity.this, "rr"+response.body().getPesan(), Toast.LENGTH_SHORT).show();

//                        AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                    }
                }

                @Override
                public void onFailure(Call<ResKeranjang> call, Throwable t) {
//                    Log.e(TAG, " failure " + t.toString());
//                Toast.makeText(ProductDetailActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();
                    Log.d("ok", String.valueOf(t));


//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                }
            });

        }


    }
}