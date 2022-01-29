package com.example.android.alephba.domain

import com.example.android.alephba.data.model.Response
import com.example.android.alephba.data.source.PriceRepository
import javax.inject.Inject

class PriceUpdaterUseCase @Inject constructor(
    private val repository: PriceRepository
) {

    suspend operator fun invoke(): Response<Unit> {
        return repository.updateBitcoinPrice()
    }

}