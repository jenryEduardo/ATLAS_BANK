package com.appexsolutions.atlas_bank.features.auth.domain.repositories

import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Register
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User

interface AtlasBanckRepository {
    suspend fun getUser(id: Int): User
    suspend fun login(user: Login): Response_userBank
    suspend fun register(user: Register): Response_userBank
}
