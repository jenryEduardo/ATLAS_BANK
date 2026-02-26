package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models

import com.google.gson.annotations.SerializedName

// data/models/TransferDTO.kt
data class TransferRequestDTO(
    @SerializedName("cuenta_origen_id") val cuentaOrigenId: String,
    @SerializedName("cuenta_destino_id") val cuentaDestinoId: String,
    val monto: Float,
    val concepto: String
)

data class TransferResponseDTO(
    val success: Boolean,
    val mensaje: String
)