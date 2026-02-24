package com.appexsolutions.atlas_bank.features.auth.domain.entities

data class Register(
    val name : String,
    val date_of_birth : String,
    val email : String,
    val password : String
)