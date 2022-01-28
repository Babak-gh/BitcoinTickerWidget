package com.example.android.alephba.di

import com.example.android.alephba.data.source.PriceDataSource
import com.example.android.alephba.data.source.PriceRepository
import com.example.android.alephba.data.source.PriceRepositoryImp
import com.example.android.alephba.data.source.remote.RemotePriceDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PriceModule {

    @Binds
    abstract fun bindPriceRemoteDataSource(
        priceRemoteDataSourceImp: RemotePriceDataSource
    ): PriceDataSource

    @Binds
    abstract fun bindAuthRepository(
        priceRepositoryImp: PriceRepositoryImp
    ): PriceRepository

}