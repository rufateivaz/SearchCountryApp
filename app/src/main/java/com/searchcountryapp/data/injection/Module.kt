package com.searchcountryapp.data.injection

import com.searchcountryapp.data.country.injection.countryDataModule
import com.searchcountryapp.data.database.injection.databaseModule
import com.searchcountryapp.data.network.injection.networkModule

val dataModule = listOf (
    networkModule,
    countryDataModule,
    databaseModule
)