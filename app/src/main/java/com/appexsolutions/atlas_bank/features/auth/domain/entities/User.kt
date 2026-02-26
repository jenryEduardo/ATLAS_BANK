package com.appexsolutions.atlas_bank.features.auth.domain.entities

data class User(
    val id: String,
    val cuentaId: String,
    val name: String,
    val wallet: Float,
    val name_card: String,
    val num_card: String,
    val expires: String,
    val card_holder: String,
    val concept: String,
    val type_inf: String,
    val day_transfer: String,
    val mount: Float
)