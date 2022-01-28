package com.example.android.alephba.data.source

interface PriceRepository {
    suspend fun getBitcoinPrice()
}