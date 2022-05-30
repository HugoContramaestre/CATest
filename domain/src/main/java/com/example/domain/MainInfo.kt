package com.example.domain

data class MainInfo(
    val temp: Float?,
    val feelsLike: Float?,
    val tempMin: Float,
    val tempMax: Float,
    val pressure: Int?,
    val humidity: Int?
)
