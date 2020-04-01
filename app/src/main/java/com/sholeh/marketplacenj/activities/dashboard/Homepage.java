package com.sholeh.marketplacenj.activities.dashboard;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.app.SearchManager;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.adapter.details.ViewPagerAdapter;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.util.CONSTANTS;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class Homepage extends AppCompatActivity {

    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerView;
    private RecycleAdapteHomeBanner mAdapter;
    private Integer image[] = {R.drawable.image95, R.drawable.image95, R.drawable.image95, R.drawable.image95};


    private ArrayList<HomeCategoryModelClass> homeCategoryModelClasses;
    private RecyclerView category_recyclerView;
    private RecycleAdapteHomeCategory mAdapter1;
    private String title[] = {"All Categories", "Mens", "Womens", "Electronics", "Home and Furniture", "Sports"};

    //produk
    ArrayList<Foto> tampil = new ArrayList<Foto>();
    private ProdukAdapter produkAdapter;
    private List<Model> tvDataProduk;
    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};

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
        Banner();
        kategori();
        produksearch();
        likeproduk();
        produksamsung();
        recentproduk();
        produkapi();
        fiturpencarian();
        frameLayout.setVisibility(View.VISIBLE);


//        Homepage Banner Recyclerview Code is here

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


        //        Category Recyclerview Code is here

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


        //        Top Ten  Recyclerview Code is here

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


        //      Like  Recyclerview Code is here
        //untuk produk

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
        //untuk produk


        //mobile/hp samsung
//        topTenModelClasses1 = new ArrayList<>();
//
//        for (int i = 0; i < image2.length; i++) {
//            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image2[i], title2[i], type2[i]);
//
//            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
//        }


        mAdapter3 = new RecycleAdapteTopTenHome(Homepage.this, topTenModelClasses1);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        like_recyclerview.setLayoutManager(mLayoutManager3);


        like_recyclerview.setLayoutManager(mLayoutManager3);
        like_recyclerview.setItemAnimator(new DefaultItemAnimator());
        like_recyclerview.setAdapter(mAdapter3);
        //mobile/hp samsung


        //        Recent  Recyclerview Code is here

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);

        Log.w("myApp", "onCreateOptionsMenu -started- ");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.hint));
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.w("myApp", "onQueryTextSubmit ");
                        produkAdapter.getFilter().filter(query);
                        topTenModelClasses.clear();
                        homeCategoryModelClasses.clear();
                        topTenModelClasses1.clear();
                        topTenModelClasses.clear();
                        homeBannerModelClasses.clear();
//                        Intent intent = new Intent(Homepage.this, SearchActivity.class);
//                        intent.putExtra("query" , query);
//                        startActivity(intent);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
//                        getAllData();
//                        topTenModelClasses.addAll(topTenModelClasses);
//                        homeCategoryModelClasses.addAll(Collections.<HomeCategoryModelClass>emptyList());
//                        topTenModelClasses1.clear();
//                        topTenModelClasses.clear();
//                        homeBannerModelClasses.clear();
//                        produkAdapter.getFilter().filter(newText);

                        return false;
                    }
                });

        return true;
    }


    public void getAllData() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                Log.d("YOLO", "coba" + response.body());
                tvDataProduk = response.body();
                produkAdapter = new ProdukAdapter(getBaseContext(), tvDataProduk);
                like_recyclerview.setAdapter(produkAdapter);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "ini"+valueOf(t), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void LoadJson() {
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<Model>> call = service.getProduk();
//
//        call.enqueue(new Callback<List<Model>>() {
//            @Override
//            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
//
//                tvDataProduk = response.body();
//                produkAdapter = new ProdukAdapter(getBaseContext(), tvDataProduk);
//                like_recyclerview.setAdapter(produkAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Model>> call, Throwable t) {
//                Toast.makeText(getBaseContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}