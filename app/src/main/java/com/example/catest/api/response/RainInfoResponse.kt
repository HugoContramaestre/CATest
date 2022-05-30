package com.example.catest.api.response

import com.example.domain.RainInfo
import com.google.gson.annotations.SerializedName

data class RainInfoResponse(
    @SerializedName("rain.1h") val rain1h: Int?,
    @SerializedName("rain.3h") val rain3h: Int?
) {
    fun toDomain() = RainInfo(
        rain1h,
        rain3h
    )
}
