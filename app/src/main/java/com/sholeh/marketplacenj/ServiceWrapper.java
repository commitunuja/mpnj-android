package com.sholeh.marketplacenj;

import okhttp3.Interceptor;
import retrofit2.Retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sholeh.marketplacenj.respon.ResAddAlamat;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.respon.ResLogin;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceWrapper { // ini service wrapper untuk konversi dan menampung semua respon
    private APIInterface mServiceInterface;

    public ServiceWrapper(Interceptor mInterceptorheader) {
        mServiceInterface = getRetrofit(mInterceptorheader).create(APIInterface.class);
    }

    public Retrofit getRetrofit(Interceptor mInterceptorheader) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mOkHttpClient = null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONSTANTS.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(CONSTANTS.API_READ_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        mOkHttpClient = builder.build();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient)
                .build();
        return retrofit;
    }

//    new regristasi konsumen
    public Call<ResRegristasi> newUserRegistrationCall(String namaLengkap, String username, String password, String nomorHp, String email, String status) {
        return mServiceInterface.registerKonsumenCall(
                                                    convertPlainString(namaLengkap), convertPlainString(username),
                                                    convertPlainString(password),  convertPlainString(nomorHp),
                                                    convertPlainString(email), convertPlainString(status));
    }


    // user signin konsumen
    public Call<ResLogin> KonsumenSigninCall(String username, String password){
        return mServiceInterface.loginKonsumenCall(convertPlainString(username),  convertPlainString(password));
    }

    //    new alamat
    public Call<ResAddAlamat> newAlamatCall(String nama, String nomorHp, String provinsiId, String namaProvinsi, String cityId, String namaKota, String kodePos, String alamatLengkap, String userId, String userType) {
        return mServiceInterface.addAlamatCall(
                convertPlainString(nama), convertPlainString(nomorHp),
                convertPlainString(provinsiId),  convertPlainString(namaProvinsi),
                convertPlainString(cityId), convertPlainString(namaKota),
                convertPlainString(kodePos), convertPlainString(alamatLengkap), convertPlainString(userId),  convertPlainString(userType));
    }





    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
