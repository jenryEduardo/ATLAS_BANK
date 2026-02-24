package com.appexsolutions.atlas_bank.features.auth.domain.entities

data class Response_userBank(
    val success : Boolean,
    val message : String?,
    val id_usuario : Int
)