package com.example.android.alephba.ui.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.android.alephba.PriceUpdaterWorker
import com.example.android.alephba.databinding.AlephbaWidgetConfigureBinding
import com.example.android.alephba.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * The configuration screen for the [AlephbaWidget] AppWidget.
 */
@AndroidEntryPoint
class AlephbaWidgetConfigureActivity : AppCompatActivity() {
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private lateinit var appWidgetText: EditText
    private var price = "Fetching"

    private val viewModel: MainViewModel by viewModels()

    private var onClickListener = View.OnClickListener {
        val context = this@AlephbaWidgetConfigureActivity


        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(context)
        updateAppWidget(context, appWidgetManager, appWidgetId, price)

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
    private lateinit var binding: AlephbaWidgetConfigureBinding

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        val test = OneTimeWorkRequestBuilder<PriceUpdaterWorker>().build()
        WorkManager.getInstance(this)
            .enqueue(
                test
            )

        viewModel.bitcoinPriceLiveData.observe(this) {
            Log.d("Babak", "Fuck")
            price = it
        }

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        binding = AlephbaWidgetConfigureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appWidgetText = binding.appwidgetText as EditText
        binding.addButton.setOnClickListener(onClickListener)

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }
    }

}