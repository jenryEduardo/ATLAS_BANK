package com.appexsolutions.atlas_bank.features.auth.domain.repositories

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.BankResponse
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.LoginDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.RegisterDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.UserDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User

interface AtlasBanckRepository {
    suspend fun getUser(id:Int): User
    suspend fun login(user: LoginDTO): Response_userBank
    suspend fun Register(user: RegisterDTO): Any
}