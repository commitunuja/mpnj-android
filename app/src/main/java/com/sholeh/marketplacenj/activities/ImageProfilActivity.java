package com.sholeh.marketplacenj.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;

import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.activities.alamat.PilihAlamatCheckout;
import com.sholeh.marketplacenj.activities.transaksi.KonfirmasiPembayaranActivity;
import com.sholeh.marketplacenj.activities.transaksi.StatusPembayaran;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.Preferences;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;
import static okhttp3.MediaType.parse;


public class ImageProfilActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layImg;
    ImageView imgProfil;
    Button pilih, upload;

    Preferences preferences;
    String id_konsumen;

    private ProgressDialog progres;
    Toolbar toolBarisi;


    //Uri to store the image uri

    Bitmap bitmap = null;
    Uri imageUri;
    String imagePath;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;

    private Unbinder mUnbinder;

    private Snackbar mSnackbar;
    private ResProfil tvDataProfil;
    private KProgressHUD progressDialogHud;


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
        progressDialogHud = KProgressHUD.create(ImageProfilActivity.this);
//        preferences = new Preferences(getApplication());
//        layImg= findViewById(R.id.layProfil);
//        layImg.setVisibility(View.GONE);

        imgProfil = findViewById(R.id.img_profile);
        pilih = findViewById(R.id.btn_pilih_gambar);
        upload = findViewById(R.id.btn_ubahfoto_profile);
        preferences = new Preferences(getApplication());
        pilih.setOnClickListener(this);
        upload.setOnClickListener(this);
        perizinan();
        getDataProfil();


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); //  kalau tanpa ini tombol panahnya tak berfungsi
        return true;
    }

    private void perizinan() {
        ActivityCompat.requestPermissions(ImageProfilActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                99);
    }


    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
    }

    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
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

    public void getDataProfil() {
        ProgresDialog();
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);

        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {
                progressDialogHud.dismiss();
                tvDataProfil = response.body();
                Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/" + tvDataProfil.getData().getFotoProfil()).into(imgProfil);


            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                Toast.makeText(ImageProfilActivity.this, "Terdapat Kesalahan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                progressDialogHud.dismiss();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }

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
                    ImageProfilActivity.this);
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
        } else {
            final CharSequence[] options = {"Ambil Foto", "Gallery", "Kembali"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ImageProfilActivity.this);
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

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
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
                    decodeFile(imagePath);

//                    Toast.makeText(this, "ok1", Toast.LENGTH_SHORT).show();

                }
                break;
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {
                    selectedImageUri = imageUri;
                    imagePath = getPath(selectedImageUri);
                    decodeFile(imagePath);
                    Toast.makeText(ImageProfilActivity.this, "Ambil foto dengan posisi hp landscape untuk hasil yang maksimal", Toast.LENGTH_LONG).show();

//                    Toast.makeText(this, "ok2", Toast.LENGTH_SHORT).show();



//                    try {
//                        ExifInterface ei = new ExifInterface(imagePath);
//                        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
//                                ExifInterface.ORIENTATION_UNDEFINED);
//                        Bitmap rotatedBitmap = null;
//                        switch(orientation) {
//
//                            case ExifInterface.ORIENTATION_ROTATE_90:
//                                rotatedBitmap = rotateImage(bitmap, 90);
//                                break;
//
//                            case ExifInterface.ORIENTATION_ROTATE_180:
//                                rotatedBitmap = rotateImage(bitmap, 180);
//                                break;
//
//                            case ExifInterface.ORIENTATION_ROTATE_270:
//                                rotatedBitmap = rotateImage(bitmap, 270);
//                                break;
//
//                            case ExifInterface.ORIENTATION_NORMAL:
//                            default:
//                                rotatedBitmap = bitmap;
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Foto Tidak Di Ambil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Foto Tidak Di Ambil", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        if (selectedImageUri != null) {
            try {
                String filemanagerstring = selectedImageUri.getPath(); // // OI FILE Manager
                String selectedImagePath = getPath(selectedImageUri); //MEDIA GALLERY

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast.makeText(ImageProfilActivity.this, "Unknown path",
                            Toast.LENGTH_LONG).show();
                    Log.e("Bitmap", "Unknown path");
                }

                if (filePath != null) {
                    decodeFile(filePath);
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast.makeText(ImageProfilActivity.this, "Internal error",
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }
        }

    }

    public String getPath(Uri uri) {
//
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
////        Toast.makeText(this, "getPath", Toast.LENGTH_SHORT).show();
//        if (cursor != null) {
//            int column_index = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        } else
//            return null;

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    public void decodeFile(String filePath) {
        // Decode image size
//        BitmapFactory.Options o = new BitmapFactory.Options();
//        o.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(filePath, o);
//        final int REQUIRED_SIZE = 1024;
//        int width_tmp = o.outWidth;
//        int height_tmp = o.outHeight;
//        int scale = 1;
//        while (true) {
//            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
//                break;
//            width_tmp /= 2;
//            height_tmp /= 2;
//            scale *= 2;
//        }
//        BitmapFactory.Options o2 = new BitmapFactory.Options();
//        o2.inSampleSize = scale;
//        bitmap = BitmapFactory.decodeFile(filePath, o2);
//
//
////        Matrix matrix = new Matrix();
////        matrix.postRotate(90);
////        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width_tmp, height_tmp, true);
////        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
//
////        Toast.makeText(this, "File Path", Toast.LENGTH_SHORT).show();
//        imgProfil.setImageBitmap(bitmap);
//        imgProfil.setVisibility(View.VISIBLE);

        int targetW = imgProfil.getWidth();
        int targetH = imgProfil.getHeight();

        final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, bmOptions);


        // compress
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String encoded = Base64.encodeToString(b, Base64.DEFAULT);


        if (bitmap != null) {
            imgProfil.setImageBitmap(bitmap);

        }
    }


    private void uploadImage() {
//        if ( !=null){ // foto ada


        ProgresDialog();
        File file = new File(imagePath);
//        long lenght = file.length();
        long length = file.length();
        length = length / 1024; // Kb = 1 mb
//            Toast.makeText(this, "lenght "+length, Toast.LENGTH_SHORT).show();
        int compressionRatio = 15;  //1 == originalImage, 2 = 50% compression, 4=25% compress
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
        } catch (Throwable t) {
            Log.e("ERROR", "Error compressing file." + t.toString());
            t.printStackTrace();
        }

