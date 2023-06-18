package com.searchcountryapp.data.country.remote.model

import com.google.gson.annotations.SerializedName

data class CountryDataModel(
    @SerializedName(value = "population") val population: Long,
    @SerializedName(value = "flags") val flags: Flags,
    @SerializedName(value = "maps") val maps: Maps,
    @SerializedName(value = "name") val name: Name,
    @SerializedName(value = "capital") val capitals: List<String>?,
    @SerializedName(value = "region") val region: String,
    @SerializedName(value = "subregion") val subRegion: String?
)

data class Name(
    @SerializedName(value = "common") val commonName: String,
)

data class Maps(
    @SerializedName(value = "googleMaps") val googleMaps: String
)

data class Flags(
    @SerializedName(value = "png") val flagUrl: String
)
