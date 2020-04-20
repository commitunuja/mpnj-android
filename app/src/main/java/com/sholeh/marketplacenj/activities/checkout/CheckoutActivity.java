package com.sholeh.marketplacenj.activities.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AddAlamat;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.adapter.keranjang.ExpandListScanAdapter;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
import com.sholeh.marketplacenj.respon.DataKeranjang;
import com.sholeh.marketplacenj.respon.ItemKeranjang;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvxtolbar, tvxUbahAlamat, tvxSetAlamat;
    Preferences preferences;
    String id_konsumen;
    private List<HeaderModel> listHeader;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    List<ChildModel> child;
    int hargaJual;
    private ExpandableListView listView;
    private ExpandListScanAdapter expanAdapter;

    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        tvxtolbar = findViewById(R.id.txt_toolbarKeranjang);
        tvxUbahAlamat = findViewById(R.id.tvxubahAlamat);
        tvxSetAlamat = findViewById(R.id.tvx_setAlamat);
        tvxtolbar.setText("Checkout");
        listView = findViewById(R.id.expand_checkout);

        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();

        Intent i = getIntent();

        arrayList = (ArrayList<String>) i.getStringArrayListExtra("yolo");
        arrayList.add("11");
        arrayList.add("7");

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

        tvxUbahAlamat.setOnClickListener(this);
        getDetailKeranjang();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvxubahAlamat:
                startActivity(new Intent(this, AddAlamat.class));
            break;
            default:
                break;
        }
    }
}
