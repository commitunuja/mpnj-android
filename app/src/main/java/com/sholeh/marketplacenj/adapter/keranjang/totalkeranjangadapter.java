package com.sholeh.marketplacenj.adapter.keranjang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;

public class totalkeranjangadapter extends RecyclerView.Adapter<totalkeranjangadapter.viewholder> {

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_total_keranjang, parent, false);


        return new viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView total;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            total = itemView.findViewById(R.id.total);
        }
    }
}
