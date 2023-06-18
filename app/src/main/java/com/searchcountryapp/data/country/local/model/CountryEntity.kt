package com.searchcountryapp.data.country.local.model

import androidx.room.Entity

@Entity(
    tableName = "countries",
    primaryKeys = ["name"]
)
data class CountryEntity(
    val name: String,
    val population: Long,
    val capital: String,
    val flagUrl: String,
    val mapUrl: String,
    val region: String,
    val subRegion: String
)