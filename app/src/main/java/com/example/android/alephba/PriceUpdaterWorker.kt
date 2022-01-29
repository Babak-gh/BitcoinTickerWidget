package com.example.android.alephba

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.alephba.data.source.PriceRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PriceUpdaterWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val priceRepository: PriceRepository
) : CoroutineWorker(appContext, workerParams) {


    override suspend fun doWork(): Result {
        Log.d("Babak", "WorkManager")
        val res = priceRepository.updateBitcoinPrice()
        return if (res.isSuccess()) {
            val remoteViews = RemoteViews(appContext.packageName, R.layout.alephba_widget)
            AppWidgetManager.getInstance(appContext).updateAppWidget(
                ComponentName(
                    appContext,
                    AlephbaWidget::class.java
                ), remoteViews
            )
            Result.success()
        } else {
            Result.retry()
        }
    }

}