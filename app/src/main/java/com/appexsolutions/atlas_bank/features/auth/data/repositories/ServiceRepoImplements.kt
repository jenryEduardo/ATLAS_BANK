package com.appexsolutions.atlas_bank.features.auth.data.repositories

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.api.AtlasBankAPI
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toDomain
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toLoginRequest
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toRegisterRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Register
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class ServiceRepoImplements @Inject constructor(
    private val api: AtlasBankAPI
) : AtlasBanckRepository {

    override suspend fun getUser(id: Int): User {
        return api.GetDataUser(id).toDomain()
    }

    override suspend fun login(user: Login): User {
        return api.Login(user.toLoginRequest()).toDomain()
    }

    override suspend fun register(user: Register): Response_userBank {
        return api.Register(user.toRegisterRequest()).toDomain()
    }
}
