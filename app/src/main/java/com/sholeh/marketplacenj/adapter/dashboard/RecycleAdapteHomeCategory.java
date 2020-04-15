package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.ProdukByKategori;
import com.sholeh.marketplacenj.activities.dashboard.Homepage;
import com.sholeh.marketplacenj.model.Kategori;
import com.sholeh.marketplacenj.model.dashboard.HomeCategoryModelClass;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapteHomeCategory extends RecyclerView.Adapter<RecycleAdapteHomeCategory.MyViewHolder> {
    Context context;

    private ArrayList<HomeCategoryModelClass> semua;
    private final int limit = 4;

    private List<Kategori> moviesList;
//    private List<KategoriLihatSemua> kategoriLihatSemuas;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title, semua;
        ImageView image;
        LinearLayout linear;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
//            semua = view.findViewById(R.id.tv_semua);

            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteHomeCategory(Context context, List<Kategori> moviesList, ArrayList<HomeCategoryModelClass> semua) {
        this.moviesList = moviesList;
        this.semua = semua;
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


//        HomeCategoryModelClass allcategory = semua.get(position);

        final Kategori movie = moviesList.get(position);
        holder.title.setText(movie.getNamaKategori());
//        holder.title.setText(allcategory.getTitle());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProdukByKategori.class);
                intent.putExtra("id_kategori", String.valueOf(movie.getIdKategoriProduk()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("PESAN", movie.getNamaKategori());
                context.startActivity(intent);


//                }
//                Homepage homepage = new Homepage();
//                homepage.kategoriById();
////                Toast.makeText(context, "coba", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, Homepage.class);
////                intent.putExtra("id_kategori", String.valueOf(movie.getIdKategoriProduk()));
//                Log.d("PESAN", movie.getNamaKategori());
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
//        if (moviesList.size() >  limit) {
//            return limit;
//
//
//        } else {
//            return Math.min(moviesList.size(), limit) + 1;
        return moviesList.size();
//        }
    }
}


