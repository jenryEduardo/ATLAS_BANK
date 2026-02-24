package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models


data class BankResponse(
    val success : Boolean,
    val message : String?,
    val id_usuario : Int
)