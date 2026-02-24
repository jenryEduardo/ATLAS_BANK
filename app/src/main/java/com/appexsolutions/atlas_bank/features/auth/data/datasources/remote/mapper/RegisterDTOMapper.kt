package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.RegisterDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Register

fun Register.toRegisterRequest(): RegisterDTO{
    return RegisterDTO(
        name = this.name,
        date_of_birth = this.date_of_birth,
        email = this.email,
        password = this.password
    )
}