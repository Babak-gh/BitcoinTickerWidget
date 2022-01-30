package com.example.android.alephba.ui

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.alephba.R
import com.example.android.alephba.databinding.ActivityMainBinding
import com.example.android.alephba.ui.widget.AlephbaWidget
import dagger.hilt.android.AndroidEntryPoint





@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.onViewCreated()

        viewModel.bitcoinPriceLiveData.observe(this) {
            binding.bitcoinPrice.text = it

            val remoteViews = RemoteViews(this.packageName, R.layout.alephba_widget)
            AppWidgetManager.getInstance(this).updateAppWidget(
                ComponentName(
                    this,
                    AlephbaWidget::class.java
                ), remoteViews
            )
        }

        viewModel.viewStateLiveData.observe(this) { it ->
            when (it) {
                is Loading -> {
                    binding.contentProgressBar.visibility = View.VISIBLE
                }
                is Data -> {
                    binding.contentProgressBar.visibility = View.GONE
                }
                is Error -> {
                    binding.contentProgressBar.visibility = View.GONE
                    binding.bitcoinPrice.text = it.error
                }

            }
        }

    }
}