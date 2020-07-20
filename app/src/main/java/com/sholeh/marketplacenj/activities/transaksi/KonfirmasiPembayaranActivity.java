package com.sholeh.marketplacenj.activities.transaksi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AkunActivity;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.adapter.AlamatAdapter;
import com.sholeh.marketplacenj.adapter.bank.adapterspin;
import com.sholeh.marketplacenj.adapter.transaksi.AdapterBank;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.model.transaksi.ModelBank;
import com.sholeh.marketplacenj.respon.ResBank;
import com.sholeh.marketplacenj.respon.ResKonfirmasi;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResRekAdmin;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class KonfirmasiPembayaranActivity extends AppCompatActivity implements View.OnClickListener {

    String total, namaPengirim;
    TextView toolbar, tvxtotalbayar, tvxNorek, tvxAn, tvnorek, tvnamarek, tvidRekAdmin, tvKonfirmasi, edKodeTransaksi,  edNamaPengirim, edTotalbayar, tvxWaktuTransaksi, tvxBatasBayar;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, stsub, sttotal;
    int id_transaksi, kodetransaksi;
    String idBank = null;
    String nilaiIntent;

    ImageView imgBuktiTf, imgbackKeranjang;
    Button btnChooseImg;

    Spinner spin_rek;

    ArrayList<String> arrayRek = new ArrayList<>();
    ArrayList<String> listID_rek = new ArrayList<>();
    adapterspin adapterspinner;
    Preferences preferences;
    String id_konsumen;
    String idrekAdmin, nomorRek, anRek, total_bayar, tgl_pemesanan, batas_pembayaran;
    LinearLayout lnrek;
    private List<ResBank> listResBank;

    Bitmap bitmap = null;
    Uri imageUri;
    String imagePath;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    private static final int PERMISSION_STORAGE = 2;
    double totalbayar;
    Toolbar toolBarisi;
    private KProgressHUD progressHUD;

    RadioButton button1,button2;
    LinearLayout radio1,radio2;

    private ArrayList<ModelBank> modellist = new ArrayList<>();
    private RecyclerView recyclerbank;
    private AdapterBank adapterBank;

    String paymentType[]={"Credit / Debit Card","Cash On Delivery","PAYTM","Google Wallet"};
    Integer logoImage[]={R.drawable.credit, R.drawable.cash, R.drawable.paytm, R.drawable.googlewallet};

    private ResProfil tvDataProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);
        progressHUD = KProgressHUD.create(this);
        toolbar = findViewById(R.id.txt_toolbarKeranjang);
        toolbar.setText("Konfirmasi Pembayaran");
        spin_rek = findViewById(R.id.spin_rek);
        imgbackKeranjang = findViewById(R.id.imgBackKeranjang);
        perizinan();
        getDataPref();
        getDataProfil();



        tvxtotalbayar = findViewById(R.id.tv_totalbayar);
        tvxNorek = findViewById(R.id.tvxNomoRek);
        tvxAn = findViewById(R.id.tvxAnRek);
        tvnorek = findViewById(R.id.tvrek);
        tvnamarek = findViewById(R.id.tvnamarek);
        tvidRekAdmin = findViewById(R.id.idRekAdmin);
        edKodeTransaksi = findViewById(R.id.ed_kodetransaksi);
        edNamaPengirim = findViewById(R.id.namapengirim);
        edTotalbayar = findViewById(R.id.ed_totalbayar);
        imgBuktiTf = findViewById(R.id.imgBukti);
        btnChooseImg = findViewById(R.id.btnChooseImg);
        tvKonfirmasi = findViewById(R.id.tvxKonfirmasi);
        tvxWaktuTransaksi = findViewById(R.id.tvx_waktuTransaksi);
        tvxBatasBayar = findViewById(R.id.tvx_batasPembayaran);
        lnrek = findViewById(R.id.lnrek);
        lnrek.setVisibility(View.GONE);

        Bundle b = getIntent().getExtras();
        totalbayar = b.getDouble("totalbayar");
        id_transaksi = b.getInt("id_transaksi");
        kodetransaksi = b.getInt("kodetransaksi");
