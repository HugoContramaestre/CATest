package com.example.domain

data class ForecastInfoWrapper(
    val city: CityInfo?,
    val cod: String?,
    val message: Double?,
    val cnt: Int?,
    val list: List<ForecastInfo>?
)
