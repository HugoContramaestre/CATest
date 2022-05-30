package com.example.catest.api.response

import com.example.domain.SnowInfo
import com.google.gson.annotations.SerializedName

data class SnowInfoResponse(
    @SerializedName("snow.1h") val snow1h: Int?,
    @SerializedName("snow.3h") val snow3h: Int?
) {
    fun toDomain() = SnowInfo(
        snow1h,
        snow3h
    )
}
