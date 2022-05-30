package com.example.catest.api.response

import com.example.domain.TemperatureInfo

data class TemperatureInfoResponse(
    val day: Float?,
    val min: Float?,
    val max: Float?,
    val night: Float?,
    val eve: Float?,
    val morn: Float?
) {
    fun toDomain() = TemperatureInfo(
        day,
        min,
        max,
        night,
        eve,
        morn
    )
}
