package com.appexsolutions.atlas_bank.features.auth.domain.usescases

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper.toLoginRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository : AtlasBanckRepository
){
    suspend operator fun invoke(user : Login): Result<Response_userBank>{
        return try {
            val data = user.toLoginRequest()
            val login = repository.login(data)
            Result.success(login)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}