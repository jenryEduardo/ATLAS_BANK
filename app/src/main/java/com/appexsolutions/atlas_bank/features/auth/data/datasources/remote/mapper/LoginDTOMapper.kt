package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.LoginDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login

fun Login.toLoginRequest(): LoginDTO{
    return LoginDTO(
        email = this.email,
        password = this.password
    )
}