package com.appexsolutions.atlas_bank.features.auth.domain.usescases

import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: AtlasBanckRepository
){
    suspend operator fun invoke(id:Int): Result<User>{
        return try {
            val get = repository.getUser(id)
            Result.success(get)
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }
}