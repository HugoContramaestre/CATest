package com.example.catest.api.response

import com.example.domain.WeatherInfo

data class WeatherInfoResponse(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
) {
    fun toDomain() = WeatherInfo(
        id ?: -1,
        main,
        description,
        icon
    )
}