//        total_bayar = b.getString("total");
        tgl_pemesanan = b.getString("tanggal_pemesanan");
        batas_pembayaran = b.getString("batas_pembayaran");
        nilaiIntent = b.getString("icheckout");

        sttotal = new StringTokenizer(formatRupiah.format(totalbayar), ",");
        String harganya = sttotal.nextToken().trim();
        edTotalbayar.setText(harganya);
        edKodeTransaksi.setTypeface(null, Typeface.BOLD);
        edKodeTransaksi.setText("KODE TRANSAKSI : "+kodetransaksi);

        tvxWaktuTransaksi.setText(String.valueOf(tgl_pemesanan));
        tvxBatasBayar.setText(String.valueOf(batas_pembayaran));

        btnChooseImg.setOnClickListener(this);
        imgbackKeranjang.setOnClickListener(this);
        tvKonfirmasi.setOnClickListener(this);

        tampilBank();
        spin_rek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin_rek.getSelectedItem().equals("Pilih Rekening")) {


                } else {
//                    idrek = listID_rek.get(spin_rek.getSelectedItemPosition());
                    getBank(listID_rek.get(position));


//                    final String Rek = spin_rek.getSelectedItem().toString();
//                    Toast.makeText(MetodePembayaranActivity.this, ""+idrek, Toast.LENGTH_SHORT).show();

                    //                    getRek(listID_rek.get(position));
//                    namaProvinsi = spinProvinsi.getSelectedItem().toString();


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        recyclerbank= (RecyclerView)findViewById(R.id.recyclerbank);
        LinearLayoutManager mLayoutManger3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerbank.setLayoutManager(mLayoutManger3);
        recyclerbank.setItemAnimator(new DefaultItemAnimator());
        adapterBank = new AdapterBank(KonfirmasiPembayaranActivity.this, modellist);
        recyclerbank.setAdapter(adapterBank);
        tampilBankRecycler();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-pesan"));


//        navigationModelClasses2 = new ArrayList<>();
//        for (int i = 0; i < paymentType.length; i++) {
//           ModelBank beanClassForRecyclerView_contacts = new ModelBank(paymentType[i],logoImage[i]);
//
//            navigationModelClasses2.add(beanClassForRecyclerView_contacts);
//        }
//        cAdapter = new AdapterBank(KonfirmasiPembayaranActivity.this,navigationModelClasses2);
//        RecyclerView.LayoutManager cLayoutManager = new LinearLayoutManager(KonfirmasiPembayaranActivity.this);
//        recyclerView2.setLayoutManager(cLayoutManager);
//        recyclerView2.setItemAnimator(new DefaultItemAnimator());
//        recyclerView2.setAdapter(cAdapter);

    }

    private void ProgresDialog() {
        progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);

        progressHUD.show();
    }


    @Override
    public void onBackPressed() {
        onBack();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseImg:
                selectImage();
                break;

            case R.id.tvxKonfirmasi:
                simpanKonfirmasi();
//                Toast.makeText(this, "kkkkk", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgBackKeranjang:
                onBack();
                break;

            default:
                break;
        }

    }

    public void getDataProfil(){

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);

        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {

                tvDataProfil = response.body();
                String namaLengkap = tvDataProfil.getData().getNamaLengkap();
                edNamaPengirim.setText(String.valueOf(namaLengkap));
//                String noHP = tvDataProfil.getData().getNomorHp();
//                String email = tvDataProfil.getData().getEmail();
//                ed_username.setText(userName);
//                ed_nama.setText(namaLengkap);
//                ed_nohp.setText(noHP);
//                ed_email.setText(email);
//                Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/"+tvDataProfil.getData().getFotoProfil()).into(imgAkun);


            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
//                Toast.makeText(AkunActivity.this, "no connection"+t, Toast.LENGTH_LONG).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }

    public void getDataPref() {
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
//        namaPengirim = preferences.getNamaLengkap();

    }

    public void onBack() {
        new androidx.appcompat.app.AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Apakah Anda yakin ingin membatalkan pesanan ?")
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        batalPesanan();
//                        Toast.makeText(KonfirmasiPembayaranActivity.this, "Segera Selesaikan Api Batal Pesanan !!!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(KonfirmasiPembayaranActivity.this, KeranjangDetailActivity.class);
//                        startActivity(intent);
//                        finish();
//                        batalPesanan();
                    }
                }).setNegativeButton("Tidak", null).show();
    }


    private void perizinan() {
        ActivityCompat.requestPermissions(KonfirmasiPembayaranActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                99);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 99: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    perizinan();
                }
                return;
            }
        }
    }


    private void selectImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            final CharSequence[] options = {"Ambil Foto", "Gallery", "Kembali"};
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    KonfirmasiPembayaranActivity.this);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Ambil Foto")) {
                        String fileName = "new-photo-name.jpg";
                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, fileName);
                        values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        startActivityForResult(intent, PICK_Camera_IMAGE);
                    } else if (options[item].equals("Gallery")) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, PICK_IMAGE);
                    } else if (options[item].equals("Kembali")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }
    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = null;
        String filePath = null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImageUri = data.getData();
                    imagePath = getPath(selectedImageUri);
                }
                break;
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {
                    selectedImageUri = imageUri;
                    imagePath = getPath(selectedImageUri);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Foto Tidak Di Ambil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Foto Tidak Di Ambil", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        if (selectedImageUri != null) {
            try {
                String filemanagerstring = selectedImageUri.getPath();
                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast.makeText(KonfirmasiPembayaranActivity.this, "Unknown path",
                            Toast.LENGTH_LONG).show();
                    Log.e("Bitmap", "Unknown path");
                }

                if (filePath != null) {
                    decodeFile(filePath);
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast.makeText(KonfirmasiPembayaranActivity.this, "Internal error",
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }
        }

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public void decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);
        final int REQUIRED_SIZE = 1024;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);
        imgBuktiTf.setImageBitmap(bitmap);
        imgBuktiTf.setVisibility(View.VISIBLE);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void tampilBankRecycler() {
        if (!NetworkUtility.isNetworkConnected(KonfirmasiPembayaranActivity.this)) {
            AppUtilits.displayMessage(KonfirmasiPembayaranActivity.this, getString(R.string.network_not_connected));
        } else {
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResBank> call = service.getBank();
            call.enqueue(new Callback<ResBank>() {
                @Override
                public void onResponse(Call<ResBank> call, Response<ResBank> response) {

                    if (response.body() != null && response.isSuccessful()) {

//                        if (response.body().getPesan().equalsIgnoreCase("Sukses!!")) {


                            Log.d("cekbank", String.valueOf(response.body().getData().size()));

                            if (response.body().getData().size() > 0) {
                                modellist.clear();
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    modellist.add(new ModelBank(response.body().getData().get(i).getIdBank(),
                                            response.body().getData().get(i).getNamaBank(),
                                            response.body().getData().get(i).getRekening(),
                                            response.body().getData().get(i).getAtasNama(),
                                            response.body().getData().get(i).getFotoBank()));


                                }

                                adapterBank.notifyDataSetChanged();
                                recyclerbank.setVisibility(View.VISIBLE);
                                progressHUD.dismiss();
                                idBank = String.valueOf(response.body().getData().get(0).getIdBank());
                                getBank(idBank);


                            } else {
                                Toast.makeText(KonfirmasiPembayaranActivity.this, "Terdapat Kesalahan Bank, Silahkan Di Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                                recyclerbank.setVisibility(View.GONE);
                                progressHUD.dismiss();
                            }
//                        } else {
////                            AppUtilits.displayMessage(AlamatActivity.this, response.body().getPesan() );
//                            recyclerAlamat.setVisibility(View.GONE);
//                            ln_kosong.setVisibility(View.VISIBLE);
//                            progressDialogHud.dismiss();
//                        }
                    } else {
                        Toast.makeText(KonfirmasiPembayaranActivity.this, ""+getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        recyclerbank.setVisibility(View.GONE);
                        progressHUD.dismiss();

                    }

                }

                @Override
                public void onFailure(Call<ResBank> call, Throwable t) {
                    AppUtilits.displayMessage(KonfirmasiPembayaranActivity.this, getString(R.string.fail_togetbankadmin));
                    recyclerbank.setVisibility(View.GONE);
                    progressHUD.dismiss();


                }
            });
        }
    }

    public void tampilBank() {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResBank> call = service.getBank();
        call.enqueue(new Callback<ResBank>() {
            @Override
            public void onResponse(Call<ResBank> call, Response<ResBank> response) {


                if (response.isSuccessful()) {

                    int count_data = response.body().getData().size();

                    for (int a = 0; a <= count_data - 1; a++) {

                        arrayRek.add(response.body().getData().get(a).getNamaBank());
                        listID_rek.add(response.body().getData().get(a).getIdBank());


                    }
                    adapterspinner = new adapterspin(KonfirmasiPembayaranActivity.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayRek);
                    adapterspinner.add("Pilih Rekening");
                    spin_rek.setAdapter(adapterspinner);
                    spin_rek.setSelection(adapterspinner.getCount());
                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(MetodePembayaranActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResBank> call, Throwable t) {

//                Toast.makeText(MetodePembayaranActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getBank(String id_bank) {
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResRekAdmin> call = service.getDataBank(id_bank);
        call.enqueue(new Callback<ResRekAdmin>() {
            @Override
            public void onResponse(Call<ResRekAdmin> call, Response<ResRekAdmin> response) {


                Log.v("wow", "json : " + new Gson().toJson(response));

                if (response.isSuccessful()) {

                    int count_data = response.body().getData().size();

                    for (int a = 0; a <= count_data - 1; a++) {
                        idrekAdmin = response.body().getData().get(a).getIdRekeningAdmin();
                        nomorRek = response.body().getData().get(a).getNomorRekening();
                        anRek = response.body().getData().get(a).getAtasNama();

                    }
                    tvidRekAdmin.setText(String.valueOf(idrekAdmin));
                    tvxNorek.setText(String.valueOf(nomorRek));
                    tvxAn.setText(String.valueOf(anRek));
                    lnrek.setVisibility(View.VISIBLE);

                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(MetodePembayaranActivity.this, error, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResRekAdmin> call, Throwable t) {

//                Toast.makeText(MetodePembayaranActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void simpanKonfirmasi() {

        if (imagePath == null) {
            Toast.makeText(this, "Upload Foto Bukti Pembayaran Anda", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "Oke", Toast.LENGTH_SHORT).show();
            ProgresDialog();
            int bayar = (int) (totalbayar);
            namaPengirim = edNamaPengirim.getText().toString();
            File file = new File(imagePath);
            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part imageBody_ = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
            RequestBody ImageName = RequestBody.create(MediaType.parse("text/plain"), file.getName());
//        Toast.makeText(this, ""+kodetransaksi+" "+bayar+" "+idrekAdmin+" "+namaPengirim+" "+imagePath, Toast.LENGTH_SHORT).show();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            RequestBody kodetransaksi_ = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(kodetransaksi));
            RequestBody bayar_ = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(bayar));
            RequestBody idrekAdmin_ = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(idrekAdmin));
            RequestBody namaPengirim_ = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(namaPengirim));
            Call<ResKonfirmasi> call = service.simpanKonfirmasi(kodetransaksi_, bayar_, idrekAdmin_, namaPengirim_, imageBody_);
            call.enqueue(new Callback<ResKonfirmasi>() {
                @Override
                public void onResponse(Call<ResKonfirmasi> call, Response<ResKonfirmasi> response) {
                    Log.d("reskonfirmasi", String.valueOf(response));
//                Toast.makeText(KonfirmasiPembayaranActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    if (response.body() != null && response.isSuccessful()) {
                        Intent intent = new Intent(KonfirmasiPembayaranActivity.this, StatusPembayaran.class);
                        intent.putExtra("kodetransaksi", kodetransaksi);
                        intent.putExtra("namapengirim", namaPengirim);
                        intent.putExtra("waktutransaksi", tgl_pemesanan);
                        intent.putExtra("totalbayar", edTotalbayar.getText().toString());
                        startActivity(intent);
                        finish();
                        progressHUD.dismiss();
                    } else {
//                        AppUtilits.displayMessage(getApplication(), getString(R.string.network_error));
                        Log.d("reskonfirmasii", String.valueOf(response));
                        progressHUD.dismiss();
//                    Toast.makeText(KonfirmasiPembayaranActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResKonfirmasi> call, Throwable t) {
                    Log.e("reskonfirmasiii", t.toString());
                    AppUtilits.displayMessage(getApplication(), getString(R.string.network_error));
                    progressHUD.dismiss();
//                Toast.makeText(KonfirmasiPembayaranActivity.this, "res"+t, Toast.LENGTH_SHORT).show();
                }
            });
        }


//
    }


    public void batalPesanan() {
        if (!NetworkUtility.isNetworkConnected(KonfirmasiPembayaranActivity.this)) {
            AppUtilits.displayMessage(KonfirmasiPembayaranActivity.this, getString(R.string.network_not_connected));

        } else {
//        Toast.makeText(this, ""+id_transaksi, Toast.LENGTH_SHORT).show();
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<JsonObject> call = service.batalPesanan(String.valueOf(id_transaksi));
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d("gagalp", String.valueOf(response.body()));
//                Toast.makeText(KonfirmasiPembayaranActivity.this, "batalpp" + response.body() + "\n" + response.message(), Toast.LENGTH_SHORT).show();

//                if (response.isSuccessful()) {
                    if (response.body() != null && response.isSuccessful()) {
                        progressHUD.dismiss();

                        if (nilaiIntent.equalsIgnoreCase("activity")) {
                            Intent intent = new Intent(KonfirmasiPembayaranActivity.this, KeranjangDetailActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            finish();
                        }

                    }else{
                        progressHUD.dismiss();
                        AppUtilits.displayMessage(getApplication(), getString(R.string.network_error));
                    }

//

//                } else {
//                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(CheckoutActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//                }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("gagalpp", String.valueOf(t));
//                Toast.makeText(KonfirmasiPembayaranActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            idBank = intent.getStringExtra("idbank");
            getBank(idBank);
        }
    };

}
