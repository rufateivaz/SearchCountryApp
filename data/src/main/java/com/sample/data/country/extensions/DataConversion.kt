package com.sample.data.country.extensions

import com.sample.data.country.local.model.CountryEntity
import com.sample.data.country.remote.model.CountryDataModel

fun CountryDataModel.toCountryEntity() = CountryEntity(
    flagUrl = flags.flagUrl,
    population = population,
    region = region,
    subRegion = subRegion ?: "",
    name = name.commonName,
    mapUrl = maps.googleMaps,
    capital = capitals?.get(0) ?: ""
)

fun CountryEntity.toCountry() = com.sample.domain.model.Country(
    flagUrl = flagUrl,
    population = population,
    region = region,
    subRegion = subRegion,
    name = name,
    mapUrl = mapUrl,
    capital = capital
)

fun List<CountryEntity>.toCountries() = map { it.toCountry() }