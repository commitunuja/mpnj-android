package com.sholeh.marketplacenj.adapter.profilpelapak;

public class ProfilPelapakAdapter {
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

        tvDataProduk = tvDataProduks.get(i);
        viewHolder.namaProduk.setText(tvDataProduk.getNamaProduk()); // MODEL
        viewHolder.hargaProduk.setText(String.valueOf("Rp " + tvDataProduk.getHargaJual()));
        viewHolder.type.setText(tvDataProduk.getKategori().getNamaKategori());

        Picasso.with(context)
                .load(CONSTANTS.SUB_DOMAIN + tvDataProduk.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);
    }

}
