package com.example.android.alephba.data.source

import com.example.android.alephba.data.model.Response

interface PriceDataSource {

    suspend fun getBitcoinPrice(): Response<String>
}