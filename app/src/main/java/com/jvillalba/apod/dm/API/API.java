package com.jvillalba.apod.dm.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static final String BASE_URL = "https://api.nasa.gov/planetary/";
    private static Retrofit retrofit=null;
    public static final String APPKEY =  "ykEnnTgMAnkzZJJOIPMZSN9QmsIDFsNrEBAnvNwB";
    public static final int cantElements =  29;

    public static Retrofit getApi()
    {
        if(retrofit == null)
        {
                retrofit =  new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }

        return retrofit;
    }

}
