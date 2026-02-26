package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.TransferRequestDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.TransferResponseDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferRequest
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferResponse

// data/mapper/TransferMapper.kt
fun TransferRequest.toDTO() = TransferRequestDTO(
    cuentaOrigenId = this.cuentaOrigenId,
    cuentaDestinoId = this.cuentaDestinoId,
    monto = this.monto,
    concepto = this.concepto
)

fun TransferResponseDTO.toDomain() = TransferResponse(
    success = this.success,
    mensaje = this.mensaje
)