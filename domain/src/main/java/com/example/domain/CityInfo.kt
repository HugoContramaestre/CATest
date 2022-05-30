package com.example.domain

data class CityInfo(
    val id: Int,
    val name: String?,
    val coord: CityGeo?,
    val country: String?,
    val population: Long?,
    val timezone: Long?
)
