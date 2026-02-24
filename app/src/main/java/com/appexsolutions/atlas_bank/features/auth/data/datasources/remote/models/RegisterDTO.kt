package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models

data class RegisterDTO(
    val name : String,
    val date_of_birth : String,
    val email : String,
    val password : String
)