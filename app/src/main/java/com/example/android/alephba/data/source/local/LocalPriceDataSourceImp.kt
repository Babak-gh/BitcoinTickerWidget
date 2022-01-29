package com.example.android.alephba.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android.alephba.data.source.LocalPriceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data")

class LocalPriceDataSourceImp @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : LocalPriceDataSource {

    private val bitcoinPricePreferenceKey = stringPreferencesKey(KEY_BITCOIN_PRICE)

    override suspend fun storeBitcoinPrice(price: String) {
        dataStore.edit { data ->
            data[bitcoinPricePreferenceKey] = price
        }
    }

    override suspend fun getBitcoinPrice(): Flow<String?> {
        return dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[bitcoinPricePreferenceKey]
            }
    }

    private companion object {
        const val KEY_BITCOIN_PRICE = "bitcoinPrice"
    }
}