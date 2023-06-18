package com.searchcountryapp.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.searchcountryapp.domain.CountryRepository
import com.searchcountryapp.domain.model.Country
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val repository: CountryRepository
): ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    init {
        print()
    }

    private fun loadCountries() {
        _countries.value = emptyList()
        viewModelScope.launch {
            repository.loadCountries()
        }
    }

    private fun getCountries() {
        repository.getFlowOfCountries().onEach {
            _countries.value = it
        }
    }

    fun print() {

    }
}