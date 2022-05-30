package com.example.data.repository

import com.example.data.source.RemoteDataSource

class MainRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCityWeather(cityName: String) =
        remoteDataSource.getCityWeather(cityName)

    suspend fun getForecast(cityName: String, numberOfDays: Int? = null) =
        remoteDataSource.getForecast(
            cityName,
            numberOfDays
        )
}