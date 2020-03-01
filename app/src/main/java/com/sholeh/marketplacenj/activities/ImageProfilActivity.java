package com.sholeh.marketplacenj.activities;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;



public class ImageProfilActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layImg;
    ImageView imgProfil;
    Button pilih, upload;
    Bitmap bitmap = null;
    Uri imageUri;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    Preferences preferences;

    private ProgressDialog progres;
    Toolbar toolBarisi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//

        if(bitmap != null){
            Toast.makeText(this, "tampil activity", Toast.LENGTH_SHORT).show();
            super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_image_profil);
//            setTheme(R.style.Theme_Transparent);
//        toolBarisi = findViewById(R.id.toolbar);
//        toolBarisi.setTitle("Foto Profil");
//        setSupportActionBar(toolBarisi);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        preferences = new Preferences(getApplication());
//        layImg= findViewById(R.id.layProfil);
//        layImg.setVisibility(View.GONE);

            imgProfil = findViewById(R.id.img_profile);
            pilih = findViewById(R.id.btn_pilih_gambar);
            upload = findViewById(R.id.btn_ubahfoto_profile);


            preferences = new Preferences(getApplication());
            pilih.setOnClickListener(this);
            upload.setOnClickListener(this);

        }else{
            Toast.makeText(this, "tidak tampil activity", Toast.LENGTH_SHORT).show();
            selectImage();
        }

//
//        urltoimageProfil = getIntent().getExtras().getString("fotoprofil");
//        Toast.makeText(this, ""+urltoimageProfil, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "klik", Toast.LENGTH_SHORT).show();
//                upload();
                break;
            case R.id.btn_pilih_gambar:
                selectImage();
//                Toast.makeText(this, "klik", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] options = {"Ambil Foto", "Gallery"};
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
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
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
        imgProfil.setImageBitmap(bitmap);


    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
