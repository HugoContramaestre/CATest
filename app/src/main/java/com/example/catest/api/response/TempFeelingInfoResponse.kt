package com.example.catest.api.response

import com.example.domain.TempFeelingInfo

data class TempFeelingInfoResponse(
    val day: Float?,
    val night: Float?,
    val eve: Float?,
    val morn: Float?
) {
    fun toDomain() = TempFeelingInfo(
        day,
        night,
        eve,
        morn
    )
}
