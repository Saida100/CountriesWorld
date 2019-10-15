package com.saida_aliyeva.countriesworld.api;

import com.saida_aliyeva.countriesworld.model_class.Countries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaces {

    @GET("rest/v2/all")
    Call<List<Countries>> getCountries();

}
