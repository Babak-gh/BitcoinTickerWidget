package com.example.android.alephba.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.android.alephba.data.source.PriceRepository
import com.example.android.alephba.domain.PriceGetterUseCase
import com.example.android.alephba.domain.PriceUpdaterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PriceUpdaterModule {

    @Provides
    fun providePriceGetterUseCase(priceRepository: PriceRepository): PriceGetterUseCase =
        PriceGetterUseCase(priceRepository)

    @Provides
    fun providePriceUpdaterUseCase(priceRepository: PriceRepository): PriceUpdaterUseCase =
        PriceUpdaterUseCase(priceRepository)

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile(name = "data")
            }
        )
}