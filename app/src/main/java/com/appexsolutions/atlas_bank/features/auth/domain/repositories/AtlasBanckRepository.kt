package com.appexsolutions.atlas_bank.features.auth.domain.repositories

import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Register
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferResponse
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User

// AtlasBanckRepository.kt
interface AtlasBanckRepository {
    suspend fun getUser(): List<Recipient>
    suspend fun transfer(request: TransferRequest): TransferResponse
    suspend fun login(user: Login): User
    suspend fun register(user: Register): Response_userBank
    suspend fun refreshDashboard(email: String, password: String): User // 👈 agregar
}
