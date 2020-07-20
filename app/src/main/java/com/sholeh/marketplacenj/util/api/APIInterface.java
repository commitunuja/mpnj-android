package com.sholeh.marketplacenj.util.api;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.model.Kategori;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.city.ItemCity;
import com.sholeh.marketplacenj.model.cost.ItemCost;
import com.sholeh.marketplacenj.model.pesanan.Pesanan;
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.DetailPesanan;
import com.sholeh.marketplacenj.model.province.ItemProvince;
import com.sholeh.marketplacenj.model.subdistrict.ItemKec;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.respon.ResBank;
import com.sholeh.marketplacenj.respon.ResBanner;
import com.sholeh.marketplacenj.respon.ResDetailAlamat;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.respon.ResKeranjang;
import com.sholeh.marketplacenj.respon.ResKonfirmasi;
import com.sholeh.marketplacenj.respon.ResLogin;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.respon.ResRekAdmin;
import com.sholeh.marketplacenj.respon.ResTampilWishlist;
import com.sholeh.marketplacenj.respon.ResUbahJumlahProduk;
import com.sholeh.marketplacenj.respon.ResWishlist;
import com.sholeh.marketplacenj.respon.RestCost;

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
    // Diskon
    @GET("api/produk/diskon")
    abstract Call<List<Model>> getProdukDiskon();

    // Terpopuler
    @GET("api/produk/laris")
    abstract Call<List<Model>> getProdukTerpopuler();


    // Terbaru
    @GET("api/produk")
    abstract Call<List<Model>> getProduk();

    @GET("api/pelapak/{id_user}")
    Call<List<Model>> getProdukPelapak(
            @Path("id_user") String id_user);

    @GET("api/kategori")
    Call<List<Kategori>> getKategori();

    @GET("api/kategori/{id_kategori}")
    Call<List<Model>> getKategoriByid(@Path("id_kategori") String idKategori);


    // Province get from raja ongkir
    @GET("province")
    @Headers("key:b28063e60be5386c072394b4713aae8d")
    Call<ItemProvince> getProvince();
    //1c082f667d455277ed87334b364c9ac3 panggil di register

    // City get from raja ongkir
    @GET("city")
    @Headers("key:b28063e60be5386c072394b4713aae8d")
    Call<ItemCity> getCity(@Query("province") String province);

    @GET("subdistrict")
    @Headers("key:b28063e60be5386c072394b4713aae8d")
    Call<ItemKec> getKec(@Query("city") String city);

    @FormUrlEncoded
    @POST("cost")
    @Headers("key:b28063e60be5386c072394b4713aae8d")
    Call<ItemCost> hitungOngkir(@Field("origin") String origin,
                                @Field("originType") String originType,
                                @Field("destination") String destination,
                                @Field("destinationType") String destinationType,
                                @Field("weight") int weight,
                                @Field("courier") String courier);

    // signup konsumen
    @FormUrlEncoded
    @POST("api/konsumen")
    Call<ResRegristasi> registerKonsumenCall(@Field("nama_lengkap") String namaLengkap,
                                             @Field("username") String username,
                                             @Field("password") String password,
                                             @Field("nomor_hp") String nomorHp,
                                             @Field("email") String email);

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
    @GET("api/konsumen/profil/{id_konsumen}")
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
                                  @Field("kecamatan_id") String kecamatanId,
                                  @Field("nama_kecamatan") String namaKecamatan,
                                  @Field("kode_pos") String kodePos,
                                  @Field("alamat_lengkap") String alamatLengkap,
                                  @Field("user_id") String userId);

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
            @Field("kecamatan_id") String kecamatanId,
            @Field("nama_kecamatan") String namaKecamatan,
            @Field("kode_pos") String kodePos,
            @Field("alamat_lengkap") String alamatLengkap,
            @Field("user_id") String userId);

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
    Call<ResRegristasi> uploadProfiKonsumen(
            @Part("id_konsumen") RequestBody idKonsumen,
            @Part MultipartBody.Part file);


    @GET("api/keranjang")
    Call<List<Model>> getData();

    @GET("api/produk/{id}")
    Call<JsonObject> getProdukId(@Path("id") String id);

    @GET("api/produk")
    Call<List<Model>> getAllData(@Query("cari") String nama_produk);

