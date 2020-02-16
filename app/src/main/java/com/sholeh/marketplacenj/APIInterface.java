package com.sholeh.marketplacenj;

import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.respon.ResAddAlamat;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResLogin;
import com.sholeh.marketplacenj.respon.ResProfil;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
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
    @Multipart
    @POST("api/konsumen")
    Call<ResRegristasi> registerKonsumenCall(@Part("nama_lengkap") RequestBody namaLengkap,
                                             @Part("username") RequestBody username,
                                             @Part("password") RequestBody password,
                                             @Part("nomor_hp") RequestBody nomorHp,
                                             @Part("email") RequestBody email,
                                             @Part("status") RequestBody status);

    //  user signin konsumen request
    @Multipart
    @POST("api/login")
    Call<ResLogin> loginKonsumenCall(@Part("username") RequestBody phone,
                                     @Part("password") RequestBody password);

    ///  ubah password
    @FormUrlEncoded
    @PUT("api/password/{id_konsumen}")
    Call<ResNewPassword> KonsumenUbahPassword(
            @Path("id_konsumen") String idKonsumen,
            @Field("password") String password
    );


    //  tampil data profile
    @GET("api/profil/{id_konsumen}")
    Call<ResProfil> getDataProfil(
            @Path("id_konsumen") String idKonsumen
    );

    // add new address
    @Multipart
    @POST("api/konsumen/alamat")
    Call<ResAddAlamat> addAlamatCall(@Part("nama") RequestBody nama,
                                     @Part("nomor_telepon") RequestBody nomorTelepon,
                                     @Part("provinsi_id") RequestBody provinsiId,
                                     @Part("nama_provinsi") RequestBody namaProvinsi,
                                     @Part("city_id") RequestBody cityId,
                                     @Part("nama_kota") RequestBody namaKota,
                                     @Part("kode_pos") RequestBody kodePos,
                                     @Part("alamat_lengkap") RequestBody alamatLengkap,
                                     @Part("user_id") RequestBody userId);


}







