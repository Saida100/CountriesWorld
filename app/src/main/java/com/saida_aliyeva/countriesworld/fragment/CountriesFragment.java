package com.saida_aliyeva.countriesworld.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.saida_aliyeva.countriesworld.R;
import com.saida_aliyeva.countriesworld.adapter.RVAdapter;
import com.saida_aliyeva.countriesworld.Utils;


public class CountriesFragment extends Fragment {

    RecyclerView recyclerView;
    RVAdapter rvAdapter;
    private SearchView searchView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        setHasOptionsMenu(true);
        Utils.callRequest(recyclerView, getContext(), getActivity());
        //   callRequest();

    }

//    private void callRequest() {
//        ApiInit apiInit = new ApiInit();
//        apiInit.initRetrofit();
//        ApiInterfaces apiInterfaces = apiInit.getClient();
//        apiInterfaces.getCountries().enqueue(new Callback<List<Countries>>() {
//            @Override
//            public void onResponse(Call<List<Countries>> call, final Response<List<Countries>> response) {
//                List<Countries> countriesList = response.body();
//                Utils.saveTasksToSharedPrefs(getContext());
//                rvAdapter = new RVAdapter(getContext(), countriesList, new Listener() {
//                    @Override
//                    public void inItemClick(Countries countries) {
//                        putBundle(countries);
//                    }
//                });
//                recyclerView.setAdapter(rvAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Countries>> call, Throwable t) {
//                Log.e("error Message", t.getMessage());
//            }
//        });
//
//    }

//    public void putBundle(Countries countries) {
//        Bundle bundle = new Bundle();
//        try {
//            bundle.putString("countryName", countries.getName());
//            bundle.putString("capital", countries.getCapital());
//            bundle.putString("flag", countries.getFlag());
//            bundle.putString("callingCode", countries.getCallingCodes().get(0));
//            bundle.putString("region", countries.getRegion());
//            bundle.putString("subregion", countries.getSubregion());
//            bundle.putString("area", countries.getArea());
//            bundle.putString("domain", countries.getTopLevelDomain().get(0));
//            bundle.putString("lat", countries.getLatlng().get(0).toString());
//            bundle.putString("lng", countries.getLatlng().get(1).toString());
//            bundle.putString("currency", countries.getCurrencies().get(0).getName());
//            bundle.putString("symbol", countries.getCurrencies().get(0).getSymbol());
//            bundle.putString("langName", countries.getLanguages().get(0).getName());
//            bundle.putString("langNativeName", countries.getLanguages().get(0).getNativeName());
//            bundle.putString("alpha2Code", countries.getAlpha2Code());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(getActivity(), CountriesDataActivity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
//
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //  inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // change searchView icon color
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search_white_24dp));
        // change searchView textColor
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.WHITE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //   rvAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                rvAdapter.getFilter().filter(query);
                return false;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
        }
        return super.onOptionsItemSelected(item);
    }


}
