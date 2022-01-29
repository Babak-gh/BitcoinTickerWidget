package com.example.android.alephba.domain

import com.example.android.alephba.data.source.PriceRepository
import com.example.android.alephba.util.convertUSDPriceToBitcoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PriceGetterUseCase @Inject constructor(
    private val repository: PriceRepository
) {

    suspend operator fun invoke(): Flow<String?> {
        return repository.getBitcoinPrice().map { usdBitcoinPrice ->
            usdBitcoinPrice?.convertUSDPriceToBitcoin()
        }
    }

}