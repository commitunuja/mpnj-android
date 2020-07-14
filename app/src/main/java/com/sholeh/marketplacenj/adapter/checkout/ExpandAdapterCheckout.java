package com.sholeh.marketplacenj.adapter.checkout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.activities.kurir.OpsiPengirimanActivity;
import com.sholeh.marketplacenj.model.checkout.ChildCheckout;
import com.sholeh.marketplacenj.model.checkout.HeaderCheckout;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.Preferences;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class ExpandAdapterCheckout extends BaseExpandableListAdapter {
    int jumlahProduk = 0;
    int klikOpsipengiriman = 0;
    double subtotalHarga = 0;
    double subPengiriman = 0;
    double totalbayar = 0;
    private Context context;
    private List<HeaderCheckout> listHeaderFilter;
    private HashMap<HeaderCheckout, List<ChildCheckout>> listChild;
    ConstraintLayout linearLayout;
    int hargaProduk, stokProduk;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private static final String TAG = "MyExpandAdapter";
    String CUSTOM_ACTION = "com.example.YOUR_ACTION";
    String id_kabupate, id_kecamatan;
    Integer total_berat;
    List<String> idk;
    String idKecPembeli;
    String cekOngkos;

    CheckoutActivity checkoutActivity;

    ArrayList<String> myOngkos = new ArrayList<>();

    public ExpandAdapterCheckout(Context context, List<HeaderCheckout> listHeader, HashMap<HeaderCheckout, List<ChildCheckout>> listChild) {
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
        this.context = context;
//        Integer my_id = ((CheckoutActivity) context.getApplicationContext()()).getMy_id();
//        checkoutActivity = (CheckoutActivity) activity;
    }

    @Override
    public int getGroupCount() {
        return this.listHeaderFilter.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChild.get(listHeaderFilter.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeaderFilter.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listChild.get(this.listHeaderFilter.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }



    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final HeaderCheckout model = (HeaderCheckout) getGroup(groupPosition);
        final HeaderCheckout headerModel = listHeaderFilter.get(groupPosition);
        // hitung jumlah parent / jika belum sesuai dengan panjang maka tidak di set sub total pengiriman, jika melalui ongkir kesulitan validasi cek opsi


        final List<ChildCheckout> childModel = listChild.get(listHeaderFilter.get(groupPosition));

        convertView = LayoutInflater.from(context).inflate(R.layout.desain_parent_checkout, null);
        final Dialog myDialog;
        final TextView nama_kk, no_pelanggan, idKabPenjual, tvx_OpsiKurir1, tvxkurir, tvxservice, tvxongkos, tvxetd, vOngkos;
        final LinearLayout lnkurir;
        linearLayout = convertView.findViewById(R.id.linearopsi);
        nama_kk = convertView.findViewById(R.id.txtNamaToko);
        no_pelanggan = convertView.findViewById(R.id.txtIdToko);
        tvxkurir = convertView.findViewById(R.id.tvxkurirnya);
        tvxservice = convertView.findViewById(R.id.tvxservice);
        tvxongkos = convertView.findViewById(R.id.tvongkos);
        vOngkos = convertView.findViewById(R.id.v_ongkoskirim);
        tvxetd = convertView.findViewById(R.id.tvxetd);
        lnkurir = convertView.findViewById(R.id.ln_Kurir);
//        tvxAkanditerima = convertView.findViewById(R.id.tvAkanditerima);
        myDialog = new Dialog(context);



//        getSubOngkir(model);

        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());

//        Toast.makeText(context, "ongkos"+model.getOngkir(), Toast.LENGTH_SHORT).show();

        String getresetKurir = ((CheckoutActivity) context).resetKurir();


//
//        if (getresetKurir != null ) {
//            tvxkurir.setText("Silahkan Pilih Opsi Pengiriman Produk Anda");
//            tvxservice.setText("");
//            tvxongkos.setText("0");
//            tvxetd.setText("");
//            Intent intent5 = new Intent("custom-validasiopsi2");
//            intent5.putExtra("validasiopsi2", String.valueOf("Rp0"));
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intent5);
////            cekOngkos = tvxongkos.getText().toString();
////            Log.d("cekongkos1", cekOngkos);
//
////            Toast.makeText(context, "!null", Toast.LENGTH_SHORT).show();
//////            Toast.makeText(context, "!= null", Toast.LENGTH_SHORT).show();
//////
////        }else if(model.getKurir() != null){
////                lnkurir.setVisibility(View.VISIBLE);
//        }else { // jika tidak mengubah alamat utama
////            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
            int ongkir = Integer.parseInt(model.getOngkir());
        StringTokenizer konvert;
        konvert = new StringTokenizer(formatRupiah.format(ongkir), ",");
        String hargaOngkir = konvert.nextToken().trim();


        String kurir = model.getKurir();
            tvxkurir.setText(kurir);
            tvxservice.setText(model.getService());
            tvxongkos.setText(hargaOngkir);
            tvxetd.setText("Diterima dalam "+model.getEtd()+" Hari");
//            Intent intent5 = new Intent("custom-validasiopsi2");
//            intent5.putExtra("validasiopsi2", String.valueOf("Oke"));
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intent5);
//
////            cekOngkos = tvxongkos.getText().toString(); // 0
////            Log.d("cekongkos2", cekOngkos);
//
//
//
////            lnkurir.setVisibility(View.VISIBLE);
//////            Toast.makeText(context, " null", Toast.LENGTH_SHORT).show();
//        }


