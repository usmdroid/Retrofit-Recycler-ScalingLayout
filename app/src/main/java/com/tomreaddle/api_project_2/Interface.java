package com.tomreaddle.api_project_2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {

    String BASE_URL = "https://usmano8102.000webhostapp.com/";

    @GET("/demos/marvel/json.json")
    Call<List<HeroModel>> getData();
}
