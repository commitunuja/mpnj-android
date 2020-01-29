public interface APIKeranjang {
    @POST("/api/keranjang")
    @FormUrlEncoded
    Call<Model> simpanKeranjang(
            @Field("produk_id") String produk_id,
            @Field("pembeli_id") String pembeli_id,
            @Field("pembeli_type") String pembeli_type,
            @Field("jumlah") String jumlah,
            @Field("harga_jual") String harga_jual
    );
}
