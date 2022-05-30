package com.example.domain

data class CityWeatherWrapper(
    val coord: CityGeo?,
    val weather: List<WeatherInfo>?,
    val base: String?,
    val main: MainInfo?,
    val visibility: Int?,
    val wind: WindInfo?,
    val clouds: CloudInfo?,
    val rain: RainInfo?,
    val snow: SnowInfo?,
    val dt: Long?,
    val sys: SystemInfo?,
    val timezone: Long?,
    val id: String?,
    val name: String?,
    val cod: Int?
) {
}