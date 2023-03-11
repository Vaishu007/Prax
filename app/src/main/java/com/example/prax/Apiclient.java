package com.example.prax;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {
    public static final String Baseurl="https://praxwaresnv.000webhostapp.com/";
    public static Retrofit praxware() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
