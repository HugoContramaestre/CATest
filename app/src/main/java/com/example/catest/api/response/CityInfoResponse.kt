package com.example.catest.api.response

import com.example.domain.CityInfo

data class CityInfoResponse(
    val id: Int?,
    val name: String?,
    val coord: CityGeoResponse?,
    val country: String?,
    val population: Long?,
    val timezone: Long?
) {
    fun toDomain() = CityInfo(
        id ?: -1,
        name,
        coord?.toDomain(),
        country,
        population,
        timezone
    )
}
