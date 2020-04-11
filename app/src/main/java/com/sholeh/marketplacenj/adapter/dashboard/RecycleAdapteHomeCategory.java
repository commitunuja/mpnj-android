package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.CategoryModel;
import com.sholeh.marketplacenj.model.Kategori;
import com.sholeh.marketplacenj.model.dashboard.HomeCategoryModelClass;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapteHomeCategory extends RecyclerView.Adapter<RecycleAdapteHomeCategory.MyViewHolder> {
    Context context;

    private ArrayList<HomeCategoryModelClass> semua;
    private final int limit = 4;
    private int tampil;

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
        Kategori movie = moviesList.get(position);
//        HomeCategoryModelClass allcategory = semua.get(position);
        holder.title.setText(movie.getNamaKategori());
//        holder.title.setText(allcategory.getTitle());


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


