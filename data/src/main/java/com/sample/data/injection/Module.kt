package com.sample.data.injection

import com.sample.data.country.injection.countryDataModule
import com.sample.data.database.injection.databaseModule
import com.sample.data.network.injection.networkModule

val dataModule = listOf (
    networkModule,
    countryDataModule,
    databaseModule
)