package com.example.android.alephba

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
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


        val worker =
            PeriodicWorkRequestBuilder<PriceUpdaterWorker>(15, TimeUnit.MINUTES).setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            ).build()
        val test = OneTimeWorkRequestBuilder<PriceUpdaterWorker>().build()
        WorkManager.getInstance(this)
            .enqueue(
                test
            )
//        WorkManager.getInstance(this)
//            .enqueueUniquePeriodicWork(
//                "priceUpdater", ExistingPeriodicWorkPolicy.KEEP,
//                worker
//            )

    }
}