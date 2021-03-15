package com.sholeh.marketplacenj.activities.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.dashboard.SearchAdapter;
import com.sholeh.marketplacenj.mfragment.homepage.HomepageFragment;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class SearchActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    private MaterialSearchBar openSearch;
    RecyclerView recyclerViewsearch;
    HomepageFragment homepageFragment;
    private SearchAdapter searchAdapter;
    private SearchAdapter kategoriAdapter;
    private List<Model> datapencarian;
    private List<String> lastSearches;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        recyclerViewsearch = findViewById(R.id.recycler_search);
        openSearch = findViewById(R.id.searchBar2);
        openSearch.openSearch();
        openSearch.setSpeechMode(true);
        openSearch.getLastSuggestions();
        openSearch.setOnSearchActionListener(this);
//        lastSearches = loadSearchSuggestionFromDisk();
//        openSearch.setLastSuggestions(datapencarian);

    }

    //    private void fiturpencarian() {
//
//        openSearch.inflateMenu(R.menu.main);
//        searchBar.setText("Hello Zainal!");
//        Log.d("LOG_TAG", getClass().getSimpleName() + ": text " + openSearch.getText());
//        openSearch.setCardViewElevation(10);
//        openSearch.addTextChangeListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + openSearch.getText());
//
////                searchFragment = new SearchFragment();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                recyclerViewsearch.setVisibility(View.VISIBLE);
//
//
//            }
//
//        });
//    }


    @Override
    protected void onResume() {
        super.onResume();
        openSearch.getLastSuggestions();
    }

    private void produksearch(String text) {
//        namaprodukpencarian = String.valueOf(searchBar);
        searchAdapter = new SearchAdapter(getContext(), datapencarian);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewsearch.setLayoutManager(layoutManager);
        recyclerViewsearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewsearch.setNestedScrollingEnabled(false);
        recyclerViewsearch.setFocusableInTouchMode(false);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Model>> call = service.getAllData(text);
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.isSuccessful()) {
                    datapencarian = response.body();
                    searchAdapter = new SearchAdapter(getContext(), datapencarian);
                    recyclerViewsearch.setAdapter(searchAdapter);


                } else {
//                    Log.e(TAG,"response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {
        finish();

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        produksearch(openSearch.getText());
//        Toast.makeText(this, ""+openSearch.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                openSearch.closeSearch();

        }
    }
}
