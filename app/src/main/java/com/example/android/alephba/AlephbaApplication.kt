package com.example.android.alephba

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlephbaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}