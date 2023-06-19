package com.sample.data.country.remote

import com.sample.data.country.remote.model.CountryDataModel
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun getCountries(): List<CountryDataModel>
}