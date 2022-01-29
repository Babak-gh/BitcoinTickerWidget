package com.example.android.alephba.data.source

import kotlinx.coroutines.flow.Flow

interface LocalPriceDataSource {

    suspend fun storeBitcoinPrice(price: String)
    suspend fun getBitcoinPrice(): Flow<String?>

}