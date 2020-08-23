package com.sholeh.marketplacenj.activities.details;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.LoginActivity;
import com.sholeh.marketplacenj.activities.dashboard.ProdukAllActivity;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.activities.pelapak.ProfilPelapakActivity;
import com.sholeh.marketplacenj.adapter.dashboard.ProdukAdapter;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.adapter.details.ViewPagerAdapter;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;
import com.sholeh.marketplacenj.respon.ResKeranjang;
import com.sholeh.marketplacenj.respon.ResWishlist;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
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

import static com.sholeh.marketplacenj.util.MyApp.getContext;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {


    RelativeLayout rightNav;
    ArrayList<Foto> tampil = new ArrayList<Foto>();
    ViewPagerAdapter viewPagerAdapter;

    LinearLayout linear1, linear2, linear3, linear4;
    TextView txt1, txt2, txt3, txt4, idkeranjang, nama, harga, kategori, jumlahproduk,offer, namapelapak, readmore, allterkait;
    JustifiedTextView diskripsi;

    LinearLayout right1, right2, right3;
    ImageView right1_imag, right2_imag, right3_imag, keranjang;

    String namaproduk, kategoriproduk, vdeskripsi, vid_produk, pelapak, foto_pelapak, id_pelapak, fotoproduk, hargaJual;

    int vhargaproduk;
    int vstok;
    int vterjual;
    Double vdiskon, p;

    ImageView fotopelapak, tokeranjang;
    private ViewPager viewPager;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    Button tambah;

    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};

    RelativeLayout relative1, relative2, relative3, relative4;

    Preferences preferences;
    String id_konsumen, iduser;
    StringTokenizer st1, st2, st3, st4;
    private List<Model> tvDataProduks;
    private Model tvDataProduk;
    ImageView btnAddWishlist;
    private KProgressHUD progressDialogHud;
    ProgressBar myProgressBar;
    boolean login;
    private ProdukAdapter produkTerbaruAdapter;
    private List<Model> tvDataProdukTerbaru;
    RecyclerView.LayoutManager dataapiTerbaru;
    ImageView imgToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        progressDialogHud = KProgressHUD.create(ProductDetailActivity.this);
        myProgressBar = findViewById(R.id.myProgressBar);
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        namapelapak = findViewById(R.id.tv_nama_pelapak);
        fotopelapak = findViewById(R.id.img_foto_pelapak);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        tokeranjang = findViewById(R.id.imgtambah);
        imgToolbar = findViewById(R.id.imgtoolbar);
        imgToolbar.setOnClickListener(this);

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
        allterkait = findViewById(R.id.tvx_allterkait);


        offer = findViewById(R.id.txtdiskon);
        offer.setPaintFlags(offer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        keranjang.setOnClickListener(this);
        fotopelapak.setOnClickListener(this);
        readmore.setOnClickListener(this);


        rightNav = findViewById(R.id.rightNav);
        viewPager = findViewById(R.id.viewpager_product_detail);

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
        tambah = findViewById(R.id.btntambah);
        jumlahproduk = findViewById(R.id.txtjumlah);
        idkeranjang = findViewById(R.id.txtidkerenjang);
        btnAddWishlist = findViewById(R.id.btn_addtowishlist);

        tokeranjang.setOnClickListener(this);
        tambah.setOnClickListener(this);
        btnAddWishlist.setOnClickListener(this);
        allterkait.setOnClickListener(this);


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
        p = vhargaproduk - h;
        String dStr = String.valueOf(p);
        String value = dStr.matches("\\d+\\.\\d*[1-9]\\d*") ? dStr : dStr.substring(0, dStr.indexOf("."));

        st3 = new StringTokenizer(formatRupiah.format(vhargaproduk), ",");
        String hargaAwal = st3.nextToken().trim();
        st4 = new StringTokenizer(formatRupiah.format(p), ",");
        String hargaDiskon = st4.nextToken().trim();


//        String dStr = String.valueOf(p);
//        String value = dStr.matches("\\d+\\.\\d*[1-9]\\d*") ? dStr : dStr.substring(0, dStr.indexOf("."));

        login = preferences.getSPSudahLogin();
        if (login) {
            tokeranjang.setVisibility(View.VISIBLE);
            tambah.setVisibility(View.GONE);
        }else {
            tambah.setText("LOGIN");
        }
        kategori.setText(kategoriproduk);
        if (kategoriproduk.equals("Sepatu")) {
//            Toast.makeText(this, kategoriproduk, Toast.LENGTH_SHORT).show();
//            linear5.removeAllViews();
        }
        if (vdiskon == 0) {

            st2 = new StringTokenizer(formatRupiah.format(vhargaproduk), ",");
            hargaJual = st2.nextToken().trim();
            harga.setText(hargaJual);
            offer.setText("");

        } else {
            st2 = new StringTokenizer(formatRupiah.format(vhargaproduk), ",");
            String diskons = st2.nextToken().trim();
            offer.setText(diskons);

            st2 = new StringTokenizer(formatRupiah.format(p), ",");
            String hargas = st2.nextToken().trim();
            harga.setText(hargas);

        }


        diskripsi.setText(Html.fromHtml(vdeskripsi));
        kategori.setText(kategoriproduk);


        top_ten_crecyclerview = findViewById(R.id.top_ten_recyclerview);

        topTenModelClasses = new ArrayList<>();


        for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i], title1[i], type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


