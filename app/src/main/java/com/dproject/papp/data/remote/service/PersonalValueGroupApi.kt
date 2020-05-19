package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PersonalValueGroupApi {
    @GET("personal_value_groups")
    fun getPersonalValueGroups(@Header("access-token") token: String,
                          @Header("client") client:String,
                          @Header("uid") uid:String): Call<List<PersonalValueGroupEntity>>

    @GET("personal_value_groups/{id}")
    fun getPersonalValueGroup(@Header("access-token") token: String,
                              @Header("client") client:String,
                              @Header("uid") uid:String,
                              @Path("id") personalValueGroupId:Int): Call<PersonalValueGroupEntity>
}