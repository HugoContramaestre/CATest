package com.example.catest

import android.app.Application

class CaTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

}