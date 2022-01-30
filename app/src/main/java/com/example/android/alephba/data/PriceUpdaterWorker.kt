package com.example.android.alephba.data

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.alephba.data.source.PriceRepository
import com.example.android.alephba.ui.widget.AlephbaWidget
import com.example.android.alephba.ui.widget.REFRESH_ACTION
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
            Log.d("Babak", "After WorkManager")
            val intent = Intent(appContext, AlephbaWidget::class.java)
            intent.action = REFRESH_ACTION
            val ids = AppWidgetManager.getInstance(appContext).getAppWidgetIds(
                ComponentName(
                    appContext,
                    AlephbaWidget::class.java
                )
            )
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            appContext.sendBroadcast(intent)
            Result.success()
        } else {
            Result.retry()
        }
    }

}