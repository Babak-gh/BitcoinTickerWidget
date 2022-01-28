package com.example.android.alephba.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.alephba.data.source.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val priceRepository: PriceRepository
) : ViewModel() {

    fun getBitcoinPrice() {
        viewModelScope.launch {
            priceRepository.getBitcoinPrice()
        }
    }

}