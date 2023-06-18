package com.searchcountryapp.data.country.remote

import com.searchcountryapp.data.country.remote.model.CountryDataModel

class CountryRemoteDataSource(
    private val api: CountryApi
) {
    suspend fun getCountries() : List<CountryDataModel> = api.getCountries()
}