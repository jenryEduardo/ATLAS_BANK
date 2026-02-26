package com.appexsolutions.atlas_bank.features.auth.domain.usescases

import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferResponse
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.repositories.AtlasBanckRepository
import javax.inject.Inject

// GetUsersUseCase.kt
class GetUsersUseCase @Inject constructor(
    private val repository: AtlasBanckRepository
) {
    suspend operator fun invoke(): Result<List<Recipient>> = try {
        Result.success(repository.getUser())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

// TransferUseCase.kt
class TransferUseCase @Inject constructor(
    private val repository: AtlasBanckRepository
) {
    suspend operator fun invoke(request: TransferRequest): Result<TransferResponse> = try {
        Result.success(repository.transfer(request))
    } catch (e: Exception) {
        Result.failure(e)
    }
}