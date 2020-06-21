package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.custom.Product;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.Produk;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Model> datapencarian;
//    List<Model> datapencarian2;
    private Model modelpencarian;


    public SearchAdapter(Context context, List<Model> datapencarian) {
        this.context = context;
        this.datapencarian = datapencarian;


    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_search, viewGroup, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int i) {
        modelpencarian = datapencarian.get(i);
        viewHolder.namaProduk.setText(modelpencarian.getNamaProduk()); // MODEL
        viewHolder.hargaProduk.setText(String.valueOf("Rp " + modelpencarian.getHargaJual()));
        viewHolder.type.setText(modelpencarian.getKategori().getNamaKategori());

        Picasso.with(context)
                .load(CONSTANTS.SUB_DOMAIN + modelpencarian.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);
    }

    @Override
    public int getItemCount() {
        return datapencarian.size();
    }

    public void setFilter(List<Model> filterdata) {
        this.datapencarian = filterdata;
        notifyDataSetChanged();
    }
//    public void setFilter2(List<Model> filterdata2) {
//        this.datapencarian2 = filterdata2;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, stok, terjual, deskripsi, type;
        private ImageView foto_produk;


        public ViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.titleproduk);
            hargaProduk = itemView.findViewById(R.id.txthargaawal);
            type = itemView.findViewById(R.id.typeproduk);
            foto_produk = itemView.findViewById(R.id.imageproduk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Model myNewsmodel = datapencarian.get(getAdapterPosition());
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("id_produk", String.valueOf(myNewsmodel.getIdProduk()));
                    intent.putExtra("nama_produk", myNewsmodel.getNamaProduk());
                    intent.putExtra("foto_produk", CONSTANTS.SUB_DOMAIN + myNewsmodel.getFoto().get(0).getFotoProduk());
                    intent.putExtra("harga_jual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("stok", String.valueOf(myNewsmodel.getStok()));
                    intent.putExtra("terjual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("keterangan", myNewsmodel.getKeterangan());
                    intent.putExtra("kategori", myNewsmodel.getKategori().getNamaKategori());
                    intent.putExtra("diskon", String.valueOf(myNewsmodel.getDiskon()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    Toast.makeText(context, "id_produk"+myNewsmodel.getIdProduk(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                }
            });
        }
    }
}
