package com.searchcountryapp.ui.country.injection

import com.searchcountryapp.domain.CountryRepository
import com.searchcountryapp.ui.country.CountriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        CountriesViewModel(repository = get())
    }
}

private val loadUIModules by lazy {
    loadKoinModules(
        listOf(
            uiModule
        )
    )
}

fun injectUIFeatures() = loadUIModules