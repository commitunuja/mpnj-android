package com.sholeh.marketplacenj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {
    private Context context;
    private List<Model> tvDataProduks ;
    private Model tvDataProduk;

    public ProdukAdapter(Context context, List<Model> tvDataProduks) {
        this.context=context;
        this.tvDataProduks=tvDataProduks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemgrid_allproduct,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        tvDataProduk = tvDataProduks.get(i);
        viewHolder.namaProduk.setText(tvDataProduk.getNamaProduk());
        Picasso.with(context)
                    .load(CONSTANTS.BASE_URL + "assets/foto_produk/" +tvDataProduk.getFoto().get(0).getFotoProduk())
                    .resize(300, 300)
                    .into(viewHolder.foto_produk);
//        int fotoLength = tvDataProduk.getFoto().size();
//        for (int j = i; j <= fotoLength; j++) {
//            Picasso.with(context)
//                    .load("http://192.168.43.25/mpnj-web/public/assets/foto_produk/"+tvDataProduk.getFoto().get(j).getFotoProduk())
//                    .resize(100, 100)
//                    .into(viewHolder.foto_produk);
//        }
    }

    @Override
    public int getItemCount() {
        return tvDataProduks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk;
        private ImageView foto_produk;

        public ViewHolder(View itemView) {
            super(itemView);
            namaProduk = itemView.findViewById(R.id.itemNamaproduct);
            foto_produk = itemView.findViewById(R.id.foto_produk);
        }
    }
}
