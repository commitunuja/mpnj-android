package com.sholeh.marketplacenj.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CONSTANTS {
    public static final String BASE_URL = "http://belanj.id/"; //rubah dengan IP devicenya
    public static final String SUB_DOMAIN = "http://seller.belanj.id/assets/foto_produk/";
    public static final String FOTO_TOKO = "http://seller.belanj.id/assets/foto_toko/";
    public static final String ASSETBANK = "http://belanj.id/assets/foto_bank/";
    public static final String ASSETBANNER = "https://admin.belanj.id/assets/banner/";
//    public static final String BASE_URL="http://192.168.137.154/mpnj/public/"; //rubah dengan IP devicenya

    public static final String URL_RAJAONGKIR = "https://pro.rajaongkir.com/api/";

    public static final String QUOTE_ID = "QUOTE_ID";
    public static final String CART_ITEM_COUNT = " CART_ITEM_COUNT";
    public static final long API_CONNECTION_TIMEOUT = 1201;
    public static final long API_READ_TIMEOUT = 901;


    public static final String ID_KONSUMEN = "id_konsumen";
    public static final String USER_NAME = "username";
    public static final String NAMA_LENGKAP = "nama_lengkap";
    public static final String PHONE = "nomor_hp";
    public static final String EMAIL = "email";
    private static Retrofit retrofit;

    public static String SECONDARY_COLOR = "#04d39f";
    public static String SECOND_COLOR = "secondary_color";


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
