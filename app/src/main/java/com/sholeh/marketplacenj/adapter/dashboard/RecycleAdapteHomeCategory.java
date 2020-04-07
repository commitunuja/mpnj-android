package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.ProdukByKategori;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.model.Kategori;

import java.util.List;

public class RecycleAdapteHomeCategory extends RecyclerView.Adapter<RecycleAdapteHomeCategory.MyViewHolder> {
    Context context;

    private String allkategori = "Semua Kategori";
    private final int limit = 4;
    private int tampil;

    private List<Kategori> moviesList;
//    private List<KategoriLihatSemua> kategoriLihatSemuas;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        ImageView image;
        LinearLayout linear;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);

            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteHomeCategory(Context context, List<Kategori> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_list, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Kategori movie = moviesList.get(position);
        holder.title.setText(movie.getNamaKategori());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdukByKategori.class);
                intent.putExtra("id_kategori", String.valueOf(movie.getIdKategoriProduk()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (moviesList.size() > limit) {
//            int i = 1;
//            moviesList.toString();
//            tampil = String.valueOf(Integer.parseInt(String.valueOf(limit + allkategori)));

            String lagi = String.valueOf(limit);
            tampil = Integer.valueOf(lagi);
            return tampil;

        } else {
            return moviesList.size();
        }
    }
}


