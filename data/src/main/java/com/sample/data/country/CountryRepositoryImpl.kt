package com.sample.data.country

import com.sample.data.country.extensions.toCountries
import com.sample.data.country.extensions.toCountryEntity
import com.sample.data.country.local.CountryLocalDataSource
import com.sample.data.country.remote.CountryRemoteDataSource
import com.sample.domain.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountryRepositoryImpl(
    private val local: CountryLocalDataSource,
    private val remote: CountryRemoteDataSource
) : CountryRepository {

    override suspend fun loadCountries() {
        val entityCountries = remote.getCountries().map { it.toCountryEntity() }
        local.saveCountries(entityCountries)
    }

    override fun getFlowOfCountries(content: String): Flow<List<com.sample.domain.model.Country>> =
        local.getCountries().map { countryEntities ->
            countryEntities.toCountries().filter {country ->
                country.name.contains(content, ignoreCase = true) ||
                country.capital.contains(content, ignoreCase = true) ||
                country.region.contains(content, ignoreCase = true) ||
                country.subRegion.contains(content, ignoreCase = true)
            }
        }
}