package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: String,
    @SerializedName("cuenta_id")
    val cuentaId: String?,
    val name: String?,
    val wallet: Float,
    val card: Card?,
    @SerializedName("recently_inf")
    val recentlyInf: Recently_inf?
)

data class Card(
    val name_card: String?,
    @SerializedName("num_card")
    val num_card: String?,    // 👈 String, el JSON devuelve "1111" no número
    val expires: String?,
    val card_holder: String?
)

data class Recently_inf(
    val concept: String?,
    val type_inf: String?,
    val day_transfer: String?,
    val mount: Float?
)