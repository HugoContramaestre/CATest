package com.example.catest.api.response

import com.example.domain.ForecastInfoWrapper

data class ForecastInfoWrapperResponse(
    val city: CityInfoResponse?,
    val cod: String?,
    val message: Double?,
    val cnt: Int?,
    val list: List<ForecastInfoResponse>?
) {
    fun toDomain() = ForecastInfoWrapper(
        city?.toDomain(),
        cod,
        message,
        cnt,
        list?.map { it.toDomain() }
    )
}
