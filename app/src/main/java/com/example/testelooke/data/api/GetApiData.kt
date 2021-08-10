package com.example.testelooke.data.api

import com.example.testelooke.data.model.Donuts
import retrofit2.http.GET

interface GetApiData {
    @GET("teste.json")
    suspend fun getDonuts(): Donuts

    companion object{
        val instance = ApiClient.retrofit?.create(GetApiData::class.java)!!
    }
}