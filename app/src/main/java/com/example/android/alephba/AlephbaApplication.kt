package com.example.android.alephba

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AlephbaApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.VERBOSE)
            .build()

    override fun onCreate() {
        super.onCreate()

        val test = OneTimeWorkRequestBuilder<PriceUpdaterWorker>().build()
        WorkManager.getInstance(this)
            .enqueue(
                test
            )


    }
}