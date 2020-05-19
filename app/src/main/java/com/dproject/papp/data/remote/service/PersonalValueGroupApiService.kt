package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonalValueGroupApiService @Inject constructor(retrofit: Retrofit): PersonalValueGroupApi {
    private val personalValueGroupsApi: PersonalValueGroupApi by lazy {retrofit.create(PersonalValueGroupApi::class.java)}

    override fun getPersonalValueGroups(
        token: String,
        client: String,
        uid: String
    ): Call<List<PersonalValueGroupEntity>> {
        return personalValueGroupsApi.getPersonalValueGroups(token, client, uid)
    }

    override fun getPersonalValueGroup(
        token: String,
        client: String,
        uid: String,
        personalValueGroupId: Int
    ): Call<PersonalValueGroupEntity> {
        return personalValueGroupsApi.getPersonalValueGroup(token, client, uid, personalValueGroupId)
    }

}