package com.example.android.alephba.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.alephba.domain.PriceGetterUseCase
import com.example.android.alephba.domain.PriceUpdaterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val priceGetterUseCase: PriceGetterUseCase,
    private val priceUpdaterUseCase: PriceUpdaterUseCase
) : ViewModel() {

    private val _bitcoinPriceLiveData = MutableLiveData<String>()
    val bitcoinPriceLiveData: LiveData<String> = _bitcoinPriceLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState<Unit>>()
    val viewStateLiveData: LiveData<ViewState<Unit>> = _viewStateLiveData

    fun onViewCreated() {
        updateBitcoinPrice()
        getBitcoinPrice()
    }

    private fun updateBitcoinPrice() {
        viewModelScope.launch {
            _viewStateLiveData.value = Loading
            val res = priceUpdaterUseCase.invoke()
            if (res.isSuccess()) {
                _viewStateLiveData.value = Data(Unit)
            } else {
                _viewStateLiveData.value = Error("Error")
            }

        }
    }


    private fun getBitcoinPrice() {
        viewModelScope.launch {
            priceGetterUseCase.invoke().collect {
                if (it == null) {
                    _bitcoinPriceLiveData.value = "Fetching"
                } else {
                    _bitcoinPriceLiveData.value = it
                }
            }

        }
    }

}