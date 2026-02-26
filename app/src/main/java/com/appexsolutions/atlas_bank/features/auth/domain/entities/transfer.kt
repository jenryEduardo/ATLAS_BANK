package com.appexsolutions.atlas_bank.features.auth.domain.entities

// domain/entities/TransferRequest.kt
data class TransferRequest(
    val cuentaOrigenId: String,
    val cuentaDestinoId: String,
    val monto: Float,
    val concepto: String
)

// domain/entities/TransferResponse.kt
data class TransferResponse(
    val success: Boolean,
    val mensaje: String
)