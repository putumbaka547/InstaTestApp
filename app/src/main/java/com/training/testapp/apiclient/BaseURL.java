package com.training.testapp.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseURL {

    private static final String BASE_URL = "https://api.instagram.com/";

    private static Retrofit getBaseUrl() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static InstaAPI getAPI() {
        return BaseURL.getBaseUrl().create(InstaAPI.class);
    }
}
