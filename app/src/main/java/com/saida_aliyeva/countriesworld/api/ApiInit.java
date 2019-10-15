package com.saida_aliyeva.countriesworld.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInit {

    Retrofit retrofit;
    ApiInterfaces apiInterface;

    public void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpLogging())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public OkHttpClient httpLogging() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder().addInterceptor(loggingInterceptor).build();
        return okHttpClient;


    }

    public ApiInterfaces getClient() {
        if (apiInterface == null) {
            apiInterface = retrofit.create(ApiInterfaces.class);
        }
        return apiInterface;
    }
}
