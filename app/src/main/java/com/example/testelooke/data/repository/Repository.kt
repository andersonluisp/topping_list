package com.example.testelooke.data.repository

import com.example.testelooke.data.api.GetApiData

object Repository {

    private val api = GetApiData.instance

    suspend fun getDonuts() = api.getDonuts()

}