//        Bitmap bmp = BitmapFactory.decodeFile(imagePath);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 70, bos);
//        InputStream in = new ByteArrayInputStream(bos.toByteArray());
//        ContentBody foto = new InputStreamBody(in, "image/jpeg", "filename");

//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("data_type", RequestBody.create(parse("text"), "PostComplaint".getBytes()));
//        map.put("complaint_category", RequestBody.create(parse("text"), caty_id_str.getBytes()));
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(parse("text/plain"), file.getName());


        RequestBody reqFile = RequestBody.create(parse("multipart/form-data"), file);
        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody ImageName = RequestBody.create(parse("text/plain"), file.getName());
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        RequestBody _id_konsumen = RequestBody.create(parse("text/plain"), id_konsumen);
        Call<ResRegristasi> call = service.uploadProfiKonsumen(_id_konsumen, fileToUpload);
        call.enqueue(new Callback<ResRegristasi>() {
            @Override
            public void onResponse(Call<ResRegristasi> call, Response<ResRegristasi> response) {
                Log.d("resimgs", String.valueOf(response));
                if (response.body() != null && response.isSuccessful()) {
                    Toast.makeText(ImageProfilActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    finish();
                    progressDialogHud.dismiss();
                } else {
                    progressDialogHud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResRegristasi> call, Throwable t) {
                Toast.makeText(ImageProfilActivity.this, "Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_LONG).show();
                Log.d("resimgg", String.valueOf(t));
                progressDialogHud.dismiss();


                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
//        }else{
//            Toast.makeText(this, "Pilih Foto Terlebih Dahulu", Toast.LENGTH_SHORT).show();
//        }

    }


}