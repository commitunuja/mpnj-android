package com.sholeh.marketplacenj.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResImg;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.Preferences;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;


public class ImageProfilActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layImg;
    ImageView imgProfil;
    Button pilih, upload;
    Bitmap bitmap = null;
    Uri imageUri;
    Preferences preferences;
    String id_konsumen;

    private ProgressDialog progres;
    Toolbar toolBarisi;
    String imagePath;


    //Uri to store the image uri


    private static final int PICK_IMAGE = 100;
    private static final int PERMISSION_STORAGE = 2;

    private Unbinder mUnbinder;
    private String selectImagePath;
    private Snackbar mSnackbar;
    private ResProfil tvDataProfil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_profil);
//            setTheme(R.style.Theme_Transparent);
        toolBarisi = findViewById(R.id.toolbar);
        toolBarisi.setTitle("Foto Profil");
        setSupportActionBar(toolBarisi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
//        preferences = new Preferences(getApplication());
//        layImg= findViewById(R.id.layProfil);
//        layImg.setVisibility(View.GONE);

        imgProfil = findViewById(R.id.img_profile);
        pilih = findViewById(R.id.btn_pilih_gambar);
        upload = findViewById(R.id.btn_ubahfoto_profile);


        preferences = new Preferences(getApplication());
        pilih.setOnClickListener(this);
        upload.setOnClickListener(this);



        getDataProfil();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); //  kalau tanpa ini tombol panahnya tak berfungsi
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ubahfoto_profile:

                uploadImage();
                break;
            case R.id.btn_pilih_gambar:
                selectImage();
//                Toast.makeText(this, "klik", Toast.LENGTH_SHORT).show();

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


                Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/"+tvDataProfil.getData().getFotoProfil()).into(imgProfil);


            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                Toast.makeText(ImageProfilActivity.this, "no connection"+t, Toast.LENGTH_SHORT).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }


    private void selectImage() {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectImageUri = data.getData();
            selectImagePath = getRealPathFromURI(selectImageUri);
            decodeImage(selectImagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectImage();
                }

                return;
            }
        }
    }

    private String getRealPathFromURI(Uri selectImageUri) {

        Cursor cursor = getContentResolver().query(selectImageUri, null, null, null, null);
        if (cursor == null) {
            return selectImageUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private void decodeImage(String selectImagePath) {
        int targetW = imgProfil.getWidth();
        int targetH = imgProfil.getHeight();

        final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectImagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(selectImagePath, bmOptions);
        if (bitmap != null) {
            imgProfil.setImageBitmap(bitmap);
        }
    }



    private void uploadImage() {
        File file = new File(selectImagePath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody ImageName = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        RequestBody _id_konsumen = RequestBody.create(MediaType.parse("text/plain"), id_konsumen);
        Call<ResImg> call  = service.uploadProfiKonsumen(_id_konsumen, imageBody);
        call.enqueue(new Callback<ResImg>() {
            @Override
            public void onResponse(Call<ResImg> call, Response<ResImg> response) {
                Log.d("resimg", String.valueOf(response));
                Toast.makeText(ImageProfilActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResImg> call, Throwable t) {
                Toast.makeText(ImageProfilActivity.this, "Gagal"+t, Toast.LENGTH_LONG).show();
                Log.d("kocor", String.valueOf(t));

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }



}
