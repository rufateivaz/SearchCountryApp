package com.searchcountryapp.data.country.local

import com.searchcountryapp.data.country.local.model.CountryEntity
import kotlinx.coroutines.flow.Flow

class CountryLocalDataSource(
    private val dao: CountryDao
) {
    fun saveCountries(countryEntities: List<CountryEntity>) = dao.insertAll(countryEntities)

    fun getCountries() : Flow<List<CountryEntity>> = dao.getAll()
}