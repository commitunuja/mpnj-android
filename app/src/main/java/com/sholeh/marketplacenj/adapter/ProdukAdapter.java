package com.sholeh.marketplacenj.adapter;

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
import com.sholeh.marketplacenj.activities.DetailProdukActivity;
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
//        viewHolder.stok.setText(String.valueOf(tvDataProduk.getStok()));
//        viewHolder.terjual.setText(String.valueOf(tvDataProduk.getTerjual()));
//        viewHolder.deskripsi.setText(String.valueOf(tvDataProduk.getKeterangan()));

        Picasso.with(context)
//                .load(CONSTANTS.BASE_URL + "assets/foto_produk/" +tvDataProduk.getFoto().get(0))
                .load(CONSTANTS.BASE_URL + "assets/foto_produk/" +tvDataProduk.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);
//        int fotoLength = tvDataProduk.getFoto().size();
//        for (int j = i; j <= fotoLength; j++) {
//            Picasso.with(context)
//                    .load("http://192.168.43.25/mpnj-web/public/assets/foto_produk/"+tvDataProduk.getFoto().get(j).getFotoProduk())
//                    .resize(100, 100)
//                    .into(viewHolder.foto_produk);
//        }


//        viewHolder.cardproduk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, DetailProdukActivity.class);
//                i.putExtra("nama_produk", tvDataProduk.getNamaProduk());
//
//                context.startActivity(i);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return tvDataProduks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, stok, terjual, deskripsi ;
        private ImageView foto_produk;


        public ViewHolder(View itemView) {
            super(itemView);
          /*  foto_produk = (ImageView) view.findViewById(R.id.imageproduk);
            namaProduk = (TextView) view.findViewById(R.id.titleproduk);
            stok = (TextView) view.findViewById(R.id.typeproduk);*/

            namaProduk = itemView.findViewById(R.id.titleproduk);
            hargaProduk = itemView.findViewById(R.id.typeproduk);
            foto_produk = itemView.findViewById(R.id.imageproduk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Model myNewsmodel = tvDataProduks.get(getAdapterPosition());
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetailProdukActivity.class);
                    intent.putExtra("id_produk",String.valueOf(myNewsmodel.getIdProduk()) );
                    intent.putExtra("nama_produk", myNewsmodel.getNamaProduk());
                    intent.putExtra("foto_produk",CONSTANTS.BASE_URL + "assets/foto_produk/"+myNewsmodel.getFoto().get(0).getFotoProduk());
                    intent.putExtra("harga_jual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("stok", String.valueOf(myNewsmodel.getStok()));
                    intent.putExtra("terjual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("keterangan", myNewsmodel.getKeterangan());
                    Toast.makeText(context, "id_produk"+myNewsmodel.getIdProduk(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);



                }
            });
        }





    }
}
