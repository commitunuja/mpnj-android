package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.ProductAdapter;
import com.sholeh.marketplacenj.adapter.ProdukAdapter;
import com.sholeh.marketplacenj.adapter.SliderImageAdapter;
import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.ProductModel;
import com.sholeh.marketplacenj.util.Preferences;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//2.8
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    SliderView sliderMyshop;
    TextView greetText;
//    private LinearLayout llroot;

    private ImageView nav_home, nav_notifikasi, nav_transaksi, nav_profile;
    FloatingActionButton fb_favourite;

    //http://localhost:8000/api/produk
//    RecyclerView tampilproduk;
//    private List<AllProductModel> results = new ArrayList<> ();
//    private AllProductAdapter viewAdapter;

//    APIInterface mApiInterfaceInterface;
   private RecyclerView recyclerView;
    //    private RecyclerView.Adapter mAdapter;
    private ProdukAdapter produkAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private List<Model> tvDataProduk;
    private GridLayoutManager gridLayoutManager;
//    private TextView result;

//    public static MainActivity ma;

//    SharedPreferences preferences;
    Preferences preferences;
    String id_konsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();


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


//        recyclerview = findViewById(R.id.rvListViewProduk);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mApiInterfaceInterface = CONSTANTS.getClient().create(APIInterface.class);
//        ma = this;
//        tampilproduk.setHasFixedSize ( true );
        recyclerView = findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);



        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                tvDataProduk = response.body();
                produkAdapter = new ProdukAdapter(MainActivity.this, tvDataProduk);
                recyclerView.setAdapter(produkAdapter);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });




        toolbar.setTitle("");
        setSupportActionBar(toolbar);


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
//        tampilproduk();
    }

//    private void tampilproduk() {

          /*  @Override
            public void onResponse(Call<List<TVMazeDataModel>> call, Response<List<TVMazeDataModel>> response) {
                loadProgress.setVisibility(View.GONE);
                tvMazeDataModels=response.body();
                recyclerAdapter=new RecyclerAdapter(MainActivity.this,tvMazeDataModels);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<TVMazeDataModel>> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });

    }*/


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

        switch (v.getId()) {
            case R.id.nav_transaksi:
                Toast.makeText(this, "nav transaksi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_menu:
                Toast.makeText(this, "nav fab", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_notifikasi:
                Toast.makeText(this, "nav notifikasi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_profile:
                boolean login = preferences.getSPSudahLogin();

                if (login){
                    startActivity(new Intent(this,ProfileActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }

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

    /*private void tampilproduk() throws FileNotFoundException { // hampir sama dengan cari

        BufferedReader reader = new BufferedReader(new FileReader(new File("json.txt")));
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> map = new Gson().fromJson(reader, type);

        Call<RestProduk> restProdukCall = mApiInterfaceInterface.tampilkan();
        restProdukCall.enqueue(new Callback<RestProduk>() {
            @Override
            public void onResponse(Call<RestProduk> call, Response<RestProduk>
                    response) {
                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                Object[] KontakList = response.body().getListDataproduk().toArray(
                        new Boolean[] {});
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(KontakList.length));


                mAdapter = new AllProductAdapter(KontakList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<RestProduk> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(MainActivity.this, ""+t, Toast.LENGTH_LONG).show();
            }
        });

    }*/
}
