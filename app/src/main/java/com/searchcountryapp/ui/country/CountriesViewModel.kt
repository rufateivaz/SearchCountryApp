package com.searchcountryapp.ui.country

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.searchcountryapp.domain.CountryRepository
import com.searchcountryapp.domain.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.io.IOException

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
            } catch (e: HttpException) {
                Log.d("Http", "Error while loading countries!")
            } catch (e: IOException) {
                Log.d("IOException", "Something went wrong!")
            }
        }
    }
}