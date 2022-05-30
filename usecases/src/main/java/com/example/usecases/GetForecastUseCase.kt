package com.example.usecases

import com.example.data.repository.MainRepository

class GetForecastUseCase(private val repository: MainRepository) {
    suspend fun invoke(cityName: String, numberOfDays: Int? = null) =
        repository.getForecast(
            cityName,
            numberOfDays
        )
}