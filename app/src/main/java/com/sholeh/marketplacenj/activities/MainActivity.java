package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.AllProductAdapter;
import com.sholeh.marketplacenj.adapter.ProductAdapter;
import com.sholeh.marketplacenj.adapter.SliderImageAdapter;
import com.sholeh.marketplacenj.api.API;
import com.sholeh.marketplacenj.api.koneksi;
import com.sholeh.marketplacenj.model.AllProductModel;
import com.sholeh.marketplacenj.model.ProductModel;
import com.sholeh.marketplacenj.rest.RestProduk;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    SliderView sliderMyshop;
    TextView greetText;
    private LinearLayout llroot;

    private ImageView  nav_home, nav_notifikasi, nav_transaksi, nav_profile;
    FloatingActionButton fb_favourite;

    //http://localhost:8000/api/produk
//    RecyclerView tampilproduk;
//    private List<AllProductModel> results = new ArrayList<> ();
//    private AllProductAdapter viewAdapter;

    API mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        sliderMyshop = findViewById(R.id.imageSlider);
        greetText = findViewById(R.id.greeting_text);
        nav_home = findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_notifikasi = findViewById(R.id.nav_notifikasi);
        nav_notifikasi.setOnClickListener(this);
        nav_transaksi = findViewById(R.id.nav_transaksi);
        nav_transaksi.setOnClickListener(this);
        nav_profile = findViewById(R.id.nav_profile);
        nav_profile.setOnClickListener(this);
        fb_favourite = findViewById(R.id.fab_menu);
        fb_favourite.setOnClickListener(this);


        mRecyclerView =  findViewById(R.id.rvListViewProduk );
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = koneksi.getClient().create(API.class);
        ma=this;
//        tampilproduk.setHasFixedSize ( true );
//        LinearLayoutManager llm = new LinearLayoutManager ( this );
//        llm.setOrientation ( LinearLayoutManager.VERTICAL );
//        tampilproduk.setLayoutManager ( llm );


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        llroot = findViewById(R.id.mainLinearLayout1);

//        loadjson(llroot, "Terlaris", 0, 25);

        // PERULANGAN untuk menampilkan tombol kategori
//        for (String cardtitle : Constant.cards.keySet()) {
//            View card = getLayoutInflater().inflate(R.layout.item_card, null);
//            RecyclerView rv = card.findViewById(R.id.cardListView1);
//            rv.setNestedScrollingEnabled(false);
//            TextView tv = card.findViewById(R.id.cardTextView1Kategori);
//            tv.setText(cardtitle);
//            Map<Integer, String> cats = Constant.cards.get(cardtitle);
//            List<CategoryModel> datacat = new ArrayList<CategoryModel>();
//            for (int ic : cats.keySet()) {
//                datacat.add(new CategoryModel(ic, ic, cats.get(ic), false));
//            }
//            rv.addItemDecoration(new SimpleDividerItemDecoration(this));
//            rv.setAdapter(new ItemGridAdapter(datacat));
//            rv.setLayoutManager(new GridLayoutManager(this, 2)); // tombol kategori
//
//            llroot.addView(card);
//        }
//        loadjson(llroot, "Produk Terbaru", 26, 0);

       // loadjson(llroot, "Rekomendasi", 26, 0);

        final SliderImageAdapter sliderImageAdapter = new SliderImageAdapter(this);
        sliderImageAdapter.setCount(4);
        sliderMyshop.setSliderAdapter(sliderImageAdapter);
        sliderMyshop.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderMyshop.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderMyshop.setIndicatorSelectedColor(Color.WHITE);
        sliderMyshop.setIndicatorUnselectedColor(Color.GRAY);
        sliderMyshop.startAutoCycle();
        sliderMyshop.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderMyshop.setCurrentPagePosition(position);
            }
        });

        sapa();
        tampilproduk();
    }
    private void sapa() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.setText(getString(R.string.salam_pagi));
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.setText(getString(R.string.salam_siang));
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.setText(getString(R.string.salam_sore));
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.setText(getString(R.string.salam_malam));
        }
    }

    private void loadjson(LinearLayout root, String title, int startpos, int endpos) {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.bldata);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            View v = getLayoutInflater().inflate(R.layout.product_horizontal, null);
            RecyclerView rv = v.findViewById(R.id.producthorizontalRecyclerView1);
            TextView tv = v.findViewById(R.id.producthorizontalTextView1);
            tv.setText(title);
            List<ProductModel> pdata = new ArrayList<ProductModel>();
            JSONArray jdata = new JSONObject(new String(b)).getJSONArray("products");
            for (int i = startpos; i < (endpos == 0 ? jdata.length() : endpos); i++) {
                JSONObject p = jdata.getJSONObject(i);
                String name = p.getString("name");
                long price = p.getLong("price");
                long oprice = price - 1000;
                String store = p.getString("seller_name");
                String img = p.getJSONArray("images").getString(0);
                float rating = Float.parseFloat(p.getJSONObject("rating").getString("average_rate") + "f");
                pdata.add(new ProductModel(name, store, price, oprice, img, rating, 10));
            }
            rv.setAdapter(new ProductAdapter(pdata, this));
            rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
            rv.setNestedScrollingEnabled(false);
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
            rv.scrollToPosition(rv.getAdapter().getItemCount() - 1);
            root.addView(v);
        } catch (Exception e) {
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.nav_home:
                Toast.makeText(this, "nav home", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_transaksi:
                Toast.makeText(this, "nav transaksi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_menu:
                Toast.makeText(this, "nav fab", Toast.LENGTH_SHORT).show();
                break;


            default:
                break;
        }
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

//        @Override
//        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            int left = parent.getPaddingLeft();
//            int right = parent.getWidth() - parent.getPaddingRight();
//            int childCount = parent.getChildCount();
//            // tombol kategori baris  bawah
//            for (int i = 0; i < childCount; i++) {
//                View child = parent.getChildAt(i);
//                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//                int top = child.getBottom() + params.bottomMargin;
//                int bottom = top + mDivider.getIntrinsicHeight();
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
    }

    private void tampilproduk(){ // hampir sama dengan cari

        Call<RestProduk> restProdukCall = mApiInterface.tampilkan();
        restProdukCall.enqueue(new Callback<RestProduk>() {
            @Override
            public void onResponse(Call<RestProduk> call, Response<RestProduk>
                    response) {
                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                List<AllProductModel> KontakList = response.body().getListDataproduk();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(KontakList.size()));
                mAdapter = new AllProductAdapter(KontakList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<RestProduk> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(MainActivity.this, ""+t, Toast.LENGTH_LONG).show();
            }
        });

    }
}
