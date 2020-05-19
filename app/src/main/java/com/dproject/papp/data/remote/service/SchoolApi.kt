package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.school.SchoolEntity
import retrofit2.Call
import retrofit2.http.*

interface SchoolApi {

    @GET("schools")
    fun getSchoolsForCity(@Header("access-token") token: String,
                          @Header("client") client:String,
                          @Header("uid") uid:String,
                          @Query("filterrific[with_city_id]") cityId: Int): Call<List<SchoolEntity>>

    @GET("schools")
    fun getSchools(@Header("access-token") token: String,
                  @Header("client") client:String,
                  @Header("uid") uid:String): Call<List<SchoolEntity>>

    @GET("schools/{id}")
    fun getSchool(@Header("access-token") token: String,
                @Header("client") client:String,
                @Header("uid") uid:String,
                @Path("id") schoolId:Int): Call<SchoolEntity>

    @POST("schools")
    fun createSchool(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Body schoolParams:SchoolParams): Call<SchoolEntity>

    @DELETE("schools/{id}")
    fun deleteSchool(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Path("id") schoolId:Int): Call<Any?>

    @PUT("schools/{id}")
    fun updateSchool(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Path("id") schoolId:Int,
                   @Body schoolParams: SchoolParams): Call<SchoolEntity>
}

data class SchoolParams(val name:String, val city_id: Int) {

}
