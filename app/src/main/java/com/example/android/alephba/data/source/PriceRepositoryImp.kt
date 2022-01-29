package com.example.android.alephba.data.source

import com.example.android.alephba.data.model.Error
import com.example.android.alephba.data.model.Response
import com.example.android.alephba.data.model.Success
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PriceRepositoryImp @Inject constructor(
    private val priceDataSource: PriceDataSource,
    private val localPriceDataSource: LocalPriceDataSource
) : PriceRepository {
    override suspend fun updateBitcoinPrice(): Response<Unit> {
        val res = priceDataSource.getBitcoinPrice()
        return if (res.isSuccess()) {
            localPriceDataSource.storeBitcoinPrice((res as Success).data)
            Success(Unit)
        } else {
            Error("")
        }

    }

    override suspend fun getBitcoinPrice(): Flow<String?> {
        return localPriceDataSource.getBitcoinPrice()
    }
}