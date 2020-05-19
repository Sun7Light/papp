package com.dproject.papp.data.remote.service

import com.dproject.papp.data.DataAccountEntity
import retrofit2.Call
import retrofit2.http.*

interface AccountApi {
    @POST("sign_up")
    fun registration(@Body registrationBody:RegistrationBody) : Call<DataAccountEntity>

    @POST("auth/sign_in")
    fun login(@Body loginBody: LoginBody) : Call<DataAccountEntity>

    @DELETE("auth/sign_out")
    fun logout(@Header("access-token") token: String, @Header("client") client:String, @Header("uid") uid:String) : Call<Any?>

    @GET("auth/validate_token")
    fun validateToken(@Header("access-token") token: String, @Header("client") client:String, @Header("uid") uid:String) : Call<Any?>
}
data class RegistrationBody(val login:String, val password: String, val token: String)
data class LoginBody(val login:String, val password: String)