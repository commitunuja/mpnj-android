package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {
    private Context context;
    private List<Model> tvDataProduks ; // model / item
    private Model tvDataProduk;



    public ProdukAdapter(Context context, List<Model> tvDataProduks) {
        this.context=context;
        this.tvDataProduks=tvDataProduks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_ten_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        tvDataProduk = tvDataProduks.get(i);
        viewHolder.namaProduk.setText(tvDataProduk.getNamaProduk()); // MODEL
        viewHolder.hargaProduk.setText(String.valueOf(tvDataProduk.getHargaJual()));
        viewHolder.type.setText(tvDataProduk.getKategori().getNamaKategori());

        Picasso.with(context)
                .load(CONSTANTS.BASE_URL + "assets/foto_produk/" +tvDataProduk.getFoto().get(0).getFotoProduk())
                .load(CONSTANTS.SUB_DOMAIN +tvDataProduk.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);
    }

    @Override
    public int getItemCount() {
        return tvDataProduks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, stok, terjual, deskripsi , type;
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
                    intent.putExtra("id_produk",String.valueOf(myNewsmodel.getIdProduk()) );
                    intent.putExtra("nama_produk", myNewsmodel.getNamaProduk());
                    intent.putExtra("foto_produk",CONSTANTS.BASE_URL + "assets/foto_produk/"+myNewsmodel.getFoto().get(0).getFotoProduk());
                    intent.putExtra("foto_produk",CONSTANTS.SUB_DOMAIN+myNewsmodel.getFoto().get(0).getFotoProduk());
                    intent.putExtra("harga_jual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("stok", String.valueOf(myNewsmodel.getStok()));
                    intent.putExtra("terjual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("keterangan", myNewsmodel.getKeterangan());
                    intent.putExtra("kategori", myNewsmodel.getKategori().getNamaKategori());
                    intent.putExtra("diskon", String.valueOf(myNewsmodel.getDiskon()));
                    Toast.makeText(context, "id_produk"+myNewsmodel.getIdProduk(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                }
            });
        }


    }
}
