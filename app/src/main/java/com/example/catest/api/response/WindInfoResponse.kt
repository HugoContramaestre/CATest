package com.example.catest.api.response

import com.example.domain.WindInfo

data class WindInfoResponse(
    val speed: Float?,
    val deg: Int?
) {
    fun toDomain() = WindInfo(
        speed,
        deg
    )
}
