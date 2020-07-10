package com.sholeh.marketplacenj.adapter.profilpelapak;

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
import com.sholeh.marketplacenj.adapter.dashboard.ProdukAdapter;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfilPelapakAdapter extends RecyclerView.Adapter<ProfilPelapakAdapter.ViewHolder> {
    private Context context;
    private List<Model> tvDataProduks; // model / item
    private Model tvDataProduk;
    private List<Model> getAllData;
    //    private Model getalldata;
    private List<Foto> tampil;


    public ProfilPelapakAdapter (Context context, List<Model> tvDataProduks) {
        this.context = context;
        this.tvDataProduks = tvDataProduks;


    }




    @NonNull
    @Override
    public ProfilPelapakAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_search, viewGroup, false);
        return new ProfilPelapakAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilPelapakAdapter.ViewHolder viewHolder, int i) {
        tvDataProduk = tvDataProduks.get(i);
        viewHolder.namaProduk.setText(tvDataProduk.getNamaProduk()); // MODEL
        viewHolder.hargaProduk.setText(String.valueOf("Rp " + tvDataProduk.getHargaJual()));
        viewHolder.type.setText(tvDataProduk.getKategori().getNamaKategori());

        Picasso.with(context)
                .load(CONSTANTS.SUB_DOMAIN + tvDataProduk.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);
    }

    @Override
    public int getItemCount() {

        return tvDataProduks.size();
    }

    public void setFilter(List<Model> filterdata) {
        this.tvDataProduks = filterdata;
        notifyDataSetChanged();
    }

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
                    Model myNewsmodel = tvDataProduks.get(getAdapterPosition());
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
