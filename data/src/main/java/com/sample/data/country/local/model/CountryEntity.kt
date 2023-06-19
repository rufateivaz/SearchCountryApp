package com.sample.data.country.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val population: Long,
    val capital: String,
    val flagUrl: String,
    val mapUrl: String,
    val region: String,
    val subRegion: String
)