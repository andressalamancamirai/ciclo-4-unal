package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getDogsByBreed(@Url url:String): Call<DogsResponse>
}