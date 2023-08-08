package com.searchcountryapp.ui.country.injection

import com.searchcountryapp.ui.country.CountriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val countriesModule = module {

    viewModel {
        CountriesViewModel(repository = get())
    }
}

private val loadModules by lazy {
    loadKoinModules(
        listOf(countriesModule)
    )
}

fun injectCountriesModule() = loadModules