package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.studentGroup.StudentGroupEntity
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface StudentGroupApi {
    @GET("student_groups")
    fun getStudentGroupsForSchool(@Header("access-token") token: String,
                          @Header("client") client:String,
                          @Header("uid") uid:String,
                          @Query("filterrific[with_school_id]") schoolId: Int): Call<List<StudentGroupEntity>>

    @GET("student_groups")
    fun getStudentGroups(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String): Call<List<StudentGroupEntity>>

    @GET("student_groups/{id}")
    fun getStudentGroup(@Header("access-token") token: String,
                  @Header("client") client:String,
                  @Header("uid") uid:String,
                  @Path("id") studentGroupId:Int): Call<StudentGroupEntity>

    @POST("student_groups")
    fun createStudentGroup(@Header("access-token") token: String,
                     @Header("client") client:String,
                     @Header("uid") uid:String,
                     @Body studentGroupParams:StudentGroupParams): Call<StudentGroupEntity>

    @DELETE("student_groups/{id}")
    fun deleteStudentGroup(@Header("access-token") token: String,
                     @Header("client") client:String,
                     @Header("uid") uid:String,
                     @Path("id") studentGroupId: Int): Call<Any?>

    @PUT("student_groups/{id}")
    fun updateStudentGroup(@Header("access-token") token: String,
                     @Header("client") client:String,
                     @Header("uid") uid:String,
                     @Path("id") studentGroupId: Int,
                     @Body studentGroupParams: StudentGroupParams): Call<StudentGroupEntity>
}

data class StudentGroupParams(@SerializedName("school_id") val schoolId: Int, val letter:String,@SerializedName("form_date") val formDate: String) {

}
