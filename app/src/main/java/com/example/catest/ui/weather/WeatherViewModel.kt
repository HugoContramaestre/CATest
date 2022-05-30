package com.example.catest.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.usecases.GetWeatherReportUseCase
import com.example.catest.ui.common.BaseViewModel
import com.example.catest.ui.common.Event
import com.example.catest.ui.common.FeedbackUser
import com.example.catest.ui.common.postValueEvent
import com.example.domain.CityWeatherWrapper
import com.example.domain.ForecastInfo
import com.example.usecases.GetForecastUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherReportUseCase: GetWeatherReportUseCase,
    private val getForecastUseCase: GetForecastUseCase
) : BaseViewModel() {

    private val _list = MutableLiveData<Event<List<ForecastInfo>>>()
    val list: LiveData<Event<List<ForecastInfo>>>
        get() {
            if (_list.value == null) {
                getForecast("Düsseldorf")
            }
            return _list
        }

    private val _cityWeather = MutableLiveData<CityWeatherWrapper>()
    val cityWeather: LiveData<CityWeatherWrapper>
        get() {
            if (_cityWeather.value == null) {
                getCityWeather("Düsseldorf")
            }
            return _cityWeather
        }
    private val _loader = MutableLiveData<Event<Boolean>>()
    val loader: LiveData<Event<Boolean>> = _loader

    private fun getForecast(cityName: String, numberOfDays: Int? = null) {
        viewModelScope.launch {
            _loader.postValueEvent(true)
            delay(500)
            getForecastUseCase.invoke(
                cityName = cityName,
                numberOfDays = numberOfDays
            ).fold({
                sendFeedbackUser(FeedbackUser.from(it))
            }, {
                if (it.list != null) {
                    _list.postValueEvent(it.list!!)
                }
            })
            _loader.postValueEvent(false)
        }
    }

    private fun getCityWeather(cityName: String) {
        viewModelScope.launch {
            _loader.postValueEvent(true)
            delay(500)
            getWeatherReportUseCase.invoke(cityName = cityName).fold(
                {
                    sendFeedbackUser(FeedbackUser.from(it))
                }, {
                    _cityWeather.postValue(it)
                }
            )
            _loader.postValueEvent(false)
        }
    }

}