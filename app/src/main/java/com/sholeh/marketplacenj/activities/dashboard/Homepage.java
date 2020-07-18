package com.sholeh.marketplacenj.activities.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.ProdukByKategoriAdapter;
import com.sholeh.marketplacenj.adapter.dashboard.ProdukAdapter;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteHomeBanner;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteHomeCategory;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteTopTenHome;
import com.sholeh.marketplacenj.adapter.dashboard.SearchAdapter;
import com.sholeh.marketplacenj.model.Kategori;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.dashboard.HomeBannerModelClass;
import com.sholeh.marketplacenj.model.dashboard.HomeCategoryModelClass;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    FrameLayout frameLayout;
    EditText search;
    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerView;
    private RecycleAdapteHomeBanner mAdapter;
    private Integer image[] = {R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner};


    private List<Kategori> homeCategoryModelClasses;
    private ArrayList<HomeCategoryModelClass> semua;
    TextView allcategory;
    private RecyclerView category_recyclerView;
    private RecycleAdapteHomeCategory recycleAdapteHomeCategory;
    //    private ArrayList<KategoriLihatSemua> kategoriLihatSemua;
    private String lihatsemua[] = {"All Categories"};

    //category by id

    private String id_kategori;
    private RecyclerView recyclerViewProdukByKategori, recyclerViewProdukByKategori2;
    private List<Model> dataProdukByKategori;

    private ProdukByKategoriAdapter produkByKategoriAdapter;

    //pencarian
    private SearchAdapter searchAdapter;
    private SearchAdapter kategoriAdapter;
    private List<Model> datapencarian;
    private List<Model> datakategori;
    private LinearLayout linearLayoutkategori;
    EditText edpencarian;

    //produk
    private ProdukAdapter produkAdapter;
    private List<Model> tvDataProduk;
    private TextView produkterpopuler;
    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecyclerView recyclerViewpproduk;
    private RecycleAdapteTopTenHome mAdapter2;
    private RecycleAdapteTopTenHome mAdaper10;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private Integer image3[] = {R.drawable.circular_grey_bordersolid, R.drawable.circular_grey_bordersolid, R.drawable.circular_grey_bordersolid, R.drawable.circular_grey_bordersolid};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String title3[] = {"Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu","Mohon Tunggu"};
    private String type[] = {"Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu"};

    String status;
    private ArrayList<TopTenModelClass> topTenModelClasses1;
    private RecyclerView like_recyclerview;
    private RecycleAdapteTopTenHome mAdapter3;
    private Integer image2[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile1, R.drawable.mobile2};
    private String title2[] = {"Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram", "Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram"};
    private String type2[] = {"Phones", "Phones", "Phones", "Phones"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_homepage);

        produkterpopuler = findViewById(R.id.tv_produkterpopuler);
        produkterpopuler.setOnClickListener(this);

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


    }

    private void fiturpencarian() {
        edpencarian = findViewById(R.id.etsearch);
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
                recyclerViewpproduk.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                Homepage.this.filterQuery(s.toString());
                status = "yes";

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (status.equals("yes")) {
            recyclerViewpproduk.setVisibility(View.GONE);
//            linearLayoutkategori.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            edpencarian.getText().clear();
        } else {
            super.onBackPressed();
        }
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

        for (int i = 0; i < image3.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image3[i], title3[i], type[i]);

            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
        }


        mAdapter3 = new RecycleAdapteTopTenHome(Homepage.this, topTenModelClasses1);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        like_recyclerview.setLayoutManager(mLayoutManager2);

        like_recyclerview.setLayoutManager(mLayoutManager2);
        like_recyclerview.setItemAnimator(new DefaultItemAnimator());
        like_recyclerview.setAdapter(mAdapter3);
    }

