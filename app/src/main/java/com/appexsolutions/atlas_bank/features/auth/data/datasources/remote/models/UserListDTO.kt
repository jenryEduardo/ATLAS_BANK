package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models

import com.google.gson.annotations.SerializedName

// data/models/UserListDTO.kt
data class UserListDTO(
    val id: String,
    val nombre: String?,
    @SerializedName("apellido_paterno") val apellidoPaterno: String?,
    val email: String?
)