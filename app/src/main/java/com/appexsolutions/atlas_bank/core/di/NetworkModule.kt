package com.appexsolutions.atlas_bank.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @AtlasBankRetrofit
    fun provideRetrofit(): Retrofit{
        // TODO: Reemplaza esta URL con la de tu API real antes de ejecutar
        return Retrofit.Builder()
            .baseUrl("https://your-api-url.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}