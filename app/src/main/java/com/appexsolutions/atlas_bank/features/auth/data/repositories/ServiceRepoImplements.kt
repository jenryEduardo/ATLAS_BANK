package com.appexsolutions.atlas_bank.features.auth.data.repositories

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.api.AtlasBankAPI
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toDomain
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.BankResponse
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.LoginDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.RegisterDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.UserDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class ServiceRepoImplements @Inject constructor(
    private val api : AtlasBankAPI
): AtlasBanckRepository{
    override suspend fun getUser(id: Int): User {
        val response = api.GetDataUser(id)
        return response.toDomain()
    }

    override suspend fun login(user: LoginDTO): Response_userBank {
        val response = api.Login(user)
        return response.toDomain()
    }

    override suspend fun Register(user: RegisterDTO): Any {
        TODO("Not yet implemented")
    }


}