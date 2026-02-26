package com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.api

import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.BankResponse
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.LoginDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.RegisterDTO
import com.appexsolutions.atlas_bank.features.auth.data.datasources.remote.models.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AtlasBankAPI {

    @POST("auth/login")
    suspend fun Login(@Body body: LoginDTO): UserDTO

    @POST("auth/register")
    suspend fun Register(@Body body: RegisterDTO): BankResponse

    @GET("userBanckData/{id}")
    suspend fun GetDataUser(@Path("id") id: Int): UserDTO

}