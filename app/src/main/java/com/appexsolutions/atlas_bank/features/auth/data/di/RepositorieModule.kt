package com.appexsolutions.atlas_bank.features.auth.data.di

import com.appexsolutions.atlas_bank.features.auth.data.repositories.ServiceRepoImplements
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBank(
        ServiceRepoImplements:ServiceRepoImplements
    ):AtlasBanckRepository
}