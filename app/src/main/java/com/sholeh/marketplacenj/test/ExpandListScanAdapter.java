package com.sholeh.marketplacenj.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptlmh.scannercontrol.Model.ChildModel;
import com.ptlmh.scannercontrol.Model.HeaderModel;
import com.ptlmh.scannercontrol.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class ExpandListScanAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<HeaderModel> listHeader;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;

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
        HeaderModel model = (HeaderModel) getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.desain_parent,null);
        }
        TextView nama_kk = convertView.findViewById(R.id.txNamaKKHeader);
        TextView no_pelanggan = convertView.findViewById(R.id.txNoRegHeader);
        ImageView img = convertView.findViewById(R.id.imgExpan);
        nama_kk.setText(model.getNama_pelanggan());
        no_pelanggan.setText(model.getNo_pelanggan());
        if (isExpanded){
            img.setImageResource(R.drawable.ic_keyboard_arrow_up_grey_24dp);
        } else {
            img.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_24dp);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildModel model = (ChildModel)getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.desain_child,null);
        }
        TextView tanggal = convertView.findViewById(R.id.txtglChild);
        TextView kwh = convertView.findViewById(R.id.txkwh_child);
        TextView status_lunas = convertView.findViewById(R.id.txstatus_child);
        ImageView img = convertView.findViewById(R.id.imgcheck_child);
        TextView bln = convertView.findViewById(R.id.txBulanDetail);
        TextView tagihan = convertView.findViewById(R.id.txharga_child);
        String bulan_kwh = null;
        try {
            Date tgl = new SimpleDateFormat("yyyy-MM-dd").parse(model.getTanggal());
            int a = tgl.getMonth();
            if (a == 1){ bulan_kwh = "Januari";
            } else if (a == 2){ bulan_kwh = "Februari";
            } else if (a == 3){ bulan_kwh = "Maret";
            } else if (a == 4){ bulan_kwh = "April";
            } else if (a == 5){ bulan_kwh = "Mei";
            } else if (a == 6){ bulan_kwh = "Juni";
            } else if (a == 7){ bulan_kwh = "Juli";
            } else if (a == 8){ bulan_kwh = "Agustus";
            } else if (a == 9){ bulan_kwh = "September";
            } else if (a == 10){ bulan_kwh = "Oktober";
            } else if (a == 11){ bulan_kwh = "November";
            } else{ bulan_kwh = "Desember"; }

            tanggal.setText(String.valueOf(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(tgl)));
            bln.setText(bulan_kwh);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (model.getStatus_bayar().equals("y")){
            status_lunas.setText("Lunas");
            status_lunas.setTextColor(context.getResources().getColor(R.color.colorgreen));
        } else {
            status_lunas.setText("Belum Lunas");
            status_lunas.setTextColor(context.getResources().getColor(R.color.colorOrange));
        }
        kwh.setText(model.getKwh_tercatat()+" KWh");

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        tagihan.setText(kursIndonesia.format(Double.parseDouble(model.getTagihan())));
        if (model.getSinkron().equals("1")){
            img.setImageResource(R.drawable.ic_check_green_24dp);
        } else {
            img.setImageResource(R.drawable.ic_check_orange_24dp);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String charString = constraint.toString();
//                if (charString.isEmpty()) {
//                    listHeaderFilter = listHeader;
//                } else {
//                    List<HeaderModel> filteredList = new ArrayList<>();
//                    for (HeaderModel row : listHeader) {
//                        if (row.getNama_pelanggan().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    listHeaderFilter = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = listHeaderFilter;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                listHeaderFilter = (ArrayList<HeaderModel>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
}
