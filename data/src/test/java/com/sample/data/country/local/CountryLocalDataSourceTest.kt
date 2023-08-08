package com.sample.data.country.local

import com.sample.data.country.local.model.CountryEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class CountryLocalDataSourceTest {

    @Mock
    lateinit var dao: CountryDao

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun whenSavingCountriesItShouldSaveTheCountries() {
        // Given
        val expected = listOf(countryEntity)
        doNothing().`when`(dao).insertAll(expected)

        // When
        val countryLocalDataSource = CountryLocalDataSource(dao)
        countryLocalDataSource.saveCountries(expected)

        // Then
        verify(dao).insertAll(expected)
    }

    @Test
    fun whenGettingCountriesItShouldGetTheCountries() = runBlocking {
        // Given
        val expected = listOf(countryEntity)
        `when`(dao.getAll()).thenReturn(flowOf(expected))

        // When
        val countryLocalDataSource = CountryLocalDataSource(dao)
        val actual = countryLocalDataSource.getCountries().first()

        // Then
        assertEquals(actual, expected)
    }

    private val countryEntity = CountryEntity(
        "United States",
        329484123L,
        "Washington, D.C.",
        "FlagUrl",
        "MapUrl",
        "Americas",
        "North America"
    )
}