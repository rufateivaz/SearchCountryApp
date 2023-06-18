package com.searchcountryapp.data.country.extensions

import com.searchcountryapp.data.country.local.model.CountryEntity
import com.searchcountryapp.data.country.remote.model.CountryDataModel
import com.searchcountryapp.domain.model.Country

fun CountryDataModel.toCountryEntity() = CountryEntity(
    flagUrl = flags.flagUrl,
    population = population,
    region = region,
    subRegion = subRegion,
    name = name.commonName,
    mapUrl = maps.googleMaps,
    capital = capitals[0]
)

fun CountryEntity.toCountry() = Country(
    flagUrl = flagUrl,
    population = population,
    region = region,
    subRegion = subRegion,
    name = name,
    mapUrl = mapUrl,
    capital = capital
)

fun List<CountryEntity>.toCountries() = map { it.toCountry() }