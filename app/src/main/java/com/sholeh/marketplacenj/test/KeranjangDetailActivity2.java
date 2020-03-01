package com.sholeh.marketplacenj.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeranjangDetailActivity2 extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandListScanAdapter expanAdapter;
    private List<HeaderModel> listHeader;
    private HashMap<HeaderModel, List<ChildModel>> listChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang_detail);
        listView = findViewById(R.id.expListhistori);
        tampil();
    }

    private void tampil() {
        listHeader = new ArrayList<>();
        listChild = new HashMap<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.14/mpnj/public/api/keranjang?role=konsumen&id=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String idtoko, namatoko;
                    JSONObject ob = new JSONObject(response);
                    JSONArray a = ob.getJSONArray("data_keranjang");
                    listHeader.clear();
                    listChild.clear();
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        idtoko = c.getString("id_toko");
                        namatoko = c.getString("nama_toko");
                        listHeader.add(new HeaderModel(idtoko, namatoko));
                        List<ChildModel> child = new ArrayList<>();
                        JSONArray jsonArray = c.getJSONArray("item");
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject object = jsonArray.getJSONObject(j);
                            String id_keranjang = object.getString("id_keranjang");
                            String id_produk = object.getString("id_produk");
                            String created_at = object.getString("nama_produk");
                            String updated_at = object.getString("harga_jual");
                            String status = object.getString("jumlah");
                            String gambar = object.getString("foto");

                            child.add(new ChildModel(id_keranjang, id_produk, created_at, updated_at, status, gambar));
                        }
                        listChild.put(listHeader.get(i), child);
                    }
                    expanAdapter = new ExpandListScanAdapter(KeranjangDetailActivity2.this, listHeader, listChild);
                    listView.setAdapter(expanAdapter);
                } catch (JSONException e) {
                    Log.d("KOCENG", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(KeranjangDetailActivity2.this);
        requestQueue.add(stringRequest);
    }

}
