package com.sholeh.marketplacenj.adapter.keranjang;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
import com.sholeh.marketplacenj.util.CONSTANTS;

import java.util.HashMap;
import java.util.List;

public class ExpandListScanAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    private TextWatcher watcher;


//    private boolean buka = true;
//    private static int currentPosition = 0;

    public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
        this.context = context;
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return this.listHeaderFilter.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChild.get(this.listHeaderFilter.get(groupPosition)).size();
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // set on click change
        HeaderModel model = (HeaderModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.desain_parent, null);
        }
        TextView nama_kk = convertView.findViewById(R.id.txNamaToko);
        TextView no_pelanggan = convertView.findViewById(R.id.tvxIdToko);

//        ImageView img = convertView.findViewById(R.id.imgExpan);
        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());


        ImageView img = convertView.findViewById(R.id.imgpanah);

        if (isExpanded) {
            img.setImageResource(R.drawable.ic_keyboard_arrow_up_grey_24dp);
        } else {
            img.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_24dp);
            // Toast.makeText(context, ""+isExpanded, Toast.LENGTH_SHORT).show();
        }


        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildModel model = (ChildModel) getChild(groupPosition, childPosition);

        LayoutInflater inflater2 = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater2.inflate(R.layout.desain_child, null);

        LinearLayout viewline, increment, decrement;

        final TextView addjumlah, stok, harga;
        final int hargaproduk;


        TextView nama = convertView.findViewById(R.id.txtnamaPRODUK);
        harga = convertView.findViewById(R.id.txtharga);
        ImageView gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        stok = convertView.findViewById(R.id.txtstock);
        addjumlah = convertView.findViewById(R.id.txt_addjumlah);
        increment = convertView.findViewById(R.id.increment);
        decrement = convertView.findViewById(R.id.decrement);

        nama.setText(model.getNama_produk());
        harga.setText("Rp " + model.getHarga());
        addjumlah.setText(model.getJumlah());
        stok.setText(model.getStock());
        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + model.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(gambar);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(addjumlah.getText().toString());
                int stokproduk = Integer.valueOf(stok.getText().toString());
//                int hargatotalproduk = Integer.valueOf(harga.getText().toString());

                if (count < stokproduk) {
                    count++;
                    addjumlah.setText("" + count);
//                    harga.setText(String.valueOf(hargatotalproduk *count));
                } else if (count == stokproduk) {
                    Toast.makeText(context, "Stok Barang Hanya Tersedia " + stokproduk, Toast.LENGTH_SHORT).show();
                }
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(addjumlah.getText().toString());
//                int hargatotalproduk = Integer.valueOf(harga.getText().toString());
                if (count > 1) {
                    count--;
                    addjumlah.setText("" + count);
//                    harga.setText(String.valueOf(hargatotalproduk *count));
                }
            }
        });


        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

