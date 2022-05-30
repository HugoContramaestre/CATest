package com.example.catest.api

import com.example.catest.BuildConfig
import com.example.data.exception.Failure
import com.example.data.functional.Either
import com.example.data.source.RemoteDataSource
import com.example.domain.CityWeatherWrapper
import com.example.domain.ForecastInfoWrapper
import com.haroldadmin.cnradapter.NetworkResponse

class ApiRemoteDataSource(private val api: ApiServices): RemoteDataSource {
    override suspend fun getCityWeather(cityName: String): Either<Failure, CityWeatherWrapper> {
        return api.getCityWeather(
            cityName,
            BuildConfig.API_KEY
        ).wrapperResponse {
            it.toDomain()
        }
    }

    override suspend fun getForecast(cityName: String, numberOfDays: Int?): Either<Failure, ForecastInfoWrapper> {
        return api.getCityDailyForecast(
            cityName,
            BuildConfig.API_KEY,
            numberOfDays
        ).wrapperResponse {
            it.toDomain()
        }
    }

}

inline fun <In : Any, Out : Any> NetworkResponse<In, ErrorResult>.wrapperResponse(
    transform: ((In) -> Out)
): Either<Failure, Out> {
    return when (this) {
        is NetworkResponse.Success -> Either.Right(transform.invoke(body))
        is NetworkResponse.ServerError -> {
            error?.printStackTrace()
            Either.Left(
                FailureFactory().handleApiCode(
                    code,
                    body
                )
            )
        }
        is NetworkResponse.NetworkError -> {
            error.printStackTrace()
            Either.Left(Failure.NetworkConnection)
        }
        is NetworkResponse.UnknownError -> {
            error.printStackTrace()
            Either.Left(Failure.UnknownApiError)
        }
    }
}

fun <In : Any> NetworkResponse<In, ErrorResult>.wrapperResponseEmpty(): Failure? {
    return when (this) {
        is NetworkResponse.Success -> null
        is NetworkResponse.ServerError ->
            FailureFactory().handleApiCode(
                code,
                body
            )
        else -> Failure.NetworkConnection
    }
}