package com.appexsolutions.atlas_bank.features.auth.domain.entities

data class User(
    val name : String,
    val wallet : Float,
    val name_card : String,
    val num_card : Long,
    val expires : String,
    val card_holder : String,
    val concept : String,
    val type_inf : String, //se refiere a que si recive o envia dinero si recive es + y si envia -
    val day_transfer : String,
    val mount : Float
)
