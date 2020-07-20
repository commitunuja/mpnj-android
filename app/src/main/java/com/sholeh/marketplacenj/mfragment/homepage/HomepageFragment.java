package com.sholeh.marketplacenj.mfragment.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.dashboard.ProdukAllActivity;
import com.sholeh.marketplacenj.activities.dashboard.SearchActivity;
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
import com.sholeh.marketplacenj.respon.ResBanner;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

//import com.mancj.materialsearchbar.MaterialSearchBar;

public class HomepageFragment extends Fragment implements View.OnClickListener, MaterialSearchBar.OnSearchActionListener {


    FrameLayout frameLayout;
    EditText search;
    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerbanner;
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
    private ProdukAdapter produkTerbaruAdapter;
    private ProdukAdapter produkDiskonAdapter;
    private ProdukAdapter produkTerlarisAdapter;
    private List<Model> tvDataProdukTerbaru;
    private List<Model> tvDataProdukDiskon;
    private List<Model> tvDataProdukTerlaris;
    private TextView tvx_allProdukTerbaru, tvx_allProdukDiskon, tvx_allProdukTerpopuler;
    private ArrayList<TopTenModelClass> topTenModelClasses;
//    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private RecycleAdapteTopTenHome mAdaper10;
    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private Integer image3[] = {R.drawable.circular_grey_bordersolid, R.drawable.circular_grey_bordersolid, R.drawable.circular_grey_bordersolid, R.drawable.circular_grey_bordersolid};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String title3[] = {"Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu"};
    private String type[] = {"Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu", "Mohon Tunggu"};

    String status, namaprodukpencarian;
    private ArrayList<TopTenModelClass> topTenModelClasses1;
    private RecyclerView recyclerProdukDiskon, recyclerProdukTerbaru, recyclerProdukTerlaris;
    private RecycleAdapteTopTenHome mAdapter3;
    private Integer image2[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile1, R.drawable.mobile2};
    private String title2[] = {"Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram", "Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram"};
    private String type2[] = {"Phones", "Phones", "Phones", "Phones"};
    private SearchFragment searchFragment;

    RecyclerView.LayoutManager dataapiTerbaru;
    RecyclerView.LayoutManager dataapiDiskon;
    RecyclerView.LayoutManager dataapiTerlaris;
    MaterialSearchBar searchBar;
    SliderLayout sliderHome;

    private BannerSlider bannerSlider;
    private List<Banner> remoteBanners=new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_homepage, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBar = view.findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.setOnClickListener(this);
//        sliderHome = view.findViewById(R.id.sliderhome);
        bannerSlider =view.findViewById(R.id.sliderhome);
        tvx_allProdukTerbaru = view.findViewById(R.id.tv_Allprodukterbaru);
        tvx_allProdukDiskon = view.findViewById(R.id.tv_AllProdukDiskon);
        tvx_allProdukTerpopuler = view.findViewById(R.id.tv_AllProdukTerlaris);
        recyclerProdukDiskon = view.findViewById(R.id.recycler_produkDiskon);
        recyclerProdukTerbaru = view.findViewById(R.id.rv_produkTerbaru);
        recyclerProdukTerlaris = view.findViewById(R.id.rv_produkterlaris);
        category_recyclerView = view.findViewById(R.id.category_recyclerview);
        recyclerbanner = view.findViewById(R.id.recyclerviewBanner);
//        edpencarian = view.findViewById(R.id.etsearch);
        frameLayout = view.findViewById(R.id.frag_container);
//        search = view.findViewById(R.id.etsearch);
//        search.setOnClickListener(this);
        tvx_allProdukTerbaru.setOnClickListener(this);
        tvx_allProdukDiskon.setOnClickListener(this);
        tvx_allProdukTerpopuler.setOnClickListener(this);


        Banner();
        getDataBanner();
        kategori();
//        produksearch();

        likeproduk();
        produkDiskon();
        produkTerbaru();
        produkTerlaris();

        fiturpencarian();


//        FragmentManager fm = getFragmentManager();
//        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if(getFragmentManager().getBackStackEntryCount() == 0);
//                recyclerViewpproduk.setVisibility(View.INVISIBLE);
//                frameLayout.setVisibility(View.VISIBLE);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchBar:
                Intent search = new Intent(getContext(), SearchActivity.class);
                startActivity(search);
//                Toast.makeText(getActivity(), "cari ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_AllProdukDiskon:
                Intent intent1 = new Intent(getContext(), ProdukAllActivity.class);
                intent1.putExtra("all","alldiskon");
                startActivity(intent1);
                break;

            case R.id.tv_Allprodukterbaru:
                Intent intent2 = new Intent(getContext(), ProdukAllActivity.class);
                intent2.putExtra("all", "allterbaru");
                startActivity(intent2);
                break;

