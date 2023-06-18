package com.searchcountryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.searchcountryapp.data.country.local.CountryDao
import com.searchcountryapp.data.country.local.model.CountryEntity

@Database(
    entities = [CountryEntity::class],
    version = 1
)

abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao
}