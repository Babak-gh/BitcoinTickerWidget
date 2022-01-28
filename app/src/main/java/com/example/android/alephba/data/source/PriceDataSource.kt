package com.example.android.alephba.data.source

interface PriceDataSource {

    suspend fun getBitcoinPrice()
}