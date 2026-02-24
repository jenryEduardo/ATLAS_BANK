package com.appexsolutions.atlas_bank.features.auth.data.di

import com.appexsolutions.atlas_bank.core.di.AtlasBankRetrofit
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.api.AtlasBankAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AtlasBankModule{
    @Provides
    @Singleton
    fun provideAtlasBlanKAPI(@AtlasBankRetrofit retrofit: Retrofit): AtlasBankAPI{
        return retrofit.create(AtlasBankAPI::class.java)
    }
}