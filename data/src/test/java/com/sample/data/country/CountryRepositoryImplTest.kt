package com.sample.data.country

import com.sample.data.country.local.CountryLocalDataSource
import com.sample.data.country.local.model.CountryEntity
import com.sample.data.country.remote.CountryRemoteDataSource
import com.sample.data.country.remote.model.CountryDataModel
import com.sample.data.country.remote.model.Flags
import com.sample.data.country.remote.model.Maps
import com.sample.data.country.remote.model.Name
import com.sample.domain.model.Country
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class CountryRepositoryImplTest {

    @Mock
    private lateinit var localDataSource: CountryLocalDataSource

    @Mock
    private lateinit var remoteDataSource: CountryRemoteDataSource

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun whenLoadingCountriesVerifyThatLoaded() = runBlocking {
        // Given
        val dataModels = listOf(countryDataModel)
        `when`(remoteDataSource.getCountries()).thenReturn(dataModels)

        // When
        val countryRepositoryImpl = CountryRepositoryImpl(localDataSource, remoteDataSource)
        countryRepositoryImpl.loadCountries()

        // Then
        val entities = listOf(countryEntity)
        verify(localDataSource).saveCountries(entities)
    }

    @Test
    fun whenGettingCountriesByCountryNameGivenExistThenItShouldGetTheCountries() = runBlocking {
        // Given
        val entities = listOf(countryEntity)
        `when`(localDataSource.getCountries()).thenReturn(flowOf(entities))

        // When
        val countryRepositoryImpl = CountryRepositoryImpl(localDataSource, remoteDataSource)
        val actual = countryRepositoryImpl.getFlowOfCountries("United States").first()

        // Then
        assertEquals(listOf(country), actual)
    }

    @Test
    fun whenGettingCountriesByCountryNameGivenNotExistThenItShouldReturnEmptyList() = runBlocking {
        // Given
        val entities = listOf(countryEntity)
        `when`(localDataSource.getCountries()).thenReturn(flowOf(entities))

        // When
        val countryRepositoryImpl = CountryRepositoryImpl(localDataSource, remoteDataSource)
        val actual = countryRepositoryImpl.getFlowOfCountries("United Europe").first()

        // Then
        assertEquals(emptyList<Country>(), actual)
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

    private val countryEntity = CountryEntity(
        population = 100L,
        flagUrl = "flagUrl",
        capital = "Washington D.C",
        mapUrl = "googleMaps",
        region = "Region",
        subRegion = "SubRegion",
        name = "United States"
    )

    private val country = Country(
        population = 100L,
        flagUrl = "flagUrl",
        capital = "Washington D.C",
        mapUrl = "googleMaps",
        region = "Region",
        subRegion = "SubRegion",
        name = "United States"
    )
}