//        mAdapter2 = new RecycleAdapteTopTenHome(ProductDetailActivity.this, topTenModelClasses);
//        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);
//
//
//        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);
//        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
//        top_ten_crecyclerview.setAdapter(mAdapter2);

        getDetail();
        produkTerbaru();

    }

    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btntambah:
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
//                    finish();

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }

                break;

            case R.id.btn_addtowishlist:
                addWishlist();
                break;
            case R.id.imgtambah:
                addKeranjang();
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
                break;

            case R.id.tvx_allterkait:
                Intent i = new Intent(getContext(), ProdukAllActivity.class);
                i.putExtra("all", "allterbaru");
                startActivity(i);
                break;

            case R.id.imgtoolbar:
                finish();
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


    public void getDetail() {
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
                    foto_pelapak = jsonArray.getJSONObject(0).getJSONObject("pelapak").getString("foto_pelapak");
                    Glide.with(getBaseContext())
                            .load(foto_pelapak)
                            .into(fotopelapak);
                    pelapak = jsonArray.getJSONObject(0).getJSONObject("pelapak").getString("nama_toko");
                    namapelapak.setText(pelapak);
                    id_pelapak = jsonArray.getJSONObject(0).getJSONObject("pelapak").getString("id_pelapak");
                    Log.d("CEK", "error" + fotopelapak);
                    viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), tampil);
                    viewPager.setAdapter(viewPagerAdapter);
                    myProgressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(ProductDetailActivity.this, "Terdapat Kesalahan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProductDetailActivity.this, "Internet Anda Kurang Stabil. Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addKeranjang() {
        if (!NetworkUtility.isNetworkConnected(ProductDetailActivity.this)) {
            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.network_not_connected));
        } else {
            final String harga_jual = String.valueOf(vhargaproduk);
            StringTokenizer st1 = new StringTokenizer(harga_jual, "Rp");
            String hargaJual = st1.nextToken().trim();
            ProgresDialog();
            APIInterface apiKeranjang = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResKeranjang> sendData = apiKeranjang.simpanKeranjang(vid_produk, id_konsumen, String.valueOf(1), hargaJual);
            sendData.enqueue(new Callback<ResKeranjang>() {
                @Override
                public void onResponse(Call<ResKeranjang> call, Response<ResKeranjang> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getPesan().equalsIgnoreCase("sukses")) {
                            progressDialogHud.dismiss();
                            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.add_to_cart));

                        } else {
                            progressDialogHud.dismiss();
                            Toast.makeText(ProductDetailActivity.this, "Terdapat Kesalahan Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialogHud.dismiss();
                        Toast.makeText(ProductDetailActivity.this, "Terdapat Kesalahan Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResKeranjang> call, Throwable t) {
                    progressDialogHud.dismiss();
                    Toast.makeText(ProductDetailActivity.this, "Internet Anda Kurang Stabil. Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                    Log.d("ok", String.valueOf(t));
//                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
                }
            });
        }
    }

    public void addWishlist() {
        if (!NetworkUtility.isNetworkConnected(ProductDetailActivity.this)) {
            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.network_not_connected));
        } else {
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResWishlist> addWishlist = service.addWishlist(id_konsumen, vid_produk);
            addWishlist.enqueue(new Callback<ResWishlist>() {
                @Override
                public void onResponse(Call<ResWishlist> call, Response<ResWishlist> response) {
                    String getpesan = response.body().getPesan();
                    Log.d("addwishlist", String.valueOf(response));
                    if (response.body() != null && response.isSuccessful()) {
                        if (getpesan.equalsIgnoreCase("Produk Sudah Ada Di Favorit")) {
                            progressDialogHud.dismiss();
                            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.add_to_wishlistval));
                        } else {
                            progressDialogHud.dismiss();
                            AppUtilits.displayMessage(ProductDetailActivity.this, getString(R.string.add_to_wishlist));
                        }
                    } else {
                        progressDialogHud.dismiss();
                        Toast.makeText(ProductDetailActivity.this, "Terdapat Kesalahan Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ResWishlist> call, Throwable t) {
                    progressDialogHud.dismiss();
                    Toast.makeText(ProductDetailActivity.this, "Internet Anda Kurang Stabil. Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void produkTerbaru() {
        produkTerbaruAdapter = new ProdukAdapter(getContext(), tvDataProdukTerbaru);
        dataapiTerbaru = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        top_ten_crecyclerview.setLayoutManager(dataapiTerbaru);
        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
        top_ten_crecyclerview.setHasFixedSize(true);
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    tvDataProdukTerbaru = response.body();
                    produkTerbaruAdapter = new ProdukAdapter(getContext(), tvDataProdukTerbaru);
                    top_ten_crecyclerview.setAdapter(produkTerbaruAdapter);
                } else {
                    Toast.makeText(getContext(), "Data Belum Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getContext(), "Data Belum Ada", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
}