//        Toast.makeText(context, "kurir "+kurir, Toast.LENGTH_SHORT).show();
        if (kurir != null ) {
            lnkurir.setVisibility(View.VISIBLE);
            vOngkos.setVisibility(View.VISIBLE);

////            tvxkurir.setVisibility(View.VISIBLE);
////            tvxservice.setVisibility(View.VISIBLE);
////            tvxongkos.setVisibility(View.VISIBLE);
////            tvxetd.setVisibility(View.VISIBLE);
////            tvxAkanditerima.setVisibility(View.VISIBLE);
        }else if(kurir == null){
            lnkurir.setVisibility(View.GONE);
            vOngkos.setVisibility(View.GONE);

////            tvxkurir.setVisibility(View.GONE);
////            tvxservice.setVisibility(View.GONE);
////            tvxongkos.setVisibility(View.GONE);
////            tvxetd.setVisibility(View.GONE);
////            tvxAkanditerima.setVisibility(View.GONE);
        }
//





//        Toast.makeText(context, "cekO "+cekOngkos, Toast.LENGTH_SHORT).show();

//        Log.d("idkecpembeli checkout", String.valueOf(model.get));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                klikOpsipengiriman +=1;


//                Log.d("klik", String.valueOf(klikOpsipengiriman));

                HeaderCheckout myNewsheader = listHeaderFilter.get(groupPosition);
                Context context = v.getContext();
//                listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);

//                for (int i = groupPosition; i < getChildrenCount(groupPosition); i++) {
////                    idk.add(getChild(groupPosition, ));
//                }

//                Log.d("Child Position", String.valueOf(getChildrenCount(groupPosition)));
                ResDetailKeranjang bs = ((CheckoutActivity) context).getbs();
//                Log.d("ikdo", String.valueOf());
                ArrayList<String> id = ((CheckoutActivity) context).listIdKeranjang();
                ArrayList<String> idByParent = new ArrayList<String>();

                for (int i = 0; i < bs.getDataKeranjang().get(groupPosition).getItem().size(); i++) {
                    idByParent.add(bs.getDataKeranjang().get(groupPosition).getItem().get(i).getIdKeranjang());
                }

                String idKabPenjual = bs.getDataKeranjang().get(groupPosition).getIdKabupaten();
               String idKecPembeli = bs.getPembeli().getIdKecamatan();



