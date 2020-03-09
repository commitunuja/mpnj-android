package com.sholeh.marketplacenj.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.KeranjangDetailAdapter;
import com.sholeh.marketplacenj.model.Keranjang;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.test.ExpandListScanAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeranjangDetailActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private KeranjangDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<Model> mItems = new ArrayList<>();
    private List<Keranjang> itemKeranjang = new ArrayList<>();
    ArrayList<HashMap<String, String>> tampil = new ArrayList<HashMap<String, String>>();
    private ExpandListScanAdapter expanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);

    }
//        getData.enqueue(new Callback<List<Model>>(){
//            @Override
//            public void onResponse(Call<List<Model>>call, Response<List<Model>> response) {
////                pd.hide();
//                Log.d("RETRO", "RESPONSE : " + response.body());
//
//
////                mItems = response.body().get();
//
//                mAdapter = new KeranjangDetailAdapter(KeranjangDetailActivity.this,mItems,itemKeranjang1);
//                mRecycler.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Model>> call, Throwable t) {
////                pd.hide();
//                Log.d("RETRO", "FAILED : " +t);
//
//            }
//        });

}
