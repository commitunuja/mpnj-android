        vid_produk = getActivity().getIntent().getStringExtra("id_produk");
        String.valueOf(vid_produk);
//        namaproduk = getActivity().getIntent().getExtras().getString("nama_produk");
        urltoimage = getActivity().getIntent().getExtras().getString("foto_produk");

        Glide.with(getContext()).load(urltoimage).into(foto);
      /*  vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        vstok = Integer.parseInt(getIntent().getStringExtra("stok"));
        vterjual = Integer.parseInt(getIntent().getStringExtra("terjual"));
        vdeskripsi = getIntent().getExtras().getString("keterangan");*/
