package com.example.android.alephba.data.source.remote

import com.example.android.alephba.data.source.PriceDataSource
import javax.inject.Inject

class RemotePriceDataSource @Inject constructor(
    private val priceService: PriceService
) : PriceDataSource {
    override suspend fun getBitcoinPrice() {
        TODO("Not yet implemented")
    }
}