//    private void kategori2() {
//        recyclerViewProdukByKategori2 = (RecyclerView) findViewById(R.id.category_recyclerview2);
//
//        homeCategoryModelClasses = new ArrayList<>();
//        semua = new ArrayList<>();
//
//        for (int i = 0; i < lihatsemua.length; i++) {
//            HomeCategoryModelClass beanClassForRecyclerView_contacts = new HomeCategoryModelClass(lihatsemua[i]);
//
//            semua.add(beanClassForRecyclerView_contacts);
//        }
//
//
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<Kategori>> call = service.getKategori();
//
//        call.enqueue(new Callback<List<Kategori>>() {
//            @Override
//            public void onResponse(Call<List<Kategori>> call, Response<List<Kategori>> response) {
////                Log.d("YOLO", "Error" + response);
//                homeCategoryModelClasses = response.body();
//                recycleAdapteHomeCategory = new RecycleAdapteHomeCategory(getBaseContext(), homeCategoryModelClasses, semua);
//                recyclerViewProdukByKategori2.setAdapter(recycleAdapteHomeCategory);
////                homeCategoryModelClasses.add());
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Kategori>> call, Throwable t) {
//                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        recycleAdapteHomeCategory = new RecycleAdapteHomeCategory(Homepage.this, homeCategoryModelClasses, semua);
//        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewProdukByKategori2.setLayoutManager(mLayoutManager1);
//
//        recyclerViewProdukByKategori2.setLayoutManager(mLayoutManager1);
//        recyclerViewProdukByKategori2.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewProdukByKategori2.setAdapter(recycleAdapteHomeCategory);
//    }

    private void kategori() {
        category_recyclerView = (RecyclerView) findViewById(R.id.category_recyclerview);
        homeCategoryModelClasses = new ArrayList<>();
        semua = new ArrayList<>();

        for (int i = 0; i < lihatsemua.length; i++) {
            HomeCategoryModelClass beanClassForRecyclerView_contacts = new HomeCategoryModelClass(lihatsemua[i]);

            semua.add(beanClassForRecyclerView_contacts);
        }


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Kategori>> call = service.getKategori();

        call.enqueue(new Callback<List<Kategori>>() {
            @Override
            public void onResponse(Call<List<Kategori>> call, Response<List<Kategori>> response) {
//                Log.d("YOLO", "Error" + response);
                homeCategoryModelClasses = response.body();
                recycleAdapteHomeCategory = new RecycleAdapteHomeCategory(getBaseContext(), homeCategoryModelClasses);
                category_recyclerView.setAdapter(recycleAdapteHomeCategory);
//                homeCategoryModelClasses.add());

            }

            @Override
            public void onFailure(Call<List<Kategori>> call, Throwable t) {
                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });

        recycleAdapteHomeCategory = new RecycleAdapteHomeCategory(Homepage.this, homeCategoryModelClasses);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(mLayoutManager1);

        category_recyclerView.setLayoutManager(mLayoutManager1);
        category_recyclerView.setItemAnimator(new DefaultItemAnimator());
        category_recyclerView.setAdapter(recycleAdapteHomeCategory);
    }

    private void produksearch() {
        recyclerViewpproduk = findViewById(R.id.recyclersearch);
        searchAdapter = new SearchAdapter(Homepage.this, datapencarian);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Homepage.this, 2);
        recyclerViewpproduk.setLayoutManager(layoutManager);
        recyclerViewpproduk.setItemAnimator(new DefaultItemAnimator());
        recyclerViewpproduk.setNestedScrollingEnabled(false);
        recyclerViewpproduk.setFocusableInTouchMode(false);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                datapencarian = response.body();
                searchAdapter = new SearchAdapter(getBaseContext(), datapencarian);
                recyclerViewpproduk.setAdapter(searchAdapter);

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
        for (Model s : this.datapencarian) {
            if (s.getNamaProduk().toLowerCase().contains(text) || s.getKeterangan().toLowerCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.searchAdapter.setFilter(filterdNames);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_produkterpopuler:
                Intent intent = new Intent(this, ProdukTerbaruActivity.class);
                startActivity(intent);
        }
    }

//    public void kategoriById() {
//        recyclerViewProdukByKategori2 = findViewById(R.id.category_recyclerview2);
//        recyclerViewProdukByKategori2.setVisibility(View.VISIBLE);
////        recyclerViewProdukByKategori = (RecyclerView) findViewById(R.id.barang);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Homepage.this, 2);
//        recyclerViewProdukByKategori2.setLayoutManager(layoutManager);
//        recyclerViewProdukByKategori2.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewProdukByKategori2.setNestedScrollingEnabled(false);
//        recyclerViewProdukByKategori2.setFocusableInTouchMode(false);
//        getDataProdukByKategori();
//        kategori2();
//    }

//    private void getDataProdukByKategori() {
//
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<Model>> call = service.getKategoriByid(id_kategori);
//
//        call.enqueue(new Callback<List<Model>>() {
//            @Override
//            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
//
//                if (response.body() == null) {
//                    Toast.makeText(getBaseContext(), "Tidak ada data pada kategori ini.", Toast.LENGTH_SHORT).show();
//                } else {
////                    progressBarProdukByKategori.setVisibility(View.GONE);
//                    dataProdukByKategori = response.body();
//                    produkByKategoriAdapter = new ProdukByKategoriAdapter(getBaseContext(), dataProdukByKategori);
//                    recyclerViewProdukByKategori2.setAdapter(produkByKategoriAdapter);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Model>> call, Throwable t) {
//                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}




