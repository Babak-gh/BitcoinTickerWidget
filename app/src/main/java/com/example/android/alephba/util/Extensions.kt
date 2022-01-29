package com.example.android.alephba.util

fun String.convertUSDPriceToBitcoin(): String? {
    val usdPriceInDouble = this.toDoubleOrNull()
    return usdPriceInDouble?.let {
        val bitcoinPrice = 1 / it
        String.format("%.2f", bitcoinPrice)
    }
}