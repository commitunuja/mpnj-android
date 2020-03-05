    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerView;
    private RecycleAdapteHomeBanner mAdapter;
    private Integer image[] = {R.drawable.image95, R.drawable.image95, R.drawable.image95, R.drawable.image95};

    private ArrayList<HomeCategoryModelClass> homeCategoryModelClasses;
    private RecyclerView category_recyclerView;
    private RecycleAdapteHomeCategory mAdapter1;
    private String title[] = {"All Categories", "Mens", "Womens", "Electronics", "Home and Furniture", "Sports"};
//produk
    private ProdukAdapter produkAdapter;
    private List<Model> tvDataProduk;
    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private Integer image1[]={R.drawable.ac,R.drawable.headphones,R.drawable.ac,R.drawable.headphones};
    private String title1[] ={"Vigo Atom Personal Air Condi....","Bosh Head Phone Blue Color","Vigo Atom Personal Air Condi....","Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid","HeadPhones","Kitenid","HeadPhones"};
    private ArrayList<TopTenModelClass> topTenModelClasses1;
    private RecyclerView like_recyclerview;
    private RecycleAdapteTopTenHome mAdapter3;
    private Integer image2[]={R.drawable.mobile1,R.drawable.mobile2,R.drawable.mobile1,R.drawable.mobile2};
    private String title2[] ={"Samsung On Mask 2GB Ram","Samsung Galaxy 8 6GB Ram","Samsung On Mask 2GB Ram","Samsung Galaxy 8 6GB Ram"};
    private String type2[] = {"Phones","Phones","Phones","Phones"};

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        homeBannerModelClasses = new ArrayList<>();



        for (int i = 0; i < image.length; i++) {
            HomeBannerModelClass beanClassForRecyclerView_contacts = new HomeBannerModelClass(image[i]);

            homeBannerModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapteHomeBanner(Homepage.this, homeBannerModelClasses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        category_recyclerView = (RecyclerView)findViewById(R.id.category_recyclerview);

        homeCategoryModelClasses = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            HomeCategoryModelClass beanClassForRecyclerView_contacts = new HomeCategoryModelClass(title[i]);

            homeCategoryModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter1 = new RecycleAdapteHomeCategory(Homepage.this, homeCategoryModelClasses);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(Homepage.this, LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(mLayoutManager1);
