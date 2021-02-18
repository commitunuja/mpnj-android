package com.sholeh.marketplacenj.adapter;

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
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdukByKategoriAdapter extends RecyclerView.Adapter<ProdukByKategoriAdapter.ViewHolder> {
    private Context context;
    private List<Model> dataProdukByKategori;
    private Model modelProdukByKategori;


    public ProdukByKategoriAdapter(Context context, List<Model> dataProdukByKategori) {
        this.context = context;
        this.dataProdukByKategori = dataProdukByKategori;
    }

    @NonNull
    @Override
    public ProdukByKategoriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_search, viewGroup, false);
        return new ProdukByKategoriAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukByKategoriAdapter.ViewHolder viewHolder, int i) {
        modelProdukByKategori = dataProdukByKategori.get(i);
        viewHolder.namaProduk.setText(modelProdukByKategori.getNamaProduk()); // MODEL
        viewHolder.hargaProduk.setText(String.valueOf("Rp " + modelProdukByKategori.getHargaJual()));
        viewHolder.type.setText(modelProdukByKategori.getKategori().getNamaKategori());

        Picasso.with(context)
                .load(CONSTANTS.SUB_DOMAIN + modelProdukByKategori.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);
    }

    @Override
    public int getItemCount() {
        return dataProdukByKategori.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, stok, terjual, deskripsi, type;
        private ImageView foto_produk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.titleproduk);
            hargaProduk = itemView.findViewById(R.id.txthargaawal);
            type = itemView.findViewById(R.id.typeproduk);
            foto_produk = itemView.findViewById(R.id.imageproduk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Model myNewsmodel = dataProdukByKategori.get(getAdapterPosition());
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
//                    Toast.makeText(context, "id_produk"+myNewsmodel.getIdProduk(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                }
            });
        }
    }
}