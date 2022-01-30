package com.example.android.alephba.data.source.remote

import com.example.android.alephba.data.model.*
import com.example.android.alephba.data.source.PriceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemotePriceDataSource @Inject constructor(
    private val priceService: PriceService
) : PriceDataSource {
    override suspend fun getBitcoinPrice(): Response<String> {
        return withContext(Dispatchers.IO) {
            try {
                val bitcoinPriceResponse = priceService.getUSDPriceInBitcoin("USD")
                if (bitcoinPriceResponse.isSuccessful) {
                    Success(bitcoinPriceResponse.body()?.string()!!)
                } else {
                    Error(ErrorModel(bitcoinPriceResponse.code(), bitcoinPriceResponse.message()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                NetworkError
            }


        }
    }
}