package com.appexsolutions.atlas_bank.features.auth.data.repositories

import android.util.Log
import com.appexsolutions.atlas_bank.core.session.SessionManager
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.api.AtlasBankAPI
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toDomain
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toLoginRequest
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toRecipient
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toRegisterRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Register
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferResponse
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class ServiceRepoImplements @Inject constructor(
    private val api: AtlasBankAPI,
    private val sessionManager: SessionManager
) : AtlasBanckRepository {

    override suspend fun getUser(): List<Recipient> {
        return api.GetDataUser().map { it.toRecipient() }
    }


    override suspend fun transfer(request: TransferRequest): TransferResponse {
        Log.d("test","nose data $request")
        return api.Transfer(request.toDTO()).toDomain()
    }

    override suspend fun login(user: Login): User {
        val dto = user.toLoginRequest()
        Log.d("REPO", "Enviando login: $dto")
        val response = api.Login(dto)
        Log.d("REPO", "Respuesta raw: id=${response.id}, name=${response.name}, wallet=${response.wallet}, card=${response.card}")
        val domainUser = response.toDomain()
        sessionManager.saveUserId(domainUser.id)
        if (domainUser.cuentaId.isNotEmpty()) {
            sessionManager.saveCuentaId(domainUser.cuentaId)
        }
        return domainUser
    }

    override suspend fun register(user: Register): Response_userBank {
        return api.Register(user.toRegisterRequest()).toDomain()
    }
}
