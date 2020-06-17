package com.sholeh.marketplacenj.adapter.checkout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.model.checkout.ChildCheckout;
import com.sholeh.marketplacenj.model.checkout.HeaderCheckout;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.respon.ResUbahJumlahProduk;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpandAdapterCheckout extends BaseExpandableListAdapter {
    int jumlahProduk = 0;
    double totalHarga = 0;
    private Context context;
    private List<HeaderCheckout> listHeaderFilter;
    private HashMap<HeaderCheckout, List<ChildCheckout>> listChild;
    int hargaProduk, stokProduk;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private static final String TAG = "MyExpandAdapter";
    String CUSTOM_ACTION = "com.example.YOUR_ACTION";


    public ExpandAdapterCheckout(Context context, List<HeaderCheckout> listHeader, HashMap<HeaderCheckout, List<ChildCheckout>> listChild) {
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
        this.context = context;
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
        final List<ChildCheckout> childModel = listChild.get(listHeaderFilter.get(groupPosition));


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_parent_checkout, null);
        }


        final TextView nama_kk, no_pelanggan;

        nama_kk = convertView.findViewById(R.id.txtNamaToko);
        no_pelanggan = convertView.findViewById(R.id.txtIdToko);

        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());



        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d("sholeh", listHeaderFilter.get(groupPosition).getId_toko());

        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_child_checkout, null);
        }

        final TextView tvx_nama, tvx_idKeranjang, tvx_jumlahproduk,  tvx_harga, tvx_totharga, tvx_hargaDiskon;
        final ImageView img_gambar;
            final ChildCheckout childCheckout = listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);

        tvx_nama = convertView.findViewById(R.id.txtnamaPRODUK);
        tvx_idKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        tvx_harga = convertView.findViewById(R.id.tvx_harga);
        tvx_totharga = convertView.findViewById(R.id.tvx_hargajum);
        tvx_hargaDiskon = convertView.findViewById(R.id.tvxHrgaDiskon);
        tvx_jumlahproduk = convertView.findViewById(R.id.tvxJumlahProduk);
        img_gambar = convertView.findViewById(R.id.img_gambarkeranjang);


        hargaProduk = childCheckout.getHarga();
        jumlahProduk = childCheckout.getJumlah();



        vdiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(childCheckout.getDiskon()))));
        tvx_nama.setText(childCheckout.getNama_produk());
        tvx_idKeranjang.setText(childCheckout.getId_keranjang());
        tvx_jumlahproduk.setText(String.valueOf(jumlahProduk));

        int hitungJumHarga = jumlahProduk * hargaProduk;
        st = new StringTokenizer(formatRupiah.format(hitungJumHarga), ",");
        String hargajum = st.nextToken().trim();
        tvx_harga.setText(hargajum);
        tvx_totharga.setText(hargajum);



        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + childCheckout.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(img_gambar);


        if (childCheckout.getDiskon() == 0) {
            tvx_hargaDiskon.setVisibility(View.GONE);
        } else {
            double h = vdiskon / 100 * hargaProduk;
            double p = hargaProduk - h;
            double hitung = jumlahProduk * p;
            st = new StringTokenizer(formatRupiah.format(hitung), ",");
            String harganya = st.nextToken().trim();
            tvx_hargaDiskon.setPaintFlags(tvx_harga.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvx_hargaDiskon.setTextColor(context.getResources().getColor(R.color.redTransparent));
            tvx_hargaDiskon.setTypeface(tvx_harga.getTypeface(), Typeface.NORMAL);
            tvx_hargaDiskon.setVisibility(View.VISIBLE);
            tvx_hargaDiskon.setText(hargajum);
            tvx_harga.setText(harganya);
            tvx_totharga.setText(harganya);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void getTotal() {
        jumlahProduk = 0; //totalCount
        totalHarga = 0;
        String idK = null;
//        List<String> list;
        Bundle extras = new Bundle();
        ArrayList<String> myArray = new ArrayList<>();
        String sum = null;
        String  getid = null;
        String[] words = new String[0];

        for (int i = 0; i < listHeaderFilter.size(); i++) {
            List<ChildCheckout> childMapList = listChild.get(listHeaderFilter.get(i));

            for (int j = 0; j < childMapList.size(); j++) {
                ChildCheckout childModel = childMapList.get(j);
                int jumlah = childMapList.get(j).getJumlah();
                double Harga = childMapList.get(j).getHarga();
                double diskonHarga = childModel.getDiskon();
                double h = diskonHarga / 100 * Harga;
                double p = Harga - h;
                getid = childModel.getId_keranjang();



//                    list = new ArrayList<String>();
//                    list.add(getid);
                    myArray.add(String.valueOf(getid));

//                    String line = getid+" ";
//                    //using String split function
//                    words = line.split(" ");
//                    System.out.println(Arrays.toString(words));
//                    //using java.util.regex Pattern
//                    Pattern pattern = Pattern.compile(" ");
//                    words = pattern.split(line);
//                    Toast.makeText(context, ""+Arrays.toString(words), Toast.LENGTH_SHORT).show();



//                    Log.d("array", String.valueOf(myArray));
//                    Toast.makeText(context, ""+list, Toast.LENGTH_SHORT).show();



//                    int []id_keranjang = {Integer.parseInt(getid)};
//                    Toast.makeText(context, "g"+id_keranjang.length, Toast.LENGTH_SHORT).show();
//


//                    for (int a =0; a < myArray.size(); a++){
////                        idK = id_keranjang[a];
//                        sum = String.valueOf(myArray.get(i));
////                        Toast.makeText(context, ""+sum, Toast.LENGTH_SHORT).show();
//
//                    }

//                    Toast.makeText(context, "f"+getid, Toast.LENGTH_SHORT).show();
//                    Log.d("idkeranjang", idK);
//                    Intent intent = new Intent("custom-idk");
//                    intent.putExtra("array-idkeranjang", String.valueOf(idK));


//
//                    Toast.makeText(context, "s"+sum, Toast.LENGTH_SHORT).show();
//
//                    Intent in = new Intent("custom-idk");
////                    intent.putExtra("idkeranjang",String.valueOf(sum+",")) ;
//                    in.putExtra("idkeranjang",String.valueOf(getid)) ;
//                    LocalBroadcastManager.getInstance(context).sendBroadcast(in);

//                    Toast.makeText(context, ""+myArray.size(), Toast.LENGTH_SHORT).show();
//                    Log.d("array", String.valueOf(sum));









            }
        }
//        Intent intent = new Intent("custom-message");
//        intent.putExtra("total", String.valueOf(totalHarga));
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
////        Toast.makeText(context, "m"+myArray, Toast.LENGTH_SHORT).show();
////        Log.d("array", String.valueOf(myArray));
//
//        Intent i = new Intent("custom-idk");
//
////        i.putExtra("idkeranjang",String.valueOf(myArray)) ;
//        i.putExtra("idkeranjang",String.valueOf(myArray)) ;
//        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
////        Toast.makeText(context, ""+String.valueOf(myArray), Toast.LENGTH_SHORT).show();
//
//


    }
}