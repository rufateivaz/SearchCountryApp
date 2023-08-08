package com.searchcountryapp.ui.country

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.domain.CountryRepository
import com.sample.domain.model.Country
import getOrAwaitValue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks

class CountriesViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CountryRepository

    @Before
    fun setup() {
        initMocks(this)
    }

    @Test
    fun whenViewModelIsInitialized_CountriesShouldBeLoadedAndCollectedThroughTheFlow() =
        runBlocking {
            // Given
            Mockito
                .`when`(repository.loadCountries())
                .thenReturn(Unit)
            Mockito
                .`when`(repository.getFlowOfCountries(""))
                .thenReturn(flowOf(listOf(country)))
            val countries = listOf(country)

            // When
            val viewModel = CountriesViewModel(repository)

            // Then
            Assert.assertEquals(viewModel.countries.getOrAwaitValue(), countries)
        }

    private val country = Country(
        "United States",
        329484123L,
        "Washington, D.C.",
        "FlagUrl",
        "MapUrl",
        "Americas",
        "North America"
    )
}