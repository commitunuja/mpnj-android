package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.KeranjangDetailAdapter;
import com.sholeh.marketplacenj.model.Keranjang;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.api.APIKeranjang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangDetailActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<Model> mItems = new ArrayList<>();
    private List<Keranjang> ItemKeranjang = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);
        mRecycler = (RecyclerView) findViewById(R.id.rv_detailkeranjang);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);


        APIKeranjang api = CONSTANTS.getClient().create(APIKeranjang.class);
        Call<List<Model>> getData = api.getKeranjang();
        getData.enqueue(new Callback<List<Model>>(){
            @Override
            public void onResponse(Call<List<Model>>call, Response<List<Model>> response) {
//                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body());
//                mItems = response.body().get();

                mAdapter = new KeranjangDetailAdapter(KeranjangDetailActivity.this,mItems,ItemKeranjang);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
//                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });

    }
}
