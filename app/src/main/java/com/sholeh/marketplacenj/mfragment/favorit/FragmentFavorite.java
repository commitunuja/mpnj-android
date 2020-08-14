package com.sholeh.marketplacenj.mfragment.favorit;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.wishlist.AdapterWishlist;
import com.sholeh.marketplacenj.model.wishlist.modelWishlist;
import com.sholeh.marketplacenj.respon.ResTampilWishlist;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFavorite extends Fragment implements View.OnClickListener {
//    TextView tvxTitleTolbar, tvx_edit;

    Toolbar toolBarisi;
    Preferences preferences;
    String id_konsumen;
    EditText edCariWishlist;
    private ImageView imgreset;

    private ArrayList<modelWishlist> modellist = new ArrayList<>();
    private AdapterWishlist adapter;
    private GridView mGridView;
//    ProgressBar myProgressBar;

    private KProgressHUD progressHud;
    LinearLayout lnKosong;
    TextView tvxDesainKosong;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        toolBarisi = rootView.findViewById(R.id.toolbar);
        toolBarisi.setTitle("Favorit Saya");
        appCompatActivity.setSupportActionBar(toolBarisi);
//        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new Preferences(getActivity());
        id_konsumen = preferences.getIdKonsumen();
        progressHud = KProgressHUD.create(getActivity());
        lnKosong = rootView.findViewById(R.id.lnKosong);
        tvxDesainKosong = rootView.findViewById(R.id.tvDataKosong);
        imgreset = rootView.findViewById(R.id.imgResetSearch);
        imgreset.setOnClickListener(this);

        mGridView = rootView.findViewById(R.id.grid_favorit);
        edCariWishlist = rootView.findViewById(R.id.tvxCariWishlist);
        edCariWishlist.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (edCariWishlist.getText().toString().trim().isEmpty()) {
                        Toast.makeText(appCompatActivity, "Pencarian Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(appCompatActivity, ""+edCariWishlist.getText().toString(), Toast.LENGTH_SHORT).show();
                        cariWishlish(edCariWishlist.getText().toString().trim());
                    }

                }

                return false;
            }
        });
        getWishlish();

        return rootView;
    }

    private void ProgresDialog() {
        progressHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Proses...")
                .setCancellable(false);
        progressHud.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgResetSearch:
                edCariWishlist.setText("");
                getWishlish();

                break;


            default:
                break;
        }

    }

    @Override
    public void onResume() {
        getWishlish();
        super.onResume();
    }

    public void getWishlish() {
        if (!NetworkUtility.isNetworkConnected(getActivity())) {
            AppUtilits.displayMessage(getActivity(), getString(R.string.network_not_connected));
        } else {
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResTampilWishlist> call = service.getDataWishlist(id_konsumen);
            call.enqueue(new Callback<ResTampilWishlist>() {
                @Override
                public void onResponse(Call<ResTampilWishlist> call, Response<ResTampilWishlist> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getData().size() > 0) {
                            modellist.clear();
                            for (int i = 0; i < response.body().getData().size(); i++) {
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
                                        response.body().getData().get(i).getTerjual(),
                                        response.body().getData().get(i).getPelapak().getKabupaten(),
                                        response.body().getData().get(i).getFoto().get(0).getFotoProduk()));
                            }
                            adapter = new AdapterWishlist(getActivity(), modellist);
                            mGridView.setAdapter(adapter);
                            lnKosong.setVisibility(View.GONE);
                            mGridView.setVisibility(View.VISIBLE);
                            progressHud.dismiss();
                        } else {
                            mGridView.setVisibility(View.GONE);
                            lnKosong.setVisibility(View.VISIBLE);
                            progressHud.dismiss();
                            tvxDesainKosong.setText("Barang Favoritmu Belum Ada");
                        }
                    } else {
                        mGridView.setVisibility(View.GONE);
                        lnKosong.setVisibility(View.VISIBLE);
                        progressHud.dismiss();
                        tvxDesainKosong.setText(R.string.network_error);
                        AppUtilits.displayMessage(getActivity(), getString(R.string.network_error));
                    }
                }

                @Override
                public void onFailure(Call<ResTampilWishlist> call, Throwable t) {
                    mGridView.setVisibility(View.GONE);
                    lnKosong.setVisibility(View.VISIBLE);
                    progressHud.dismiss();
                    tvxDesainKosong.setText("Barang Favoritmu Belum Ada");
                    AppUtilits.displayMessage(getActivity(), getString(R.string.network_error));
                }
            });
        }
    }

    public void cariWishlish(String search) {
        if (!NetworkUtility.isNetworkConnected(getActivity())) {
            AppUtilits.displayMessage(getActivity(), getString(R.string.network_not_connected));
        } else {
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResTampilWishlist> call = service.cariWishlist(id_konsumen, search);

            call.enqueue(new Callback<ResTampilWishlist>() {
                @Override
                public void onResponse(Call<ResTampilWishlist> call, Response<ResTampilWishlist> response) {
                    Log.d("cariwishlist", "onResponse: " + response);
                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getData().size() > 0) {
                            modellist.clear();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                response.body().getData().get(i).getNamaProduk();
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
                                        response.body().getData().get(i).getTerjual(),
                                        response.body().getData().get(i).getPelapak().getKabupaten(),
                                        response.body().getData().get(i).getFoto().get(0).getFotoProduk()));
                            }
                            adapter = new AdapterWishlist(getActivity(), modellist);
                            mGridView.setAdapter(adapter);
                            lnKosong.setVisibility(View.GONE);
                            mGridView.setVisibility(View.VISIBLE);
                            progressHud.dismiss();

                        } else {
                            mGridView.setVisibility(View.GONE);
                            lnKosong.setVisibility(View.VISIBLE);
                            progressHud.dismiss();
                            tvxDesainKosong.setText("Tidak Ada");
                        }
                    } else {
                        mGridView.setVisibility(View.GONE);
                        lnKosong.setVisibility(View.VISIBLE);
                        progressHud.dismiss();
                        tvxDesainKosong.setText(R.string.network_error);
                        AppUtilits.displayMessage(getActivity(), getString(R.string.network_error));
                    }

                }

                @Override
                public void onFailure(Call<ResTampilWishlist> call, Throwable t) {
                    Log.d("cariwishlist", "onError: " + t);
                    mGridView.setVisibility(View.GONE);
                    lnKosong.setVisibility(View.VISIBLE);
                    progressHud.dismiss();
                    tvxDesainKosong.setText("Tidak Ada");
                    AppUtilits.displayMessage(getActivity(), getString(R.string.network_error));
                }
            });
        }
    }

}
