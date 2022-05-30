package com.example.catest.api.response

import com.example.domain.ForecastInfo
import com.google.gson.annotations.SerializedName

data class ForecastInfoResponse(
    val dt: Long?,
    val main: MainInfoResponse?,
    val weather: List<WeatherInfoResponse>?,
    val clouds: CloudInfoResponse?,
    val wind: WindInfoResponse?,
    val visibility: Int?,
    val pop: Float?,
    val sys: SystemInfoResponse?,
    @SerializedName("dt_txt") val dtText: String?
) {
    fun toDomain() = ForecastInfo(
        dt,
        main?.toDomain(),
        weather?.map { it.toDomain() },
        clouds?.toDomain(),
        wind?.toDomain(),
        visibility,
        pop,
        sys?.toDomain(),
        dtText
    )
}
