package com.example.catest.api.response

import com.example.domain.CityWeatherWrapper

data class CityWeatherWrapperResponse(
    val coord: CityGeoResponse?,
    val weather: List<WeatherInfoResponse>?,
    val base: String?,
    val main: MainInfoResponse?,
    val visibility: Int?,
    val wind: WindInfoResponse?,
    val clouds: CloudInfoResponse?,
    val rain: RainInfoResponse?,
    val snow: SnowInfoResponse?,
    val dt: Long?,
    val sys: SystemInfoResponse?,
    val timezone: Long?,
    val id: String?,
    val name: String?,
    val cod: Int?
) {
    fun toDomain() = CityWeatherWrapper(
        coord?.toDomain(),
        weather?.map { it.toDomain() },
        base,
        main?.toDomain(),
        visibility,
        wind?.toDomain(),
        clouds?.toDomain(),
        rain?.toDomain(),
        snow?.toDomain(),
        dt,
        sys?.toDomain(),
        timezone,
        id,
        name,
        cod
    )
}