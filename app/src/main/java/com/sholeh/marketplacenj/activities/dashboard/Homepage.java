package com.sholeh.marketplacenj.activities.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.dashboard.ProdukAdapter;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteHomeBanner;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteHomeCategory;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.dashboard.HomeBannerModelClass;
import com.sholeh.marketplacenj.model.dashboard.HomeCategoryModelClass;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class Homepage extends AppCompatActivity {

    FrameLayout frameLayout;
    EditText search;
    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerView;
    private RecycleAdapteHomeBanner mAdapter;
    private Integer image[] = {R.drawable.image95, R.drawable.image95, R.drawable.image95, R.drawable.image95};


    private ArrayList<HomeCategoryModelClass> homeCategoryModelClasses;
    private RecyclerView category_recyclerView;
    private RecycleAdapteHomeCategory mAdapter1;
    private String title[] = {"All Categories", "Mens", "Womens", "Electronics", "Home and Furniture", "Sports"};

    private SearchAdapter searchAdapter;
    private List<Model> datapencarian;
    private LinearLayout linearLayoutkategori;
    EditText edpencarian;
    //produk
    private Object Homepage;
    private ProdukAdapter produkAdapter;
    private List<Model> tvDataProduk;
    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecyclerView recyclerViewpproduk;
    private RecycleAdapteTopTenHome mAdapter2;
    private RecycleAdapteTopTenHome mAdaper10;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};

    private ArrayList<TopTenModelClass> topTenModelClasses1;
    private RecyclerView like_recyclerview;
    private RecycleAdapteTopTenHome mAdapter3;
    private Integer image2[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile1, R.drawable.mobile2};
    private String title2[] = {"Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram", "Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram"};
    private String type2[] = {"Phones", "Phones", "Phones", "Phones"};
    private FragmentSearch fragmentSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_homepage);
        Banner();
        kategori();
        produksearch();
        likeproduk();
        produksamsung();
        recentproduk();
        produkapi();
        fiturpencarian();
        recyclerViewpproduk.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        linearLayoutkategori.setVisibility(View.GONE);
    }

    private void fiturpencarian() {
        edpencarian = findViewById(R.id.etsearch);
        linearLayoutkategori = findViewById(R.id.linearkategori);
        frameLayout = findViewById(R.id.frag_container);
        search = findViewById(R.id.etsearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                Homepage.this.filterQuery(s.toString());
                frameLayout.removeAllViews();
                recyclerViewpproduk.setVisibility(View.VISIBLE);
                Homepage.this.filterQuery(s.toString());
                frameLayout.setVisibility(View.GONE);


            }
        });
    }

    private void produksamsung() {
        like_recyclerview = (RecyclerView) findViewById(R.id.top_ten_recyclerview);

        topTenModelClasses1 = new ArrayList<>();


        for (int i = 0; i < image2.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image2[i], title2[i], type[i]);

            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
        }


        mAdapter3 = new RecycleAdapteTopTenHome(Homepage.this, topTenModelClasses1);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        like_recyclerview.setLayoutManager(mLayoutManager2);


        like_recyclerview.setLayoutManager(mLayoutManager2);
        like_recyclerview.setItemAnimator(new DefaultItemAnimator());
        like_recyclerview.setAdapter(mAdapter3);
    }

    private void produkapi() {
        like_recyclerview = (RecyclerView) findViewById(R.id.like_recyclerview);
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                tvDataProduk = response.body();
                produkAdapter = new ProdukAdapter(getBaseContext(), tvDataProduk);
                like_recyclerview.setAdapter(produkAdapter);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void recentproduk() {
        top_ten_crecyclerview = (RecyclerView) findViewById(R.id.recent_recyclerview);

        topTenModelClasses = new ArrayList<>();


        for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i], title1[i], type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter2 = new RecycleAdapteTopTenHome(Homepage.this, topTenModelClasses);
        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        top_ten_crecyclerview.setLayoutManager(mLayoutManager4);


        top_ten_crecyclerview.setLayoutManager(mLayoutManager4);
        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
        top_ten_crecyclerview.setAdapter(mAdapter2);
    }


    private void likeproduk() {
        like_recyclerview = (RecyclerView) findViewById(R.id.like_recyclerview);
        topTenModelClasses1 = new ArrayList<>();

        for (int i = 0; i < image2.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image2[i], title2[i], type[i]);

            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
        }


        mAdapter3 = new RecycleAdapteTopTenHome(Homepage.this, topTenModelClasses1);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        like_recyclerview.setLayoutManager(mLayoutManager2);

        like_recyclerview.setLayoutManager(mLayoutManager2);
        like_recyclerview.setItemAnimator(new DefaultItemAnimator());
        like_recyclerview.setAdapter(mAdapter3);
    }

    private void kategori() {
        category_recyclerView = (RecyclerView) findViewById(R.id.category_recyclerview);
        homeCategoryModelClasses = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            HomeCategoryModelClass beanClassForRecyclerView_contacts = new HomeCategoryModelClass(title[i]);
            homeCategoryModelClasses.add(beanClassForRecyclerView_contacts);
        }

        mAdapter1 = new RecycleAdapteHomeCategory(Homepage.this, homeCategoryModelClasses);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(mLayoutManager1);

        category_recyclerView.setLayoutManager(mLayoutManager1);
        category_recyclerView.setItemAnimator(new DefaultItemAnimator());
        category_recyclerView.setAdapter(mAdapter1);
    }

    private void produksearch() {
        recyclerViewpproduk = findViewById(R.id.recyclersearch);
        produkAdapter = new ProdukAdapter(Homepage.this, tvDataProduk);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
//        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewpproduk.setLayoutManager(gridLayoutManager);

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                tvDataProduk = response.body();
                produkAdapter = new ProdukAdapter(getBaseContext(), tvDataProduk);
                recyclerViewpproduk.setAdapter(produkAdapter);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Banner() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        homeBannerModelClasses = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            HomeBannerModelClass beanClassForRecyclerView_contacts = new HomeBannerModelClass(image[i]);
            homeBannerModelClasses.add(beanClassForRecyclerView_contacts);
        }

        mAdapter = new RecycleAdapteHomeBanner(Homepage.this, homeBannerModelClasses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public void filterQuery(String text) {
        ArrayList<Model> filterdNames = new ArrayList<>();
        for (Model s : this.tvDataProduk) {
            if (s.getNamaProduk().toLowerCase().contains(text) || s.getKeterangan().toLowerCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.produkAdapter.setFilter(filterdNames);
    }

    @Override
    public void onBackPressed() {
        if (recyclerViewpproduk.isShown()) {
            Toast.makeText(this, "asdasdsa", Toast.LENGTH_SHORT).show();
            recyclerViewpproduk.removeAllViews();
            frameLayout.setVisibility(View.VISIBLE);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Homepage.super.onBackPressed();
                        }
                    }).create().show();
        }
    }
}

