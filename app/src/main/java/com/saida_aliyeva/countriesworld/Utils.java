package com.saida_aliyeva.countriesworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.saida_aliyeva.countriesworld.activity.CountriesDataActivity;
import com.saida_aliyeva.countriesworld.activity.QuizByCapitalActivity;
import com.saida_aliyeva.countriesworld.adapter.RVAdapter;
import com.saida_aliyeva.countriesworld.api.ApiInit;
import com.saida_aliyeva.countriesworld.api.ApiInterfaces;
import com.saida_aliyeva.countriesworld.model_class.Countries;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {

    static RVAdapter rvAdapter;
    static List<Countries> countriesList;

    public static void loadImage(String url, String alpha2Code, String ext, ImageView imageView) {
        Picasso.get().load(url + alpha2Code + ext).into(imageView);
    }

    public static void callRequest(final RecyclerView recyclerView, final Context context, final Activity activity) {
        ApiInit apiInit = new ApiInit();
        apiInit.initRetrofit();
        ApiInterfaces apiInterfaces = apiInit.getClient();
        apiInterfaces.getCountries().enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, final Response<List<Countries>> response) {
                countriesList = response.body();
                saveTasksToSharedPrefs(context);

                rvAdapter = new RVAdapter(context, countriesList, new Listener() {
                    @Override
                    public void inItemClick(Countries countries) {
                        Bundle bundle = putBundle(countries, activity);
                        Intent intent = new Intent(activity, CountriesDataActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);

                    }
                });
                recyclerView.setAdapter(rvAdapter);
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                Log.e("error Message", t.getMessage());
            }
        });

    }

    public static Bundle putBundle(Countries countries, Activity activity) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("countryName", countries.getName());
            bundle.putString("capital", countries.getCapital());
            bundle.putString("flag", countries.getFlag());
            bundle.putString("callingCode", countries.getCallingCodes().get(0));
            bundle.putString("region", countries.getRegion());
            bundle.putString("subregion", countries.getSubregion());
            bundle.putString("area", countries.getArea());
            bundle.putString("domain", countries.getTopLevelDomain().get(0));
            bundle.putString("lat", countries.getLatlng().get(0).toString());
            bundle.putString("lng", countries.getLatlng().get(1).toString());
            bundle.putString("currency", countries.getCurrencies().get(0).getName());
            bundle.putString("symbol", countries.getCurrencies().get(0).getSymbol());
            bundle.putString("langName", countries.getLanguages().get(0).getName());
            bundle.putString("langNativeName", countries.getLanguages().get(0).getNativeName());
            bundle.putString("alpha2Code", countries.getAlpha2Code());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bundle;

    }

    public static void saveTasksToSharedPrefs(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(countriesList); //countries is an ArrayList instance variable
        prefsEditor.putString("currentTasks", json);
        prefsEditor.commit();
    }


    public static void setBundle(String key, String value, Activity first, Context context) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        Intent intent = new Intent(first, QuizByCapitalActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
