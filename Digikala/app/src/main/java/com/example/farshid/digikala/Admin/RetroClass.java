package com.example.farshid.digikala.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    private static final String BASE_PATH = "http://learnshipp.ir/farshid/diji/";

    private static Retrofit getRetroInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(BASE_PATH).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static ApiService getApiService() {
        return getRetroInstance().create(ApiService.class);
    }

}
