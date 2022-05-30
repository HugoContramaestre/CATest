package com.example.usecases

import com.example.data.repository.MainRepository

class GetWeatherReportUseCase(private val repository: MainRepository) {
    suspend fun invoke(cityName: String) = repository.getCityWeather(cityName)
}