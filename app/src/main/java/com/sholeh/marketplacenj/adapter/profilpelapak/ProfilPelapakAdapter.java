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

}
