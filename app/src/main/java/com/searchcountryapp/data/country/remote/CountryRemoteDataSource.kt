package com.searchcountryapp.data.country.remote

import com.searchcountryapp.data.country.remote.model.CountryResponse

class CountryRemoteDataSource(
    private val api: CountryApi
) {
    suspend fun getCountries() : CountryResponse = api.getCountries()
}