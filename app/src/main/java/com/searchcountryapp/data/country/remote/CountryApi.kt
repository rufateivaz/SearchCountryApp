package com.searchcountryapp.data.country.remote

import com.searchcountryapp.data.country.remote.model.CountryDataModel
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    suspend fun getCountries(): List<CountryDataModel>
}