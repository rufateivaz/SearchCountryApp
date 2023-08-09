package com.sample.data.country.remote

import com.sample.data.country.remote.model.CountryDataModel
import com.sample.data.country.remote.model.Flags
import com.sample.data.country.remote.model.Maps
import com.sample.data.country.remote.model.Name
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks

class CountryRemoteDataSourceTest {

    @Mock
    private lateinit var api: CountryApi

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun test() = runBlocking {
        // Given
        val dataModels = listOf(countryDataModel)
        Mockito.`when`(api.getCountries()).thenReturn(dataModels)

        // When
        val remoteDataSource = CountryRemoteDataSource(api)
        val actual = remoteDataSource.getCountries()

        // Then
        assertEquals(dataModels, actual)
    }

    private val countryDataModel = CountryDataModel(
        population = 100L,
        flags = Flags("flagUrl"),
        maps = Maps(googleMaps = "googleMaps"),
        name = Name(commonName = "United States"),
        capitals = listOf("Washington D.C"),
        region = "Region",
        subRegion = "SubRegion"
    )
}