package com.appexsolutions.atlas_bank.features.auth.domain.usescases

import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AtlasBanckRepository
) {
    suspend operator fun invoke(user: Login): Result<User> {
        return try {
            Result.success(repository.login(user))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
