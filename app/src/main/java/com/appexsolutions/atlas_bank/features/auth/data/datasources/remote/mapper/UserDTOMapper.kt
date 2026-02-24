package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.mapper

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.UserDTO
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User

fun UserDTO.toDomain(): User {
    return User(
        name = this.name,
        wallet = this.wallet,
        name_card = this.card.name_card,
        num_card = this.card.num_card,
        expires = this.card.expires,
        card_holder = this.card.card_holder,
        concept = this.recentlyInf.concept,
        type_inf = this.recentlyInf.type_inf,
        day_transfer = this.recentlyInf.day_transfer,
        mount = this.recentlyInf.mount
    )
}