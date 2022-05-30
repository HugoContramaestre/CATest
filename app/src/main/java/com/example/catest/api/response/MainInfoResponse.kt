package com.example.catest.api.response

import com.example.domain.MainInfo
import com.google.gson.annotations.SerializedName

data class MainInfoResponse(
    val temp: Float?,
    @SerializedName("feels_like") val feelsLike: Float?,
    @SerializedName("temp_min") val tempMin: Float?,
    @SerializedName("temp_max") val tempMax: Float?,
    val pressure: Int?,
    val humidity: Int?
) {
    fun toDomain() = MainInfo(
        temp,
        feelsLike,
        getCelsius(tempMin),
        getCelsius(tempMax),
        pressure,
        humidity
    )

    private fun getCelsius(temp: Float?): Float {
        return if (temp != null) {
            (temp - 273.15F)
        }
        else {
            0F
        }
    }
}
