package com.sholeh.marketplacenj.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;
import com.sholeh.marketplacenj.R;
import com.squareup.picasso.Picasso;

import static java.lang.Integer.parseInt;

public class DiskripsiActivity extends AppCompatActivity implements View.OnClickListener {
    TextView NamaProduk, HargaProduk, offer;
    JustifiedTextView Diskripsi;
    String namaproduk, vdeskripsi, fotoproduk;
    ImageView foto, back;
    int vhargaproduk;
    Double vdiskon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diskripsi);


        NamaProduk = findViewById(R.id.tv_nama_produk_detail);
        HargaProduk = findViewById(R.id.tv_hargaproduk);
        Diskripsi = findViewById(R.id.txtdiskripsi_diskripsi);
        offer = findViewById(R.id.txtdiskondiskripsi);
        foto = findViewById(R.id.img_fotoproduk);
        back = findViewById(R.id.img_back);


        back.setOnClickListener(this);

        offer.setPaintFlags(offer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        namaproduk = getIntent().getExtras().getString("nama_produk");
        vhargaproduk = parseInt(getIntent().getStringExtra("harga_jual"));
        vdiskon = Double.valueOf((getIntent().getStringExtra("diskon")));
        vdeskripsi = getIntent().getExtras().getString("keterangan");
        fotoproduk = getIntent().getStringExtra("foto_produk");
        Diskripsi.setText(Html.fromHtml(vdeskripsi));
        NamaProduk.setText(namaproduk);

        Picasso.with(this)
                .load(fotoproduk)
                .resize(300, 300)
                .into(foto);

        double h = vdiskon / 100 * vhargaproduk;
        double p = vhargaproduk - h;
        String dStr = String.valueOf(p);
        String value = dStr.matches("\\d+\\.\\d*[1-9]\\d*") ? dStr : dStr.substring(0, dStr.indexOf("."));


        if (vdiskon == 0) {
            HargaProduk.setText("Rp " + vhargaproduk);
            offer.setText("");

        } else {
            offer.setText("Rp " + vhargaproduk);

            HargaProduk.setText("Rp " + value);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