            case R.id.tv_AllProdukTerlaris:
                Intent intent3 = new Intent(getContext(), ProdukAllActivity.class);
                intent3.putExtra("all", "allterlaris");
                startActivity(intent3);
                break;

            case R.id.etsearch:
                Intent pencarian = new Intent(getContext(), SearchActivity.class);
                startActivity(pencarian);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    //    public void onBackPressed() {
//        if (status.equals("yes")) {
//            recyclerViewpproduk.setVisibility(View.GONE);
////            linearLayoutkategori.setVisibility(View.GONE);
//            frameLayout.setVisibility(View.VISIBLE);
//            edpencarian.getText().clear();
//        } else {
//            super.onBackPressed();
//        }
//    }




    private void fiturpencarian() {

        searchBar.setSpeechMode(true);
//        searchBar.setText("Hello Zainal!");
        Log.d("LOG_TAG", getClass().getSimpleName() + ": text " + searchBar.getText());
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());

//                searchFragment = new SearchFragment();
            }

            @Override
            public void afterTextChanged(Editable editable) {


//                HomepageFragment.this.filterQuery(editable.toString());
                status = "yes";

            }

        });

//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                recyclerViewpproduk.setVisibility(View.VISIBLE);
//                frameLayout.setVisibility(View.GONE);
//                HomepageFragment.this.filterQuery(s.toString());
//                status = "yes";
//
//            }
//        });
    }




    private void likeproduk() {
//
//        topTenModelClasses1 = new ArrayList<>();
//
//        for (int i = 0; i < image3.length; i++) {
//            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image3[i], title3[i], type[i]);
//
//            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
//        }
//
//
//        mAdapter3 = new RecycleAdapteTopTenHome(getContext(), topTenModelClasses1);
//        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        like_recyclerview.setLayoutManager(mLayoutManager2);
//
//        like_recyclerview.setLayoutManager(mLayoutManager2);
//        like_recyclerview.setItemAnimator(new DefaultItemAnimator());
//        like_recyclerview.setAdapter(mAdapter3);
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
                homeCategoryModelClasses = response.body();
                recycleAdapteHomeCategory = new RecycleAdapteHomeCategory(getContext(), homeCategoryModelClasses);
                category_recyclerView.setAdapter(recycleAdapteHomeCategory);
//                homeCategoryModelClasses.add());

            }

            @Override
            public void onFailure(Call<List<Kategori>> call, Throwable t) {
                Toast.makeText(getContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });

        recycleAdapteHomeCategory = new RecycleAdapteHomeCategory(getContext(), homeCategoryModelClasses);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(mLayoutManager1);

        category_recyclerView.setLayoutManager(mLayoutManager1);
        category_recyclerView.setItemAnimator(new DefaultItemAnimator());
        category_recyclerView.setAdapter(recycleAdapteHomeCategory);
    }

//    private void produksearch(String text) {
////        namaprodukpencarian = String.valueOf(searchBar);
//        searchBar.setSpeechMode(true);
//        searchAdapter = new SearchAdapter(getContext(), datapencarian);
//
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
//        recyclerViewpproduk.setLayoutManager(layoutManager);
//        recyclerViewpproduk.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewpproduk.setNestedScrollingEnabled(false);
//        recyclerViewpproduk.setFocusableInTouchMode(false);
//
//
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
////        Call<List<Model>> call = service.getProduk();
//        Call<List<Model>> call = service.getAllData(text);
//        call.enqueue(new Callback<List<Model>>() {
//            @Override
//            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
//                if (response.isSuccessful()) {
//                    datapencarian = response.body();
//                    searchAdapter = new SearchAdapter(getContext(), datapencarian);
//                    recyclerViewpproduk.setAdapter(searchAdapter);
//
//                } else {
//                    Log.e(getTag(), "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Model>> call, Throwable t) {
//                Toast.makeText(getContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    public void filterQuery(String text) {
//        ArrayList<Model> filterdNames = new ArrayList<>();
//        for (Model s : this.datapencarian) {
//            if (s.getNamaProduk().toLowerCase().contains(text) || s.getKeterangan().toLowerCase().contains(text)) {
//                filterdNames.add(s);
//            }
//        }
//        this.searchAdapter.setFilter(filterdNames);
//    }



    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
