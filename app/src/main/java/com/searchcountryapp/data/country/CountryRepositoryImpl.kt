package com.searchcountryapp.data.country

import com.searchcountryapp.data.country.extensions.toCountries
import com.searchcountryapp.data.country.extensions.toCountryEntity
import com.searchcountryapp.data.country.local.CountryLocalDataSource
import com.searchcountryapp.data.country.remote.CountryRemoteDataSource
import com.searchcountryapp.domain.CountryRepository
import com.searchcountryapp.domain.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CountryRepositoryImpl(
    private val local: CountryLocalDataSource,
    private val remote: CountryRemoteDataSource
) : CountryRepository {

    override suspend fun loadCountries() {
        withContext(Dispatchers.IO) {
            val response = remote.getCountries()
            val entityCountries = response.countriesDataModel.map { it.toCountryEntity() }
            local.saveCountries(entityCountries)
        }
    }

    override fun getFlowOfCountries(): Flow<List<Country>> =
        local.getCountries().map { it.toCountries() }
}