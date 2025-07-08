package com.openstudy.carefull.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarefullApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}