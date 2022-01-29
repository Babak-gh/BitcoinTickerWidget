package com.example.android.alephba.data.source.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PriceService {

    @GET("tobtc")
    suspend fun getUSDPriceInBitcoin(
        @Query("currency") currency: String,
        @Query("value") value: Int = 1
    ): Response<ResponseBody>
}