package com.example.catest.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.catest.R
import com.example.catest.databinding.FragmentWeatherBinding
import com.example.catest.ui.common.BaseFragment
import com.example.catest.ui.common.EventObserver
import com.example.catest.ui.common.extensions.applyDefaultSkeleton
import com.example.catest.ui.common.extensions.loadUrl
import com.example.catest.ui.common.extensions.setDate
import com.example.catest.ui.common.extensions.toggle
import com.faltenreich.skeletonlayout.Skeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModel()
    private val adapter = ForecastAdapter()
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelObservers(viewModel)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        with(binding) {
            list.adapter = adapter
            skeleton = skeletonList.applyDefaultSkeleton(R.layout.item_forecast_day)
        }
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        viewModel.cityWeather.observe(viewLifecycleOwner, Observer { weatherData ->
            with(binding) {
                weatherData.dt?.let { dt ->
                    cityWeatherCityDate.setDate(dt)
                }
                cityWeatherCityName.text = weatherData.name
                weatherData.weather?.let { weatherList ->
                    if (weatherList.isNotEmpty()) {
                        cityWeatherCityWeatherType.text = weatherList.first().description
                        cityWeatherImage.loadUrl(weatherList.first().icon)
                    }
                }
                weatherData.main?.let { weather ->
                    cityWeatherCityTemperature.text = resources.getString(
                        R.string.common_min_max_temp,
                        weather.tempMin,
                        weather.tempMax
                    )
                }
            }
        })
        viewModel.list.observe(viewLifecycleOwner, EventObserver {
            adapter.submitList(it)
        })
        viewModel.loader.observe(viewLifecycleOwner, EventObserver {
            skeleton.toggle(it)
        })
    }
}