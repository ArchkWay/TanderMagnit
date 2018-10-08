package com.example.archek.tandermagnit.net;

import com.example.archek.tandermagnit.model.ObjectResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface TanderService {

    @GET("allStations.json")
        Call<ObjectResponse> getData();

    @GET("allStations.json")
    Call<ObjectResponse> searchData(@Query("stationTitle") String searchBody);
}
