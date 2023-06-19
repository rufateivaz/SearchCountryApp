package com.sample.domain

import com.sample.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun loadCountries()

    fun getFlowOfCountries(content: String): Flow<List<Country>>
}