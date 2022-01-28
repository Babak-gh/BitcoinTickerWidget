package com.example.android.alephba.data.source

import javax.inject.Inject

class PriceRepositoryImp @Inject constructor(
    private val priceDataSource: PriceDataSource
) : PriceRepository {
    override suspend fun getBitcoinPrice() {
        TODO("Not yet implemented")
    }
}