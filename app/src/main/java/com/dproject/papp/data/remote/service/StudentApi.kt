package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.student.Sex
import com.dproject.papp.domain.student.StudentEntity
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface StudentApi {
    @GET("students")
    fun getStudentsForStudentGroup(@Header("access-token") token: String,
                                  @Header("client") client:String,
                                  @Header("uid") uid:String,
                                  @Query("filterrific[with_student_group_id]") studentGroupId: Int): Call<List<StudentEntity>>

    @GET("students")
    fun getStudents(@Header("access-token") token: String,
                         @Header("client") client:String,
                         @Header("uid") uid:String): Call<List<StudentEntity>>

    @GET("students/{id}")
    fun getStudent(@Header("access-token") token: String,
                        @Header("client") client:String,
                        @Header("uid") uid:String,
                        @Path("id") studentId:Int): Call<StudentEntity>

    @POST("students")
    fun createStudent(@Header("access-token") token: String,
                           @Header("client") client:String,
                           @Header("uid") uid:String,
                           @Body studentParams:StudentParams): Call<StudentEntity>

    @DELETE("students/{id}")
    fun deleteStudent(@Header("access-token") token: String,
                           @Header("client") client:String,
                           @Header("uid") uid:String,
                           @Path("id") studentId: Int): Call<Any?>

    @PUT("students/{id}")
    fun updateStudent(@Header("access-token") token: String,
                           @Header("client") client:String,
                           @Header("uid") uid:String,
                           @Path("id") studentId: Int,
                           @Body studentParams: StudentParams): Call<StudentEntity>
}

data class StudentParams(@SerializedName("student_group_id") val studentGroupId: Int, val name: String, val sex:Sex) {

}
