package com.sholeh.marketplacenj.util.api;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.model.Kategori;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.respon.ResDetailAlamat;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.respon.ResImg;
import com.sholeh.marketplacenj.respon.ResKeranjang;
import com.sholeh.marketplacenj.respon.ResLogin;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.respon.ResUbahJumlahProduk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIInterface {
    @GET("api/produk")
    abstract Call<List<Model>> getProduk();

    @GET("api/pelapak/{id_user}")
     Call<List<Model>> getProdukPelapak(
            @Path("id_user") String id_user);

    @GET("api/kategori")
    Call<List<Kategori>> getKategori();

    @GET("api/kategori/{id_kategori}")
    Call<List<Model>> getKategoriByid( @Path("id_kategori") String idKategori );


    // Province get from raja ongkir
    @GET("province")
    @Headers("key:1c082f667d455277ed87334b364c9ac3")
    Call<ItemProvince> getProvince();
    //1c082f667d455277ed87334b364c9ac3 panggil di register

    // City get from raja ongkir
    @GET("city")
    @Headers("key:1c082f667d455277ed87334b364c9ac3")
    Call<ItemCity> getCity(@Query("province") String province);

    // signup konsumen
    @FormUrlEncoded
    @POST("api/konsumen")
    Call<ResRegristasi> registerKonsumenCall(@Field("nama_lengkap") String namaLengkap,
                                             @Field("username") String username,
                                             @Field("password") String password,
                                             @Field("nomor_hp") String nomorHp,
                                             @Field("email") String email,
                                             @Field("status") String status);

    //  user signin konsumen request
    @FormUrlEncoded
    @POST("api/login")
    Call<ResLogin> loginKonsumenCall(@Field("username") String username,
                                     @Field("password") String password);

    ///  ubah password
    @FormUrlEncoded
    @PUT("api/password/{id_konsumen}")
    Call<ResNewPassword> KonsumenUbahPassword(
            @Path("id_konsumen") String idKonsumen,
            @Field("cekpassword") String cekPassword,
            @Field("password") String password
    );


    //  tampil data profile
    @GET("api/profil/{id_konsumen}")
    Call<ResProfil> getDataProfil(
            @Path("id_konsumen") String idKonsumen
    );

    // add new address
    @FormUrlEncoded
    @POST("api/konsumen/alamat")
    Call<ResAlamat> addAlamatCall(@Field("nama") String nama,
                                  @Field("nomor_telepon") String nomorTelepon,
                                  @Field("provinsi_id") String provinsiId,
                                  @Field("nama_provinsi") String namaProvinsi,
                                  @Field("city_id") String cityId,
                                  @Field("nama_kota") String namaKota,
                                  @Field("kode_pos") String kodePos,
                                  @Field("alamat_lengkap") String alamatLengkap,
                                  @Field("user_id") String userId,
                                  @Field("user_type") String userType);

    //  tampil detail alamat
    @GET("api/konsumen/tampil/alamat/{id_alamat}")
    Call<ResDetailAlamat> getDetailAlamat(
            @Path("id_alamat") String idAlamat
    );


    ///  ubah alamat
    @FormUrlEncoded
    @PUT("api/konsumen/edit/alamat/{id_alamat}")
    Call<ResAlamat> KonsumenUbahAlamat(
            @Path("id_alamat") String idAlamat,
            @Field("nama") String nama,
            @Field("nomor_telepon") String nomorTelepon,
            @Field("provinsi_id") String provinsiId,
            @Field("nama_provinsi") String namaProvinsi,
            @Field("city_id") String cityId,
            @Field("nama_kota") String namaKota,
            @Field("kode_pos") String kodePos,
            @Field("alamat_lengkap") String alamatLengkap,
            @Field("user_id") String userId,
            @Field("user_type") String userType
    );

    //  delete alamat
    @DELETE("api/konsumen/hapus/alamat/{id_alamat}")
    Call<ResAlamat> hapusItemAlamat(@Path("id_alamat") String idAlamat);

    //  ubah alamat
    @FormUrlEncoded
    @PUT("api/konsumen/{id_konsumen}")
    Call<ResRegristasi> updateKonsumen(
            @Path("id_konsumen") String idKonsumen,
            @Field("nama_lengkap") String namaLengkap,
            @Field("nomor_hp") String nomorHp,
            @Field("email") String email,
            @Field("status") String status);


    @Multipart
    @POST("api/konsumen/upload")
    Call<ResImg> uploadProfiKonsumen(
            @Part("id_konsumen") RequestBody idKonsumen,
            @Part MultipartBody.Part file);


    @GET("api/keranjang")
    Call<List<Model>> getData();

    @GET("api/produk/{id}")
    Call<JsonObject> getProdukId(@Path("id") String id);

    @GET("api/produk")
    Call<List<Model>> getAllData(@Query("cari") String nama_produk);


    @FormUrlEncoded
    @POST("api/keranjang")
    Call<ResKeranjang> simpanKeranjang(
            @Field("produk_id") String produkId,
            @Field("pembeli_id") String pembeliId,
//            @Field("pembeli_type") String pembeliType,
//            @Field("status") String status,
            @Field("jumlah") String jumlah,
            @Field("harga_jual") String hargaJual
    );


    @GET("api/keranjang")
    Call<ResDetailKeranjang> getDataDetailKeranjang(
            @Query("id") String id);

    //  delete produk keranjang
    @DELETE("api/keranjang/{id_keranjang}")
    Call<ResHapusKeranjang> hapusProdukKeranjang(@Path("id_keranjang") String idKeranjang);


    //  ubah jumlah keranjang
    @FormUrlEncoded
    @PUT("api/keranjang/ganti_jumlah/{id_keranjang}")
    Call<ResUbahJumlahProduk> updateJumlah(
            @Path("id_keranjang") String idKeranjang,
            @Field("jumlah") String Jumlah);

    // ubah status keranjang
    @FormUrlEncoded
    @PUT("api/keranjang/{id_user}/go_checkout")
    Call<ResDetailKeranjang> ubahStatusKeranjang(
            @Path("id_user") String id_user,
            @Field("id_keranjang[]") ArrayList<String> id_keranjang
    );

}







