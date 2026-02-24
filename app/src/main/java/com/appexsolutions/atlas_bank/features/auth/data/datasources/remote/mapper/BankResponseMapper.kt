package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.BankResponse
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Response_userBank

fun BankResponse.toDomain(): Response_userBank{
    return Response_userBank(
        success = this.success,
        message = this.message,
        id_usuario = this.id_usuario
    )
}