//        produksearch(searchBar.getText());


    }

    @Override
    public void onButtonClicked(int buttonCode) {

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

    private void produkDiskon() {
        produkDiskonAdapter = new ProdukAdapter(getContext(), tvDataProdukDiskon);
        dataapiDiskon = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerProdukDiskon.setLayoutManager(dataapiDiskon);
        recyclerProdukDiskon.setItemAnimator(new DefaultItemAnimator());
        recyclerProdukDiskon.setHasFixedSize(true);
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProdukDiskon();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    tvDataProdukDiskon = response.body();
                    produkDiskonAdapter = new ProdukAdapter(getContext(), tvDataProdukDiskon);
                    recyclerProdukDiskon.setAdapter(produkDiskonAdapter);
                } else {
                    Toast.makeText(getContext(), "Data Belum Ada", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(), "gagal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getContext(), "Data Belum Ada", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void produkTerbaru() {
        produkTerbaruAdapter = new ProdukAdapter(getContext(), tvDataProdukTerbaru);
        dataapiTerbaru = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerProdukTerbaru.setLayoutManager(dataapiTerbaru);
        recyclerProdukTerbaru.setItemAnimator(new DefaultItemAnimator());
        recyclerProdukTerbaru.setHasFixedSize(true);
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProduk();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    tvDataProdukTerbaru = response.body();
                    produkTerbaruAdapter = new ProdukAdapter(getContext(), tvDataProdukTerbaru);
                    recyclerProdukTerbaru.setAdapter(produkTerbaruAdapter);
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
    private void produkTerlaris() {
        produkTerlarisAdapter = new ProdukAdapter(getContext(), tvDataProdukTerlaris);
        dataapiTerlaris = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerProdukTerlaris.setLayoutManager(dataapiTerlaris);
        recyclerProdukTerlaris.setItemAnimator(new DefaultItemAnimator());
        recyclerProdukTerlaris.setHasFixedSize(true);
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getProdukTerpopuler();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    tvDataProdukTerlaris = response.body();
                    produkTerlarisAdapter = new ProdukAdapter(getContext(), tvDataProdukTerlaris);
                    recyclerProdukTerlaris.setAdapter(produkTerlarisAdapter);
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


    private void Banner() {


        homeBannerModelClasses = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            HomeBannerModelClass beanClassForRecyclerView_contacts = new HomeBannerModelClass(image[i]);
            homeBannerModelClasses.add(beanClassForRecyclerView_contacts);
        }

        mAdapter = new RecycleAdapteHomeBanner(getContext(), homeBannerModelClasses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerbanner.setLayoutManager(mLayoutManager);


        recyclerbanner.setLayoutManager(mLayoutManager);
        recyclerbanner.setItemAnimator(new DefaultItemAnimator());
        recyclerbanner.setAdapter(mAdapter);

    }

    public void getDataBanner(){
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResBanner> call = service.getBanner();


        call.enqueue(new Callback<ResBanner>() {
            @Override
            public void onResponse(Call<ResBanner> call, Response<ResBanner> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        for(int i = 0; i <response.body().getData().size(); i++){
                            String idBanner = response.body().getData().get(i).getIdBanner();
                            String namaBanner = response.body().getData().get(i).getNamaBanner();
                            String fotoBanner = CONSTANTS.ASSETBANNER+response.body().getData().get(i).getFotoBanner();

                            remoteBanners.add(new RemoteBanner(fotoBanner));


//                            TextSliderView textSliderView = new TextSliderView(getActivity());
//                            textSliderView
//                                    .description(namaBanner)
//                                    .image(CONSTANTS.ASSETBANNER+fotoBanner)
//                                    .setScaleType(BaseSliderView.ScaleType.Fit);
////                                          .setOnSliderClickListener(this);
//                            Log.d("kocor", "onResponse: "+namaBanner);
//                            textSliderView.bundle(new Bundle());
//                            textSliderView.getBundle().putString("judul",namaBanner);
//                            sliderHome.addSlider(textSliderView);

                        }
                        bannerSlider.setBanners(remoteBanners);
//                        sliderHome.setPresetTransformer(SliderLayout.Transformer.Accordion);
//                        sliderHome.setPresetIndicator(SliderLayout.PresetIndicators.Right_Top);
//                        sliderHome.setCustomAnimation(new DescriptionAnimation());
//                        sliderHome.setDuration(5000);

                    }else{

                    }

                }else{

                }

//                tvDataProfil = response.body();
//
//                Log.d("cekimg", String.valueOf(tvDataProfil));
//                // validasi error null asset foto
//                //    Toast.makeText(getActivity(), ""+tvDataProfil.getPesan(), Toast.LENGTH_SHORT).show();
//                if (tvDataProfil.getData().getFotoProfil() == null){
//
//                    // Picasso.with(getContext()).load(R.drawable.man).into(imageProfil);
//                }else{
//                    tvx_namaCustomter.setText(String.valueOf(tvDataProfil.getData().getNamaLengkap()));
//                    Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/"+tvDataProfil.getData().getFotoProfil()).into(imageProfil);
//                }
//                Toast.makeText(getActivity(), tvDataProfil.getData().getFotoProfil(), Toast.LENGTH_LONG).show();
//                Glide.with(getActivity()).load(foto).into(imageProfil);

            }

            @Override
            public void onFailure(Call<ResBanner> call, Throwable t) {
                Toast.makeText(getActivity(), "no connection"+t, Toast.LENGTH_SHORT).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }
}
