package com.sholeh.marketplacenj.activities.pesanan;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.pesanan.DetailPesananAdapter;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.model.pesanan.Item;
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.DetailPesanan;
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.ItemDetailPesanan;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class DetailPesananActivity extends AppCompatActivity implements View.OnClickListener {

    String namaproduk, namatoko, harga, foto, fotokirimwa, bitmpath;
    TextView vnamaproduk, waktu, totalhargaproduk, total, vharga, toko, status, alamat, totalhargadetail, kurir, tongkir, tanya;
    ImageView iproduk, imgcoba;
    private List<ItemDetailPesanan> dataPesanans;
    RecyclerView recyclerView;
    private HashMap<DataPesanan, List<Item>> item;
    String kode, pack;
    DetailPesananAdapter recyclerdetailpesanan;
    RecyclerView.LayoutManager layoutManager;
    Bitmap bitmap;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer stringTokenizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        pack = "tanya";
        imgcoba = findViewById(R.id.img);
        tanya = findViewById(R.id.tv_tanya);
        totalhargadetail = findViewById(R.id.totalhargadetail);
        vnamaproduk = findViewById(R.id.tv_nama_produk_detail);
        vharga = findViewById(R.id.tv_harga_produk_detail);
        iproduk = findViewById(R.id.img_produk_detail);
        recyclerView = findViewById(R.id.recycler_pesanan_detail);
        toko = findViewById(R.id.tv_toko);
        waktu = findViewById(R.id.tv_waktupembelian);
        status = findViewById(R.id.tv_status_order_detail);
        totalhargaproduk = findViewById(R.id.tv_totalhargaproduk);
        total = findViewById(R.id.tv_total_detail_bayar);
        alamat = findViewById(R.id.tv_alamat_kirim);
        kurir = findViewById(R.id.tv_kurir);
        tongkir = findViewById(R.id.tv_ongkir);
        Intent i = getIntent();
        kode = i.getStringExtra("kode");
        tanya.setOnClickListener(this);

        getData();

    }


    public void getData() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<DetailPesanan> call = service.getDataDetailPesanan(String.valueOf(kode));

        dataPesanans = new ArrayList<>();
        item = new HashMap<>();
        call.enqueue(new Callback<DetailPesanan>() {
            @Override
            public void onResponse(Call<DetailPesanan> call, retrofit2.Response<DetailPesanan> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {

                        dataPesanans.clear();
                        item.clear();
                        List<ItemDetailPesanan> array = response.body().getData();
                        for (int i = 0; i < array.size(); i++) {

                            dataPesanans.add(new ItemDetailPesanan(response.body().getData().get(i).getNamaToko(),
                                    response.body().getData().get(i).getNamaProduk(),
                                    response.body().getData().get(i).getJumlah(),
                                    response.body().getData().get(i).getTotalBayar(),
                                    response.body().getData().get(i).getHarga(),
                                    response.body().getData().get(i).getFotoProduk(),
                                    response.body().getData().get(i).getStatusOrder(),
                                    response.body().getData().get(i).getBayarSebelum(),
                                    response.body().getData().get(i).getIdTransaksiDetail(),
                                    response.body().getData().get(i).getKurir(),
                                    response.body().getData().get(i).getOngkir(),
                                    response.body().getData().get(i).getWaktuPesan()));


                            fotokirimwa = response.body().getData().get(i).getFotoProduk();
                            Glide.with(getApplication()).asBitmap().load("https://www.google.es/images/srpr/logo11w.png").into(new CustomTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    imgcoba.setImageBitmap(resource);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                }
                            });

                            toko.setText(response.body().getData().get(i).getNamaToko());
                            waktu.setText(response.body().getData().get(i).getWaktuPesan());
                            status.setText(response.body().getData().get(i).getStatusOrder());
                            kurir.setText(response.body().getData().get(i).getKurir().toString());

                            int ongkir = Integer.parseInt((response.body().getData().get(i).getOngkir()));
                            stringTokenizer = new StringTokenizer(formatRupiah.format(ongkir), ",");
                            String hargaongkir = stringTokenizer.nextToken().trim();
                            tongkir.setText(hargaongkir);

                            int total1 = Integer.parseInt(response.body().getData().get(i).getTotalBayar());
                            stringTokenizer = new StringTokenizer(formatRupiah.format(total1), ",");
                            String totalbayar = stringTokenizer.nextToken().trim();
                            total.setText(totalbayar);
                            alamat.setText(response.body().getData().get(i).getTujuan());
                            int harga = Integer.parseInt(response.body().getData().get(i).getHarga());
                            stringTokenizer = new StringTokenizer(formatRupiah.format(harga), ",");
                            String harga1 = stringTokenizer.nextToken().trim();
                            totalhargadetail.setText(harga1);

                            if (status.equals("Selesai")) {
                                tanya.setText("Tulis Review");
                            }
                            tanya.setText("Tanya Pelapak");

                        }

                    } else {

                    }
                    recyclerdetailpesanan = new DetailPesananAdapter(getContext(), dataPesanans);
                    recyclerView.setAdapter(recyclerdetailpesanan);
                }
            }

            @Override
            public void onFailure(Call<DetailPesanan> call, Throwable t) {

            }
        });

    }

    public Bitmap StringToBitMap(String fotokirimwa){
        try{
            byte [] encodeByte=Base64.decode(fotokirimwa,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            imgcoba.setImageBitmap(bitmap);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public void onClickApp(String fotokirimwa) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imgcoba.setImageBitmap(decodedImage);
//            byte [] encodeByte= Base64.decode(fotokirimwa,Base64.DEFAULT);
//            bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//
        bitmpath = MediaStore.Images.Media.insertImage(getContentResolver(), decodedImage, "whatsApp", null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Uri uri = Uri.parse(bitmpath);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.setPackage("com.whatsapp");
        share.putExtra(Intent.EXTRA_TEXT, "Apakah Bisa Dikirim Hari Ini ?");
//        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "share into "));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tanya:
                onClickApp(fotokirimwa);
//                StringToBitMap(fotokirimwa);
        }
    }
}