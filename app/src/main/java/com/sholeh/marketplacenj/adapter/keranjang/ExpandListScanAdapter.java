package com.sholeh.marketplacenj.adapter.keranjang;

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
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.respon.ResUbahJumlahProduk;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
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

public class ExpandListScanAdapter extends BaseExpandableListAdapter {
    int jumlahProduk = 0;
    double totalHarga = 0;
    private Context context;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    int hargaProduk, stokProduk;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private static final String TAG = "MyExpandAdapter";
    String CUSTOM_ACTION = "com.example.YOUR_ACTION";


    public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
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
        final HeaderModel model = (HeaderModel) getGroup(groupPosition);
        final HeaderModel headerModel = listHeaderFilter.get(groupPosition);
        final List<ChildModel> childModel = listChild.get(listHeaderFilter.get(groupPosition));


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_parent, null);
        }


        final TextView nama_kk, no_pelanggan;
        final CheckBox cbparent;

        nama_kk = convertView.findViewById(R.id.txNamaToko);
        no_pelanggan = convertView.findViewById(R.id.tvxIdToko);
        cbparent = convertView.findViewById(R.id.cb_select_parent);

        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());

        cbparent.setChecked(headerModel.isChecked());
        final boolean nowBeanChecked = headerModel.isChecked();

        cbparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);
                valueCheckboxParent();

            }
        });

        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d("sholeh", listHeaderFilter.get(groupPosition).getId_toko());

        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_child, null);
        }
        LinearLayout viewline, increment, decrement;
        final TextView tvx_nama, tvx_idKeranjang, tvx_addjumlah, tvx_stok, tvx_harga, tvx_hargaDiskon;
        final ImageView delete_item, img_gambar;
        final CheckBox cbchild;
            final ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);

        tvx_nama = convertView.findViewById(R.id.txtnamaPRODUK);
        tvx_idKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        tvx_harga = convertView.findViewById(R.id.txtharga);
        tvx_hargaDiskon = convertView.findViewById(R.id.txthargaDiskon);
        img_gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        tvx_stok = convertView.findViewById(R.id.txtstock);
        tvx_addjumlah = convertView.findViewById(R.id.txt_addjumlah);
        increment = convertView.findViewById(R.id.increment);
        decrement = convertView.findViewById(R.id.decrement);
        delete_item = convertView.findViewById(R.id.cart_delete);
        cbchild = convertView.findViewById(R.id.cb_select_child);


        hargaProduk = childModel.getHarga();
        stokProduk = childModel.getStok();
        jumlahProduk = childModel.getJumlah();


        vdiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(childModel.getDiskon()))));
        tvx_stok.setText(String.valueOf(stokProduk));
        tvx_nama.setText(childModel.getNama_produk());
        tvx_idKeranjang.setText(childModel.getId_keranjang());
        tvx_addjumlah.setText(String.valueOf(jumlahProduk));

        int hitungJumHarga = jumlahProduk * hargaProduk;
        st = new StringTokenizer(formatRupiah.format(hitungJumHarga), ",");
        String hargajum = st.nextToken().trim();
        tvx_harga.setText(hargajum);



        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + childModel.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(img_gambar);


        if (childModel.getDiskon() == 0) {
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
        }

        cbchild.setChecked(childModel.isChecked());
        cbchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean nowBeanChecked = childModel.isChecked();
                childModel.setIsChecked(!nowBeanChecked);


                boolean parentIsChecked = dealOneParentAllChildIsChecked(groupPosition);
                HeaderModel headerModel = listHeaderFilter.get(groupPosition);
                headerModel.setIsChecked(parentIsChecked); // koding agar checkbox alsemua tidak terchecked ketika chil tidak terpenuhi
                notifyDataSetChanged();
                valueCheckboxParent();
                getTotal();
            }
        });

        cbchild.setChecked(childModel.isChecked());
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJumlah(childModel);
            }
        });
        cbchild.setChecked(childModel.isChecked());
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kurangiJumlah(childModel);
            }
        });

        delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_keranjang = childModel.getId_keranjang();
                APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                Call<ResHapusKeranjang> call = service.hapusProdukKeranjang(id_keranjang);
                call.enqueue(new Callback<ResHapusKeranjang>() {
                    @Override
                    public void onResponse(Call<ResHapusKeranjang> call, Response<ResHapusKeranjang> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (response.body().getPesan().equalsIgnoreCase("sukses")) {
                                AppUtilits.displayMessage(context, "Sukses hapus produk dari keranjang");

                                ((KeranjangDetailActivity) context).getDetailKeranjang();

                            } else {
                                AppUtilits.displayMessage(context, "Gagal hapus produk dari keranjang");
                            }
                        } else {
//                            AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                        }

                    }

                    @Override
                    public void onFailure(Call<ResHapusKeranjang> call, Throwable t) {
                        AppUtilits.displayMessage(context, context.getString(R.string.failed_request));

                    }
                });