//                Toast.makeText(context, "idkec"+idKecPembeli, Toast.LENGTH_SHORT).show();

                String nama_kota = bs.getDataKeranjang().get(groupPosition).getNamaKota();
                String weight = bs.getDataKeranjang().get(groupPosition).getTotalBerat();
                Intent intent = new Intent(context, OpsiPengirimanActivity.class);
                intent.putExtra("idkab_toko", String.valueOf(idKabPenjual));
                intent.putExtra("idkec_pembeli", String.valueOf(idKecPembeli));
                intent.putExtra("weight", String.valueOf(weight));
                intent.putStringArrayListExtra("idcheckout", id);
                intent.putStringArrayListExtra("idByParent", idByParent);
                context.startActivity(intent);
//                ((CheckoutActivity) context).finish();
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
//
            }
        });



        return convertView;
    }


    @SuppressLint("ResourceType")
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        Log.d("sholeh", listHeaderFilter.get(groupPosition).getId_toko());


        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();

        convertView = LayoutInflater.from(context).inflate(R.layout.desain_child_checkout, null);


        final TextView tvx_nama, tvx_idKeranjang, tvx_jumlahproduk, tvx_harga, tvx_totharga, tvx_hargaDiskon;
        final ImageView img_gambar;

        final ChildCheckout childCheckout = listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);

        TextView opsipengiriman;
        LinearLayout linearLayoutOpsi_Pengiriman;

//        opsipengiriman = convertView.findViewById(R.id.tvx_opsiKurir1);
//        linearLayout = convertView.findViewById(R.id.linearopsi);
        tvx_nama = convertView.findViewById(R.id.txtnamaPRODUK);
//        tvx_idKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        tvx_harga = convertView.findViewById(R.id.tvx_hargacheckout);
        tvx_totharga = convertView.findViewById(R.id.tvx_totalhargacheckout);
//        tvx_hargaDiskon = convertView.findViewById(R.id.tvxHrgaDiskon);
        tvx_jumlahproduk = convertView.findViewById(R.id.tvxJumlahProduk);
        img_gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        tvx_nama.setText(childCheckout.getNama_produk());
//        tvx_idKeranjang.setText(childCheckout.getId_keranjang());

        int  hargaawal = childCheckout.getHarga();
        int jumlahProduk = childCheckout.getJumlah();
        double diskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(childCheckout.getDiskon()))));
        tvx_jumlahproduk.setText(String.valueOf(jumlahProduk));



        double h = diskon / 100 * hargaawal;
        double p = hargaawal - h;
        double hitungTotal = jumlahProduk * p;
        StringTokenizer st = new StringTokenizer(formatRupiah.format(p), ",");
        String hargaDiskon = st.nextToken().trim();
        StringTokenizer stTotal = new StringTokenizer(formatRupiah.format(hitungTotal), ",");
        String hargaTotal = stTotal.nextToken().trim();
        tvx_harga.setText(hargaDiskon);
        tvx_totharga.setText(hargaTotal);



//        int hitungJumHarga = jumlahProduk * hargaProduk;
//
//
//        tvx_totharga.setText(hargajum);

        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + childCheckout.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(img_gambar);


//        if (childCheckout.getDiskon() == 0) {
//            tvx_hargaDiskon.setVisibility(View.GONE);
//        } else {
//            double h = vdiskon / 100 * hargaProduk;
//            double p = hargaProduk - h;
//            double hitung = jumlahProduk * p;
//
//            tvx_hargaDiskon.setPaintFlags(tvx_harga.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            tvx_hargaDiskon.setTextColor(context.getResources().getColor(R.color.redTransparent));
//            tvx_hargaDiskon.setTypeface(tvx_harga.getTypeface(), Typeface.NORMAL);
//            tvx_hargaDiskon.setVisibility(View.VISIBLE);
//            tvx_hargaDiskon.setText(hargajum);
//            tvx_harga.setText(harganya);
//            tvx_totharga.setText(harganya);
//        }


