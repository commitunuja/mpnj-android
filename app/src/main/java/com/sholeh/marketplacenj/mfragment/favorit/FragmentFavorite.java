package com.sholeh.marketplacenj.mfragment.favorit;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.adapter.wishlist.AdapterWishlist;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.wishlist.modelWishlist;
import com.sholeh.marketplacenj.respon.ItemKeranjang;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResTampilWishlist;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFavorite extends Fragment {
//    TextView tvxTitleTolbar, tvx_edit;

    Toolbar toolBarisi;
    Preferences preferences;
    String id_konsumen;

    private ArrayList<modelWishlist> modellist = new ArrayList<>();
    private AdapterWishlist adapter;
    private GridView mGridView;
    ProgressBar myProgressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite,container,false);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        toolBarisi =  rootView.findViewById(R.id.toolbar);
        toolBarisi.setTitle("Favorit Saya");
        appCompatActivity.setSupportActionBar(toolBarisi);
//        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new Preferences(getActivity());
        id_konsumen = preferences.getIdKonsumen();

        mGridView = rootView.findViewById(R.id.grid_favorit);

        myProgressBar= rootView.findViewById(R.id.myProgressBar);
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        getWishlish();

        return rootView;
    }

    public void getWishlish() {
//        if (!NetworkUtility.isNetworkConnected(AlamatActivity.this)) {
//            AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.network_not_connected));
//        } else {
//            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResTampilWishlist> call = service.getDataWishlist(id_konsumen);
            call.enqueue(new Callback<ResTampilWishlist>() {
                @Override
                public void onResponse(Call<ResTampilWishlist> call, Response<ResTampilWishlist> response) {
                    Log.d("wishlist", "onResponse: "+response);

                    if (response.body() != null && response.isSuccessful()) {

//                        if (response.body().getPesan().equalsIgnoreCase("Sukses!!")) {
//
//
//                            Log.d("cekalamat", String.valueOf(response.body().getData().getDaftarAlamat().size()));
                            if (response.body().getData().size() > 0) {
                                modellist.clear();
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    response.body().getData().get(i).getNamaProduk();

//                                    List<Foto> arrayFoto = response.body().getData().get(i).getFoto();
//                                    for (int j = 0; j < arrayFoto.size(); j++) {
                                        modellist.add(new modelWishlist(
                                                    response.body().getData().get(i).getIdWishlist(),
                                                    response.body().getData().get(i).getIdUser(),
                                                    response.body().getData().get(i).getIdProduk(),
                                                    response.body().getData().get(i).getNamaProduk(),
                                                    response.body().getData().get(i).getHargaJual(),
                                                    response.body().getData().get(i).getDiskon(),
                                                    response.body().getData().get(i).getStok(),
                                                    response.body().getData().get(i).getKategori(),
                                                    response.body().getData().get(i).getKeterangan(),
                                                    response.body().getData().get(i).getFoto().get(0).getFotoProduk()));
//                                    }
//                                    Toast.makeText(getActivity(), ""+response.body().getData().get(i).getNamaProduk(), Toast.LENGTH_SHORT).show();
//




//
//
                                }
                                adapter = new AdapterWishlist(getActivity(),modellist);
                                mGridView.setAdapter(adapter);
                                myProgressBar.setVisibility(View.GONE);

//
//                                adapter.notifyDataSetChanged();

//                                recyclerAlamat.setVisibility(View.VISIBLE);
////                            ln_kosong.setVisibility(View.GONE);
//                                progressDialogHud.dismiss();
//
//
                            } else {
//                                Toast.makeText(AlamatActivity.this, "Data Belum Ada", Toast.LENGTH_SHORT).show();
//                                recyclerAlamat.setVisibility(View.GONE);
////                            ln_kosong.setVisibility(View.VISIBLE);
//                                progressDialogHud.dismiss();
                            }
//                        } else {
////                            AppUtilits.displayMessage(AlamatActivity.this, response.body().getPesan() );
//                            recyclerAlamat.setVisibility(View.GONE);
//                            ln_kosong.setVisibility(View.VISIBLE);
//                            progressDialogHud.dismiss();
//                        }
                    } else {
//                        AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.network_error));
//                        recyclerAlamat.setVisibility(View.GONE);
//                        ln_kosong.setVisibility(View.VISIBLE);
//                        progressDialogHud.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ResTampilWishlist> call, Throwable t) {
                    Log.d("wishlist", "onError: "+t);
                    myProgressBar.setVisibility(View.GONE);

//                    AppUtilits.displayMessage(AlamatActivity.this, getString(R.string.fail_togetaddress));
//                    recyclerAlamat.setVisibility(View.GONE);
//                    ln_kosong.setVisibility(View.VISIBLE);
//                    progressDialogHud.dismiss();


                }
            });
//        }
    }



}
