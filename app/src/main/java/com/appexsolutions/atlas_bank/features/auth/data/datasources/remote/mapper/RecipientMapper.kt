package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.UserListDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient

// data/mapper/RecipientMapper.kt
fun UserListDTO.toRecipient(): Recipient = Recipient(
    id = this.id,
    name = "${this.nombre ?: ""} ${this.apellidoPaterno ?: ""}".trim(),
    email = this.email ?: ""
)