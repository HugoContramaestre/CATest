package com.example.catest.api.response

import com.example.domain.CityGeo

data class CityGeoResponse(
    val lon: Float?,
    val lat: Float?
) {
    fun toDomain() = CityGeo(
        lon,
        lat
    )
}
