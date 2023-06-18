package com.searchcountryapp.data.country.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.searchcountryapp.data.country.local.model.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countryEntities: List<CountryEntity>)

    @Query("SELECT * FROM countries")
    fun getAll() : Flow<List<CountryEntity>>
}