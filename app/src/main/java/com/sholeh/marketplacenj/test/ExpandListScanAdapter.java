package com.sholeh.marketplacenj.test;

import android.content.Context;
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

import java.util.HashMap;
import java.util.List;

public class ExpandListScanAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<HeaderModel> listHeader;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;


    private boolean buka = true;
    private static int currentPosition = 0;

    public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
        this.context = context;
        this.listHeader = listHeader;
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
        return this.listHeader.get(groupPosition);
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
//


        // set on click change
        HeaderModel model = (HeaderModel) getGroup(groupPosition);
        if (convertView == null) {
            Toast.makeText(context, "" + groupPosition, Toast.LENGTH_SHORT).show();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.desain_parent, null);
        }
        TextView nama_kk = convertView.findViewById(R.id.txNamaToko);
        TextView no_pelanggan = convertView.findViewById(R.id.tvxIdToko);
//        ImageView img = convertView.findViewById(R.id.imgExpan);
        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());

        ImageView img = convertView.findViewById(R.id.imgpanah);

//        if (isExpanded){
//            Toast.makeText(context, ""+isExpanded, Toast.LENGTH_SHORT).show();
//            img.setImageResource(R.drawable.ic_keyboard_arrow_up_grey_24dp);
//        } else {
//            img.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_24dp);
//            Toast.makeText(context, ""+isExpanded, Toast.LENGTH_SHORT).show();
//        }
        if (currentPosition != groupPosition) {
            Toast.makeText(context, "" + groupPosition, Toast.LENGTH_SHORT).show();


        }

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildModel model = (ChildModel) getChild(groupPosition, childPosition);

        LayoutInflater inflater2 = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater2.inflate(R.layout.desain_child, null);
        LinearLayout increment, decrement;

        increment = convertView.findViewById(R.id.increment);
        decrement = convertView.findViewById(R.id.decrement);
        TextView nama = convertView.findViewById(R.id.txtnamaPRODUK);
        ImageView gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        final TextView jumlah = convertView.findViewById(R.id.txtjumlah);
        final TextView total = convertView.findViewById(R.id.txttotal);
        final int harga = Integer.parseInt(model.getHarga());
        int jumlahbarang = Integer.parseInt(model.getJumlah());

        nama.setText(model.getNama_produk());
        total.setText(String.valueOf(harga * jumlahbarang));
        jumlah.setText(model.getJumlah());
        Glide.with(convertView.getContext())
                .load(model.getGambar())
                .apply(new RequestOptions().override(350, 550))
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.ic_missing)
                .into(gambar);


        /*increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(String.valueOf(jumlah.getText()));
                count++;
                total.setText(String.valueOf(count));
            }

        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(String.valueOf(jumlah.getText()));
                if (count > 1)
                    count--;
               total.setText(String.valueOf(count));


            }
        });
*/

        return convertView;
    }

    /* @SuppressLint("SetTextI18n")
     public void decrement(View view) {

         TextView harga = view.findViewById(R.id.tv_hargakeranjang);
         harga = Integer.parseInt(getIntent().getStringExtra("harga_jual"));


         if (quantity > 1) {
             quantity--;
             quantityProductPage.setText(String.valueOf(quantity));

             harga.setText(String.valueOf(vhargaproduk * quantity));

         }
     }

     public void increment(View view) {
         TextView harga = findViewById(R.id.tv_hargakeranjang);
         vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));

         if (quantity < 500) {
             quantity++;
             quantityProductPage.setText(String.valueOf(quantity));
             harga.setText(String.valueOf(vhargaproduk * quantity));

         } else {
             Toast.makeText(this, "Product Count Must be less than 500", Toast.LENGTH_SHORT).show();
         }
     }*/
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
