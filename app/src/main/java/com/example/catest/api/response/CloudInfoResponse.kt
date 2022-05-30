package com.example.catest.api.response

import com.example.domain.CloudInfo

data class CloudInfoResponse(
    val all: Int?
) {
    fun toDomain() = CloudInfo(
        all
    )
}
