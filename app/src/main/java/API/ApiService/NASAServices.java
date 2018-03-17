package API.ApiService;

import java.util.List;

import model.NASA;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jvillalba on 16/03/18.
 */

public interface NASAServices {

        @GET("apod")
        Call<List<NASA>> getAPOD(@Query("api_key") String key, @Query("count") Integer count);
}
