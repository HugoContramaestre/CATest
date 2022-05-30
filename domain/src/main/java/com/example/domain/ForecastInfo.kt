package com.example.domain

data class ForecastInfo(
    val dt: Long?,
    val main: MainInfo?,
    val weather: List<WeatherInfo>?,
    val clouds: CloudInfo?,
    val wind: WindInfo?,
    val visibility: Int?,
    val pop: Float?,
    val sys: SystemInfo?,
    val dtText: String?
)
