package com.sample.data.database.injection

import androidx.room.Room
import com.sample.data.database.CountryDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CountryDatabase::class.java,
            "country_database"
        ).fallbackToDestructiveMigration().build()
    }
    single { get<CountryDatabase>().countryDao }
}