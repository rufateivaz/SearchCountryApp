package com.searchcountryapp.ui.country

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.searchcountryapp.domain.CountryRepository
import com.searchcountryapp.domain.model.Country
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CountriesViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    init {
        Log.d("HELL0!", "ViewModel was created successfully!")
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
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