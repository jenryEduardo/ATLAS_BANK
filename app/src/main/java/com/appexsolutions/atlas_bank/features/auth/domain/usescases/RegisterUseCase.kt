package com.appexsolutions.atlas_bank.features.auth.domain.usescases

import com.appexsolutions.atlas_bank.features.auth.domain.entities.Register
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AtlasBanckRepository
) {
    suspend operator fun invoke(user: Register): Result<Response_userBank> {
        return try {
            Result.success(repository.register(user))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
