package com.example.android.alephba.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.work.*
import com.example.android.alephba.R
import com.example.android.alephba.data.PriceUpdaterWorker
import com.example.android.alephba.domain.PriceGetterUseCase
import com.example.android.alephba.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [AlephbaWidgetConfigureActivity]
 */
const val REFRESH_ACTION: String = "com.example.android.alephba.REFRESH_ACTION"


@AndroidEntryPoint
class AlephbaWidget : AppWidgetProvider() {

    @Inject
    lateinit var priceGetterUseCase: PriceGetterUseCase

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("Babak", "Widget")
        CoroutineScope(Dispatchers.Main).launch {
            val bitcoinPrice = priceGetterUseCase.invoke().firstOrNull()
            Log.d("Babak", bitcoinPrice.toString())
            // There may be multiple widgets active, so update all of them
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId, bitcoinPrice)
            }
        }

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent != null && intent.action != null) {
            if (intent.action?.equals(REFRESH_ACTION)!!) {
                val extras = intent.extras
                if (extras != null) {
                    val appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS)
                    if (appWidgetIds != null && appWidgetIds.isNotEmpty()) {
                        Log.d("Babak", "onReceive")
                        this.onUpdate(
                            context!!,
                            AppWidgetManager.getInstance(context),
                            appWidgetIds
                        );
                    }
                }
            }
        }
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        val worker =
            PeriodicWorkRequestBuilder<PriceUpdaterWorker>(15, TimeUnit.MINUTES).setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            ).build()
        WorkManager.getInstance(context!!)
            .enqueueUniquePeriodicWork(
                "priceUpdater", ExistingPeriodicWorkPolicy.KEEP,
                worker
            )
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    bitcoinPrice: String?
) {

    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        Intent(context, MainActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    var finalPrice =
        bitcoinPrice ?: "Fetching"

    val views = RemoteViews(context.packageName, R.layout.alephba_widget).apply {
        setOnClickPendingIntent(R.id.rootView, pendingIntent)
    }
    views.setTextViewText(R.id.appwidget_price, finalPrice)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}