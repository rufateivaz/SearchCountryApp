package com.sample.domain.model

data class Country(
    val name: String,
    val population: Long,
    val capital: String,
    val flagUrl: String,
    val mapUrl: String,
    val region: String,
    val subRegion: String
)