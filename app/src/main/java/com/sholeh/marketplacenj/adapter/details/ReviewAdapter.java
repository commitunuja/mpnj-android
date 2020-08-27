package com.sholeh.marketplacenj.adapter.details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.dashboard.ProdukAdapter;
import com.sholeh.marketplacenj.model.review.HeaderReview;
import com.sholeh.marketplacenj.model.review.Review;
import com.sholeh.marketplacenj.model.review.ReviewModel;

import java.util.List;
import java.util.zip.Inflater;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Holder> {
    Context context;
    private List<ReviewModel> reviewModels;
    private ReviewModel reviewModel;



    public ReviewAdapter (Context context, List<ReviewModel> reviewModels){
        this.context = context;
        this.reviewModels = reviewModels;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        reviewModel = reviewModels.get(position);
        holder.nama.setText(reviewModel.getReviewer());
        holder.tanggal.setText(reviewModel.getReview());
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView nama, diskripsi, tanggal;

        public Holder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tvnamareview);
            diskripsi  = itemView.findViewById(R.id.tvdiskripsireview);
            tanggal  = itemView.findViewById(R.id.tvtglreview);

        }
    }
}
