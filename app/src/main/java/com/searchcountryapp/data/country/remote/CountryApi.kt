package com.searchcountryapp.data.country.remote

import com.searchcountryapp.BuildConfig
import com.searchcountryapp.data.country.remote.model.CountryResponse
import retrofit2.http.GET

interface CountryApi {
    @GET("${BuildConfig.API_URL}/all")
    suspend fun getCountries(): CountryResponse
}