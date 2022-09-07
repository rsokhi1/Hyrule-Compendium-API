package com.example.a3_rajbeer_sokhi.network;

import com.example.a3_rajbeer_sokhi.models.CharacterResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    public final String BASE_URL = "https://botw-compendium.herokuapp.com/api/v2/";

    @GET("category/equipment")
    public Call<CharacterResponseObject> getAllData();
}
