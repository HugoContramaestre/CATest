package com.example.data.source

import com.example.data.exception.Failure
import com.example.data.functional.Either
import com.example.domain.CityWeatherWrapper
import com.example.domain.ForecastInfoWrapper

interface RemoteDataSource {

    suspend fun getCityWeather(cityName: String): Either<Failure, CityWeatherWrapper>
    suspend fun getForecast(cityName: String, numberOfDays: Int? = null): Either<Failure, ForecastInfoWrapper>

}