//        }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




    public void addJumlah(ChildModel childModel) {
        int jumlahProduk = childModel.getJumlah();
        int stokProduk = childModel.getStok();
        String idKeranjang = childModel.getId_keranjang();
        if (jumlahProduk == stokProduk) {
            Toast.makeText(context, "Stok Barang Hanya Tersedia " + stokProduk, Toast.LENGTH_SHORT).show();
            return;
        }
        jumlahProduk++;
        childModel.setJumlah(jumlahProduk);
        notifyDataSetChanged();
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang(id_konsumen);
        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, Response<ResDetailKeranjang> response) {
                if (response.body() != null && response.isSuccessful()) {
                    getTotal();

                } else {
                    Toast.makeText(context, "gagal " + response.body(), Toast.LENGTH_SHORT).show();
//                                AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                }
            }

            @Override
            public void onFailure(Call<ResDetailKeranjang> call, Throwable t) {
                Toast.makeText(context, "gagal " + t, Toast.LENGTH_SHORT).show();
//                            Log.e(TAG, " edit fail "+ t.toString());
//                            AppUtilits.displayMessage(mContext,  mContext.getString(R.string.fail_toeditcart));
            }
        });
    }

    public void kurangiJumlah(ChildModel childModel) {
        int jumlahProduk = childModel.getJumlah();
        int stokProduk = childModel.getStok();
        String idKeranjang = childModel.getId_keranjang();
        if (jumlahProduk == 1) {
            Toast.makeText(context, "Stok Barang Tersedia " + stokProduk, Toast.LENGTH_SHORT).show();
            return;
        }
        jumlahProduk--;
        childModel.setJumlah(jumlahProduk);
        notifyDataSetChanged();

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResUbahJumlahProduk> call = service.updateJumlah(idKeranjang, String.valueOf(jumlahProduk));
        call.enqueue(new Callback<ResUbahJumlahProduk>() {
            @Override
            public void onResponse(Call<ResUbahJumlahProduk> call, Response<ResUbahJumlahProduk> response) {
                if (response.body() != null && response.isSuccessful()) {
                    getTotal();

//
                } else {
                    Toast.makeText(context, "gagal " + response.body(), Toast.LENGTH_SHORT).show();
//                                AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                }
            }


            @Override
            public void onFailure(Call<ResUbahJumlahProduk> call, Throwable t) {
                Toast.makeText(context, "gagal " + t, Toast.LENGTH_SHORT).show();
//                            Log.e(TAG, " edit fail "+ t.toString());
//                            AppUtilits.displayMessage(mContext,  mContext.getString(R.string.fail_toeditcart));
            }
        });
    }

    private void setupOneParentAllChildChecked(boolean isChecked, int groupPosition) { //b
        HeaderModel headerModel = listHeaderFilter.get(groupPosition);
        headerModel.setIsChecked(isChecked);
        for (int i = 0; i < listChild.get(listHeaderFilter.get(groupPosition)).size(); i++) {
            ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(i);
            childModel.setIsChecked(isChecked);
        }
        notifyDataSetChanged();
        getTotal();
    }


    public void setupAllChecked(boolean isChecked) {
        for (int i = 0; i < listHeaderFilter.size(); i++) {
            HeaderModel headerModel = listHeaderFilter.get(i);
            headerModel.setIsChecked(isChecked);
            List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));
            for (int j = 0; j < childMapList.size(); j++) {
                ChildModel childModel = childMapList.get(j);
                childModel.setIsChecked(isChecked);
            }
        }
        notifyDataSetChanged();
    }

    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
        for (int j = 0; j < listChild.get(listHeaderFilter.get(groupPosition)).size(); j++) {
            ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(j);
            if (!childModel.isChecked()) { // jika sudah tidak di pilih
                return false;
            }
        }
        return true;
    }

    public boolean AllIsChecked() {
        for (int i = 0; i < listHeaderFilter.size(); i++) {
            HeaderModel headerModel = listHeaderFilter.get(i);
            if (!headerModel.isChecked()) {
                return false;
            }
        }
        return true;
    }

    public void valueCheckboxParent() {
        String data = String.valueOf(AllIsChecked());
        Intent intent = new Intent("custom-cball");
        intent.putExtra("valParent", data);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


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
            List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));

            for (int j = 0; j < childMapList.size(); j++) {
                ChildModel childModel = childMapList.get(j);
                int jumlah = childMapList.get(j).getJumlah();
                double Harga = childMapList.get(j).getHarga();
                double diskonHarga = childModel.getDiskon();
                double h = diskonHarga / 100 * Harga;
                double p = Harga - h;
                getid = childModel.getId_keranjang();
                if (childModel.isChecked()) {
                    jumlahProduk++;
                    totalHarga += p * jumlah;


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
        }
        Intent intent = new Intent("custom-message");
        intent.putExtra("total", String.valueOf(totalHarga));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

//        Toast.makeText(context, "m"+myArray, Toast.LENGTH_SHORT).show();
//        Log.d("array", String.valueOf(myArray));

        Intent i = new Intent("custom-idk");

//        i.putExtra("idkeranjang",String.valueOf(myArray)) ;
        i.putExtra("idkeranjang",String.valueOf(myArray)) ;
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
//        Toast.makeText(context, ""+String.valueOf(myArray), Toast.LENGTH_SHORT).show();




    }
}