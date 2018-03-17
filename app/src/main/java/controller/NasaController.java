package controller;

import android.support.annotation.NonNull;

import java.util.List;

import API.API;
import API.ApiService.NASAServices;
import adapter.MyAdapter;
import model.NASA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasaController {

    public void getNASAAPODS(final MyAdapter mAdapter)
    {
        NASAServices nasaServices = API.getApi().create(NASAServices.class);
        Call<List<NASA>> call = nasaServices.getAPOD(API.APPKEY,API.cantElementos);

        call.enqueue(new Callback<List<NASA>>() {
            @Override
            public void onResponse(@NonNull Call<List<NASA>> call, @NonNull Response<List<NASA>> response) {
                switch (response.code()) {
                    case 200:
                        List<NASA> nasaAPOD = response.body();
                        mAdapter.addAll(nasaAPOD);
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<NASA>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
    }

