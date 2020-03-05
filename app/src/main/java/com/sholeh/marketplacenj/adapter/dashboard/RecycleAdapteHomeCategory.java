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
import com.sholeh.marketplacenj.model.dashboard.HomeCategoryModelClass;

import java.util.List;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteHomeCategory extends RecyclerView.Adapter<RecycleAdapteHomeCategory.MyViewHolder> {
    Context context;


    private List<HomeCategoryModelClass> moviesList;

    int myPos = 0;


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


    public RecycleAdapteHomeCategory(Context context, List<HomeCategoryModelClass> moviesList) {
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
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        HomeCategoryModelClass movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());




    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}


