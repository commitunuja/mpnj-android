package com.sholeh.marketplacenj.activities.transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.adapterspin;
import com.sholeh.marketplacenj.respon.ResBank;
import com.sholeh.marketplacenj.respon.ResKonfirmasi;
import com.sholeh.marketplacenj.respon.ResRekAdmin;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

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

import static java.lang.String.valueOf;

public class KonfirmasiPembayaranActivity extends AppCompatActivity implements View.OnClickListener {

    String total, namaPengirim;
    TextView tvxtotalbayar, tvxNorek, tvxAn, tvnorek, tvnamarek, tvidRekAdmin, tvxKonfirmasi;
    EditText edKodeTransaksi, edNamaPengirim, edTotalbayar;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, stsub, sttotal;
    int kodetransaksi;


    ImageView imgBuktiTf;
    Button btnChooseImg;

    Spinner spin_rek;

    ArrayList<String> arrayRek = new ArrayList<>();
    ArrayList<String> listID_rek = new ArrayList<>();
    adapterspin adapterspinner;
    Preferences preferences;
    String id_konsumen;
    String idrekAdmin, nomorRek, anRek;
    LinearLayout lnrek;
    private List<ResBank> listResBank;

    Bitmap bitmap = null;
    Uri imageUri;
    String imagePath;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    private static final int PERMISSION_STORAGE = 2;
    double totalbayar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);
        getDataPref();
        spin_rek = findViewById(R.id.spin_rek);
        perizinan();


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
        tvxKonfirmasi = findViewById(R.id.tvxKonfirmasi);
        lnrek = findViewById(R.id.lnrek);
        lnrek.setVisibility(View.GONE);
//        total = getIntent().getStringExtra("totalbayar");
//        total = getIntent().getExtras().getString("totalbayar");

        Bundle b = getIntent().getExtras();
        totalbayar = b.getDouble("totalbayar");
        kodetransaksi = b.getInt("kodetransaksi");
        sttotal = new StringTokenizer(formatRupiah.format(totalbayar), ",");
        String harganya = sttotal.nextToken().trim();
        edTotalbayar.setText(harganya);
        edKodeTransaksi.setText(String.valueOf(kodetransaksi));
        edNamaPengirim.setText(String.valueOf(namaPengirim));

        btnChooseImg.setOnClickListener(this);

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseImg:
                selectImage();
                break;

            case R.id.tvxKonfirmasi:
                simpanKonfirmasi();
                break;

            default:
                break;
        }

    }

    public void getDataPref() {
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        namaPengirim = preferences.getNamaLengkap();

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

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = null;
        String filePath = null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImageUri = data.getData();
                }
                break;
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {
                    selectedImageUri = imageUri;
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
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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
        int bayar = (int) Math.round(totalbayar);
//        Toast.makeText(this, ""+kodetransaksi+" "+bayar+" "+idrekAdmin+" "+namaPengirim+" "+imagePath, Toast.LENGTH_SHORT).show();
        File file = new File(imagePath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody ImageName = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        RequestBody _id_konsumen = RequestBody.create(MediaType.parse("text/plain"), id_konsumen);
        Call<ResKonfirmasi> call = service.simpanKonfirmasi(kodetransaksi, bayar, idrekAdmin, namaPengirim, imageBody);
        call.enqueue(new Callback<ResKonfirmasi>() {
            @Override
            public void onResponse(Call<ResKonfirmasi> call, Response<ResKonfirmasi> response) {
                Log.d("resimg", String.valueOf(response));
                if (response.body() != null && response.isSuccessful()) {
                    Intent intent = new Intent(KonfirmasiPembayaranActivity.this, StatusPembayaran.class);
                    startActivity(intent);
                    finish();


                } else {
                    Toast.makeText(KonfirmasiPembayaranActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResKonfirmasi> call, Throwable t) {
                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }
}
