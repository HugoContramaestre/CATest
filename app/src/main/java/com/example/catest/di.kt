package com.example.catest

import android.app.Application
import com.example.catest.api.ApiClient
import com.example.catest.api.ApiRemoteDataSource
import com.example.catest.ui.SharedViewModel
import com.example.catest.ui.weather.WeatherFragment
import com.example.catest.ui.weather.WeatherViewModel
import com.example.catest.ui.webview.WebViewFragment
import com.example.catest.ui.webview.WebViewViewModel
import com.example.data.repository.MainRepository
import com.example.data.source.RemoteDataSource
import com.example.usecases.GetForecastUseCase
import com.example.usecases.GetWeatherReportUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun Application.initDI(){
    startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(this@initDI)
        modules(appModule, dataModule, scopesModule)
    }
}

private val appModule = module {
    factory<RemoteDataSource> { ApiRemoteDataSource(get()) }
    single(named("baseUrl")) { BuildConfig.BASE_URL }
    single { ApiClient(get(named("baseUrl"))).service }
}

private val dataModule = module {
    factory { MainRepository(get()) }
}

private val scopesModule = module {
    scope<WeatherFragment> {
        viewModel { WeatherViewModel(get(), get()) }
        scoped { GetWeatherReportUseCase(get()) }
        scoped { GetForecastUseCase(get()) }
    }
    scope<WebViewFragment> {
        viewModel { WebViewViewModel() }
    }

    viewModel { SharedViewModel() }
}