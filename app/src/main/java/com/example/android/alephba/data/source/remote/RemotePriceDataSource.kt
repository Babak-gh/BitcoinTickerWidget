package com.example.android.alephba.data.source.remote

import com.example.android.alephba.data.source.PriceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemotePriceDataSource @Inject constructor(
    private val priceService: PriceService
) : PriceDataSource {
    override suspend fun getBitcoinPrice() {
        return withContext(Dispatchers.IO) {
            priceService.getUSDPriceInBitcoin("USD")
        }
    }
}