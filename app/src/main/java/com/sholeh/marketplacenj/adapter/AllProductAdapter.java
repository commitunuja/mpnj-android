package com.sholeh.marketplacenj.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.AllProductModel;

import java.util.List;


public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.MyViewHolder> {

    List<AllProductModel> mprodukList;

    public AllProductAdapter(List <AllProductModel> AllProdukList) {
        mprodukList= AllProdukList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgrid_allproduct, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        MyViewHolder myHolder =  holder;
        AllProductModel result = mprodukList.get(position);

        myHolder.tvx_namaproduk.setText(result.getNama_produk());
        myHolder.tvx_hargaproduk.setText(result.getHarga());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
//                mIntent.putExtra("Id", mKontakList.get(position).getId());
//                mIntent.putExtra("Nama", mKontakList.get(position).getNama());
//                mIntent.putExtra("Nomor", mKontakList.get(position).getNomor());
//                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mprodukList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvx_namaproduk, tvx_hargaproduk;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvx_namaproduk = itemView.findViewById(R.id.itemNamaproduct);
            tvx_hargaproduk= (TextView) itemView.findViewById(R.id.itemhargaProduct);
        }
    }

//    private Context context;
//    private List<AllProductModel> results;
//
//    public AllProductAdapter(Context context, List<AllProductModel> results) {
//        this.context = context;
//        this.results = results;
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemgrid_allproduct, null);
//        MyHolder holder = new MyHolder(v);
//
//        return holder;
//    }
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        MyHolder myHolder = (MyHolder) holder;
//        AllProductModel result = results.get(position);
//
//        myHolder.tvx_namaproduk.setText(result.getNama_produk());
//        myHolder.tvx_hargaproduk.setText(result.getHarga());
//    }
//    @Override
//    public int getItemCount() {
//        return results.size();
//    }
//
//    class MyHolder extends RecyclerView.ViewHolder {
//        TextView tvx_namaproduk, tvx_hargaproduk;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            tvx_namaproduk = itemView.findViewById(R.id.itemNamaproduct);
//            tvx_hargaproduk= (TextView) itemView.findViewById(R.id.itemhargaProduct);
//
//        }
//
//    }
}
