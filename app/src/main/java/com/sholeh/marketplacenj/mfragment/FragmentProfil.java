package com.sholeh.marketplacenj.mfragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.activities.AkunActivity;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.activities.PengaturanAkun;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.activities.ImageProfilActivity;
import com.sholeh.marketplacenj.activities.LoginActivity;
import com.sholeh.marketplacenj.activities.TabFragmentPelapak;
import com.sholeh.marketplacenj.activities.TabFragmentPembeli;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.Preferences;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfil extends Fragment implements View.OnClickListener {
    private ImageView btnImgProfil, nav_home, nav_notifikasi, nav_transaksi, navprofile;
    TextView tvx_login, tvx_namaCustomter, tvx_title, tvx_logout,tvx_edit, tvx_username,
              tvx_Hp, tvx_profil, tvx_alamat, tvx_setting, tvx_email;



    private CircleImageView imageProfil;



    FloatingActionButton fb_favourite;
    Toolbar toolBarisi;
    Preferences preferences;
    String id_konsumen, username, namaLengkap, nomorHP, email;
    String sfoto = null;
    private ResProfil tvDataProfil;

    Bitmap bitmap = null;
    Uri imageUri;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;


    @Override
    public void onResume()
    {
        getDataProfil();

        super.onResume();
        // Load data and do stuff
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil,container,false);

        tvx_namaCustomter = rootView.findViewById(R.id.tvCustomerName);
        tvx_username = rootView.findViewById(R.id.tvx_username);
        tvx_email = rootView.findViewById(R.id.tvx_Email);
        tvx_Hp = rootView.findViewById(R.id.tvx_nomorhp);
        tvx_profil = rootView.findViewById(R.id.tv_myprofil);
        tvx_alamat = rootView.findViewById(R.id.tvAlamat);
        tvx_setting = rootView.findViewById(R.id.tvSetting);


        btnImgProfil = rootView.findViewById(R.id.imgProfil);
        btnImgProfil.setOnClickListener(this);
        imageProfil = rootView.findViewById(R.id.cirprofile_image);
        tvx_profil.setOnClickListener(this);
        tvx_alamat.setOnClickListener(this);
        tvx_setting.setOnClickListener(this);


        tvx_title = rootView.findViewById(R.id.title);
        tvx_edit = rootView.findViewById(R.id.edit_txt);
        tvx_edit.setVisibility(View.GONE);
        tvx_logout = rootView.findViewById(R.id.logout_akun);
        tvx_logout.setOnClickListener(this);
        tvx_logout.setVisibility(View.VISIBLE);
        tvx_title.setVisibility(View.VISIBLE);

//        ValDataProfil userImg = Preferences.getInstance(getContext()).getProfil();
//        ValDataProfil d = Preferences.getInstance(getContext()).getProfil().getFotoProfil();

//
//        Toast.makeText(getActivity(), ""+id_konsumen, Toast.LENGTH_SHORT).show();
//
//        Picasso.with(getContext()).load(imgprofil.gey).into(imageProfil);
//
//

        TabLayout tabLayout =  rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("AKUN PEMBELI"));
        tabLayout.addTab(tabLayout.newTab().setText("AKUN PELAPAK"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = rootView.findViewById(R.id.pagerprofil);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        PagerAdapterAkun adapter = new PagerAdapterAkun(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
//        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getDataPref();


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgProfil:
                Intent intent = new Intent(getActivity(), ImageProfilActivity.class);
                getActivity().startActivity(intent);
//                selectImage();
                break;

            case R.id.cirprofile_image:
                Intent intent2 = new Intent(getActivity(), ImageProfilActivity.class);
                getActivity().startActivity(intent2);
//                selectImage();
                break;

            case R.id.tv_myprofil:
                Intent intent3 = new Intent(getActivity(), AkunActivity.class);
                getActivity().startActivity(intent3);
//                selectImage();
                break;

            case R.id.tvx_alamat:
                Intent intent4 = new Intent(getActivity(), AlamatActivity.class);
                getActivity().startActivity(intent4);
                Toast.makeText(getActivity(), "klik", Toast.LENGTH_SHORT).show();

//                selectImage();
                break;

            case R.id.tvSetting:
                Intent intent5 = new Intent(getActivity(), PengaturanAkun.class);
                getActivity().startActivity(intent5);
//                selectImage();
                break;

            case R.id.logout_akun:
              logoutAkun();
                break;



            default:
                break;
        }
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new TabFragmentPembeli();
                case 1:
                    return new TabFragmentPelapak();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void getDataPref(){
        preferences = new Preferences(getActivity());
        id_konsumen = preferences.getIdKonsumen();
        username = preferences.getUsername();
        namaLengkap = preferences.getNamaLengkap();
        email = preferences.getEmailnya();
        nomorHP = preferences.getNomorHp();
        tvx_namaCustomter.setText(namaLengkap);
        tvx_username.setText(username);
        tvx_email.setText(email);
        tvx_Hp.setText(nomorHP);

    }


    public void getDataProfil(){

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);

        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {
                tvDataProfil = response.body();
                Log.d("cekimg", String.valueOf(tvDataProfil));
              // validasi error null asset foto
            //    Toast.makeText(getActivity(), ""+tvDataProfil.getPesan(), Toast.LENGTH_SHORT).show();
//                if (tvDataProfil.getData().getFotoProfil() == null){
//                   // Picasso.with(getContext()).load(R.drawable.man).into(imageProfil);
//                }else{
                    Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/"+tvDataProfil.getData().getFotoProfil()).into(imageProfil);
//                }
//                Toast.makeText(getActivity(), tvDataProfil.getData().getFotoProfil(), Toast.LENGTH_LONG).show();
//                Glide.with(getActivity()).load(foto).into(imageProfil);

            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                Toast.makeText(getActivity(), "no connection"+t, Toast.LENGTH_SHORT).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }

    private void selectImage() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
    }



//    private void uploadToServer(String filePath) {
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        UploadAPIs uploadAPIs = retrofit.create(UploadAPIs.class);
//        //Create a file object using file path
//        File file = new File(filePath);
//        // Create a request body with file and image media type
//        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
//        // Create MultipartBody.Part using file request-body,file name and part name
//        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
//        //Create request body with text description and text media type
//        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
//        //
//        Call call = uploadAPIs.uploadImage(part, description);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//            }
//            @Override
//            public void onFailure(Call call, Throwable t) {
//            }
//        });
//    }


    public  void logoutAkun(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Apakah anda yakin, ingin logout?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preferences.saveSPBoolean(preferences.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
                Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
