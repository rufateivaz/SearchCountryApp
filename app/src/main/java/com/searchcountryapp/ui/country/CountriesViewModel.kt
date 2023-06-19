package com.searchcountryapp.ui.country

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.domain.CountryRepository
import com.sample.domain.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.Exception

class CountriesViewModel : ViewModel(), KoinComponent {

    private val repository by inject<CountryRepository>()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    init {
        getFlowOfCountries()
        loadCountries()
    }

    fun getFlowOfCountries(content: String = "") {
        viewModelScope.launch {
            repository.getFlowOfCountries(content).collect {
                _countries.value = it
            }
        }
    }

    private fun loadCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.loadCountries()
            } catch (e: Exception) {
                Log.d("Exception!","Error while loading countries!")
            }
        }
    }
}