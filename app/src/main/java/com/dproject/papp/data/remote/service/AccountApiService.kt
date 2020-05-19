package com.dproject.papp.data.remote.service

import com.dproject.papp.data.DataAccountEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountApiService @Inject constructor(retrofit: Retrofit): AccountApi {
    private val accountApi: AccountApi by lazy {retrofit.create(AccountApi::class.java)}

    override fun registration(registrationBody: RegistrationBody)
            = accountApi.registration(registrationBody)

    override fun login(loginBody: LoginBody): Call<DataAccountEntity> {
        return accountApi.login(loginBody)
    }

    override fun logout(token: String, client: String, uid: String): Call<Any?> {
        return accountApi.logout(token, client, uid)
    }

    override fun validateToken(token: String, client: String, uid: String): Call<Any?> {
        return accountApi.validateToken(token, client, uid)
    }
}