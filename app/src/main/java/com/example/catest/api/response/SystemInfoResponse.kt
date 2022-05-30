package com.example.catest.api.response

import com.example.domain.SystemInfo

data class SystemInfoResponse(
    val type: Int?,
    val id: Int?,
    val message: Double?,
    val country: String?,
    val sunrise: Long?,
    val sunset: Long?,
    val pod: String?
) {
    fun toDomain() = SystemInfo(
        type,
        id ?: -1,
        message,
        country,
        sunrise,
        sunset,
        pod
    )
}
