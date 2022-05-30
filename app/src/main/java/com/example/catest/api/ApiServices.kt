package com.example.catest.api

import com.example.catest.api.response.CityWeatherWrapperResponse
import com.example.catest.api.response.ForecastInfoWrapperResponse
import com.example.domain.CityWeatherWrapper
import com.example.domain.ForecastInfoWrapper
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
    ): NetworkResponse<CityWeatherWrapperResponse, ErrorResult>

    @GET("forecast")
    suspend fun getCityDailyForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("cnt") days: Int? = null
    ): NetworkResponse<ForecastInfoWrapperResponse, ErrorResult>
}