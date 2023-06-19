package com.sample.data.country.remote

import com.sample.data.country.remote.model.CountryDataModel

class CountryRemoteDataSource(
    private val api: CountryApi
) {
    suspend fun getCountries() : List<CountryDataModel> = api.getCountries()
}