//        if (linearLayoutOpsi_Pengiriman.Leng){
//
//            linearLayoutOpsi_Pengiriman.setGravity(View.VISIBLE);
//        }
//        if(isLastChild == true ) {
//            linearLayoutOpsi_Pengiriman.setVisibility(View.VISIBLE);
//        }
//        tvx_OpsiKurir1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HeaderCheckout myNewsheader =listHeaderFilter.get(groupPosition);
//                Context context = v.getContext();
//                ResDetailKeranjang bs = ((CheckoutActivity) context).getbs();
//
//                String idKabPenjual = bs.getDataKeranjang().get(groupPosition).getIdKabupaten();
//                String idKecPembeli = bs.getPembeli().getIdKecamatan();
//
//                Intent intent= new Intent(context, OpsiPengirimanActivity.class);
//                intent.putExtra("idkab_toko", String.valueOf(idKabPenjual));
//                intent.putExtra("idkec_pembeli", String.valueOf(idKecPembeli));
//                context.startActivity(intent);
//            }
//        });

        getsubTotal();

        return convertView;


    }


    private boolean isLastChild(int groupPosition, int childPosition) {
        return (childPosition == getChildrenCount(groupPosition) - 1);
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void getSubOngkir(HeaderCheckout headerCheckout){
        String kurir = headerCheckout.getKurir();
        String service = headerCheckout.getService();
        String ongkos = headerCheckout.getOngkir();
        String etd = headerCheckout.getEtd();
        headerCheckout.setKurir(kurir);
        headerCheckout.setService(service);
        headerCheckout.setOngkir(ongkos);
        headerCheckout.setEtd(etd);
        notifyDataSetChanged();
    }

    public void getsubTotal() {
        jumlahProduk = 0; //totalCount
        subtotalHarga = 0;
        subPengiriman = 0;
        totalbayar = 0;
        ArrayList<String> myArrayOngkir = new ArrayList<>();
        String getid = null;
        int valP = 0;

        for (int i = 0; i < listHeaderFilter.size(); i++) {

            HeaderCheckout headerCheckout = listHeaderFilter.get(i);

            double hargaPengiriman = Double.parseDouble(headerCheckout.getOngkir());
            valP = (int) hargaPengiriman;
            myArrayOngkir.add(String.valueOf(valP));

         
            subPengiriman +=hargaPengiriman;


            List<ChildCheckout> childMapList = listChild.get(listHeaderFilter.get(i));
            for (int j = 0; j < childMapList.size(); j++) {
                ChildCheckout childModel = childMapList.get(j);
                int jumlah = childMapList.get(j).getJumlah();
                double Harga = childMapList.get(j).getHarga();
                double diskonHarga = childModel.getDiskon();
                double h = diskonHarga / 100 * Harga;
                double p = Harga - h;
                subtotalHarga += p * jumlah;


//                Intent intent = new Intent("custom-message");
//                intent.putExtra("total", String.valueOf(p));
//                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


            }
        }
        Intent intent = new Intent("custom-message");
        intent.putExtra("total", String.valueOf(subtotalHarga));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        Intent intent2 = new Intent("custom-ongkir");
        intent2.putExtra("ongkir", String.valueOf(subPengiriman));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);

        totalbayar = subtotalHarga + subPengiriman;
        Intent intent3 = new Intent("custom-total");
        intent3.putExtra("totalbayar", String.valueOf(totalbayar));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent3);



//        Log.d("myongkir", String.valueOf(myArrayOngkir));
//        if ()

//        Toast.makeText(context, ""+valP, Toast.LENGTH_SHORT).show();
//
//        Log.d("getval", String.valueOf(valP));

        String konval = String.valueOf(valP);
        if (konval.equals("0")){ // lengkapi pengiriman produk anda
//            Toast.makeText(context, "sama", Toast.LENGTH_SHORT).show();
//            Log.d("validasi1",konval);
            Intent intent4 = new Intent("custom-validasiopsi");
            intent4.putExtra("validasiopsi", String.valueOf("Rp0"));
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent4);

        }else if(!konval.equals("0")){
            Intent intent4 = new Intent("custom-validasiopsi");
            intent4.putExtra("validasiopsi", String.valueOf("Oke"));
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent4);
//            Toast.makeText(context, "tidak sama", Toast.LENGTH_SHORT).show();
//            Log.d("validasi2",konval);
        }


    }



}
