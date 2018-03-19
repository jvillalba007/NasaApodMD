package com.jvillalba.apod.dm.API.ApiService;

import java.util.List;

import com.jvillalba.apod.dm.model.NASA;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NASAServices {

        @GET("apod")
        Call<List<NASA>> getAPOD(@Query("api_key") String key, @Query("count") Integer count);
}
