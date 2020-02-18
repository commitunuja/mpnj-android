package com.sholeh.marketplacenj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.KeranjangDetailActivity;
import com.sholeh.marketplacenj.model.Keranjang;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.keranjang.toko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeranjangDetailAdapter extends RecyclerView.Adapter<KeranjangDetailAdapter.MyViewHolder> {
    private List<Model> mList;
//    private List<toko>mListToko;
  private ArrayList<HashMap<String, String>> mListToko;
    private Context ctx;

    public KeranjangDetailAdapter(Context ctx, List<Model> mList, List<Keranjang> mListKeranjang) {
        this.ctx = ctx;
        this.mList = mList;
        this.mListToko = this.mListToko;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang_detail, parent, false);
        MyViewHolder holder = new MyViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model dm = mList.get(position);
        HashMap<String, String> kj = mListToko.get(position);
//        holder.nama.setText(String.valueOf(dm.getNamaProduk()));
//        holder.namatoko.setText(kj.get("nama_toko"));
        holder.namatoko.setText(kj.get("nama_toko"));
//        holder.harga.setText(dm.getHargaJual());
//        holder.jumlah.setText(kj.getJumlah());
//        holder.dm = dm;
    }


    @Override
    public int getItemCount() {
        return 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, jumlah, harga, namatoko;
        Model dm;

        public MyViewHolder(View v) {
            super(v);

            nama = (TextView) v.findViewById(R.id.tv_namatoko);
            harga = (TextView) v.findViewById(R.id.tv_harga);
            jumlah = (TextView) v.findViewById(R.id.tv_jumlah);

          /*  v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, MainActivity.class);
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nama", dm.getNama());
                    goInput.putExtra("usia", dm.getUsia());
                    goInput.putExtra("domisili", dm.getDomisili());

                    ctx.startActivity(goInput);
                }
            });*/
        }
    }
}

