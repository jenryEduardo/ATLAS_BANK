package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models

data class UserDTO(
    val name: String,
    val wallet: Float,
    val card: Card,
    val recentlyInf: Recently_inf
)

data class Card(
    val name_card : String,
    val num_card : Long,
    val expires : String,
    val card_holder : String
)

data class Recently_inf(
    val concept : String,
    val type_inf : String, //se refiere a que si recive o envia dinero si recive es + y si envia -
    val day_transfer : String,
    val mount : Float
)