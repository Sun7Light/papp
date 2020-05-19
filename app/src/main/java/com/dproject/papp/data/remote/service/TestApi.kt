package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.test.TestEntity
import retrofit2.Call
import retrofit2.http.*

interface TestApi {

    @GET("tests")
    fun getActiveTests(@Header("access-token") token: String,
                                 @Header("client") client:String,
                                 @Header("uid") uid:String,
                                 @Query("filterrific[active]") activeId: Int): Call<List<TestEntity>>

    @GET("tests")
    fun getActiveTestsForStudent(@Header("access-token") token: String,
                                @Header("client") client:String,
                                @Header("uid") uid:String,
                                @Query("filterrific[with_student_id]") studentId: Int): Call<List<TestEntity>>
    @GET("tests")
    fun getTestsForStudentGroup(@Header("access-token") token: String,
                                   @Header("client") client:String,
                                   @Header("uid") uid:String,
                                   @Query("filterrific[with_student_group_id]") studentGroupId: Int): Call<List<TestEntity>>

    @GET("tests/{id}")
    fun getTest(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Path("id") testId:Int): Call<TestEntity>

    @POST("tests")
    fun createTest(@Header("access-token") token: String,
                      @Header("client") client:String,
                      @Header("uid") uid:String,
                      @Body testParams:TestParams): Call<TestEntity>

    @DELETE("tests/{id}")
    fun deleteTest(@Header("access-token") token: String,
                      @Header("client") client:String,
                      @Header("uid") uid:String,
                      @Path("id") testId: Int): Call<Any?>

    @PUT("tests/{id}")
    fun updateTest(@Header("access-token") token: String,
                      @Header("client") client:String,
                      @Header("uid") uid:String,
                      @Path("id") testId: Int,
                      @Body testParams: TestParams): Call<TestEntity>
}

data class TestParams(val student_group_id: Int, val start: String, val finish:String) {

}
