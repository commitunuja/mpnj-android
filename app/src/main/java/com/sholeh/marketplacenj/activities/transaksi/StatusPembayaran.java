package com.sholeh.marketplacenj.activities.transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.mfragment.homepage.HomepageFragment;
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.DetailPesanan;
import com.sholeh.marketplacenj.util.Preferences;

public class StatusPembayaran extends AppCompatActivity implements View.OnClickListener {

    private TextView tvxkode, tvxnama, tvxtgltransaksi, tvxtotal, tvx_btnSelesai;

    Preferences preferences;
    String id_konsumen,  namaPengirim, totalbayar;

    int id_transaksi, kodetransaksi;
    String tglpemesanan, batas_pembayaran ;


    final Fragment homeFragment = new HomepageFragment();
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pembayaran);
        getDataPref();

        tvxkode = findViewById(R.id.tvx_kodetransaksi);
        tvxnama = findViewById(R.id.tvx_namapengirim);
        tvxtgltransaksi = findViewById(R.id.tvx_waktutransaksi);
        tvxtotal = findViewById(R.id.tvxtotalbayar);
        tvx_btnSelesai = findViewById(R.id.btnselesai);
        tvx_btnSelesai.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        totalbayar = b.getString("totalbayar");
        id_transaksi = b.getInt("id_transaksi");
        kodetransaksi = b.getInt("kodetransaksi");
//        total_bayar = b.getString("total");
        tglpemesanan = b.getString("waktutransaksi");
        batas_pembayaran = b.getString("batas_pembayaran");

        tvxkode.setText(String.valueOf(kodetransaksi));
        tvxnama.setText(String.valueOf(namaPengirim));
        tvxtgltransaksi.setText(String.valueOf(tglpemesanan));
        tvxtotal.setText(String.valueOf(totalbayar));
    }
    public void getDataPref() {
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        namaPengirim = preferences.getNamaLengkap();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnselesai:
//                Intent intent = new Intent(StatusPembayaran.this, DetailPesanan.class);
//                intent.putExtra("kodetransaksi", kodetransaksi);
//                intent.putExtra("namapengirim", namaPengirim);
//                intent.putExtra("waktutransaksi", tgl_pemesanan);
//                intent.putExtra("totalbayar", edTotalbayar.getText().toString());
//                startActivity(intent);
                Toast.makeText(this, "Belum Selesai", Toast.LENGTH_SHORT).show();
                finish();
                break;


            default:
                break;
        }

    }
}