//    @GET("api/pesanan")
//    Call<Pesanan> getDataPesanan(@Query("id") String id);

    @GET("api/pesanan")
    Call<Pesanan> getDataPesanan(@Query("id") String id, @Query("tab") String tab);

    @GET("api/pesanan/detail/{kode}")
    Call<DetailPesanan> getDataDetailPesanan(@Path("kode") String kode);

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

    @FormUrlEncoded
    @POST("api/transaksi")
    Call<ResDetailKeranjang> getDataTransaksi(
            @Query("id") String id,
            @Field("id_keranjang[]") List<String> id_keranjang);

    @FormUrlEncoded
    @POST("api/transaksi/simpan")
    Call<JsonObject> simpanTransaksi(
            @Field("user_id") String user_id,
            @Field("totalBayar") Double totalBayar,
            @Field("id_keranjang[]") List<String> id_keranjang);

    @FormUrlEncoded
    @POST("api/transaksi/simpanKurir")
    Call<JsonObject> simpan_kurir(
            @Field("kurir") String kurir,
            @Field("service") String service,
            @Field("ongkir") Integer ongkir,
            @Field("etd") String etd,
            @Field("id_keranjang[]") List<String> id_keranjang);

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
            @Field("id_keranjang[]") List<String> id_keranjang

    );

    @FormUrlEncoded
    @PUT("api/transaksi/batal")
    Call<JsonObject> batalCheckout(@Field("user_id") String user_id);

    @GET("api/bank")
    Call<ResBank> getBank();

    //  tampil data bank admin /where
    @GET("api/bank/rekening/{id_bank}")
    Call<ResRekAdmin> getDataBank(
            @Path("id_bank") String idBank
    );

    // konfirmasi pembayaran
    @Multipart
    @POST("api/konfirmasi/simpan")
    Call<ResKonfirmasi> simpanKonfirmasi(
            @Part("kode_transaksi") RequestBody kodeTransaksi,
            @Part("total_transfer") RequestBody totalTransfer,
            @Part("rekening_admin_id") RequestBody rekeningAdminId,
            @Part("nama_pengirim") RequestBody namaPengirim,
            @Part MultipartBody.Part file);

    // ubah alamat utama
    @FormUrlEncoded
    @PUT("api/konsumen/edit/alamat/utama/{id_user}")
    Call<ResAlamat> UbahAlamatUtama(
            @Path("id_user") String userId,
            @Field("id_alamat") String idAlamat
    );

    @FormUrlEncoded
    @POST("api/transaksi/batal_transaksi")
    Call<JsonObject> batalPesanan(@Field("transaksi_id") String transaksiId);


    // add wishlist
    @FormUrlEncoded
    @POST("api/wishlist/simpan")
    Call<ResWishlist> addWishlist(@Field("id_user") String userId,
                                  @Field("id_produk") String produkId);

    //  tampil data wishlist
    @GET("api/wishlist/tampil/{id_user}")
    Call<ResTampilWishlist> getDataWishlist(
            @Path("id_user") String idUser
    );

    //  delete produk keranjang
    @DELETE("api/wishlist/hapus/{id_wishlist}")
    Call<ResHapusKeranjang> hapusProdukWishlist(
            @Path("id_wishlist") String idWishlist);


    // cari wishlist
    @FormUrlEncoded
    @POST("api/wishlist/cari")
    Call<ResTampilWishlist> cariWishlist(@Field("id_user") String userId,
                                  @Field("nama_produk") String namaProduk);

    @GET("api/banner")
    Call<ResBanner> getBanner();

}







