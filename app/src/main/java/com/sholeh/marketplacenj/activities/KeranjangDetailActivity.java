package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.adapter.KeranjangDetailAdapter;
import com.sholeh.marketplacenj.adapter.ProdukAdapter;
import com.sholeh.marketplacenj.model.Keranjang;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.api.APIKeranjang;
import com.sholeh.marketplacenj.model.keranjang.toko;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangDetailActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private KeranjangDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<Model> mItems = new ArrayList<>();
    private List<Keranjang> itemKeranjang = new ArrayList<>();
    ArrayList<HashMap<String, String>> tampil = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);
        mRecycler = (RecyclerView) findViewById(R.id.rv_detailkeranjang);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        APIKeranjang service = ServiceGenerator.getRetrofit().create(APIKeranjang.class);
        Call<JsonObject> call = service.getToko("konsumen", "1");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                    Iterator<String> keys = jsonObject.keys();
                    while (keys.hasNext()) {
                        String k = keys.next();
                        toko p = new toko(k);
                        try {
                            JSONArray array = jsonObject.getJSONArray(k);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("nama_toko", k);
                            tampil.add(map);
//                            KeranjangDetailAdapter keranjangDetailAdapter = new KeranjangDetailAdapter(KeranjangDetailActivity.this, p);
                            Log.d("TOKO", String.valueOf(k));
                            Log.d("DATA", String.valueOf(array));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
//                    mAdapter = new KeranjangDetailAdapter(KeranjangDetailActivity.this, tampil);
                    mRecycler.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    JSONArray jsonArray = obj.getJSONArray("Toko Indra");
//                    for (String key : iterate(obj.keys()))
//                    {
//                        // here key will be containing your OBJECT NAME YOU CAN SET IT IN TEXTVIEW.
//                        Toast.makeText(HomeActivity.this, ""+key, Toast.LENGTH_SHORT).show();
//                    }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("YOLO", String.valueOf(t));
            }
        });
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
