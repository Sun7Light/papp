package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.city.CityEntity
import retrofit2.Call
import retrofit2.http.*

interface CityApi {
    @GET("cities")
    fun getCities(@Header("access-token") token: String,
                  @Header("client") client:String,
                  @Header("uid") uid:String): Call<List<CityEntity>>

    @GET("cities/{id}")
    fun getCity(@Header("access-token") token: String,
                @Header("client") client:String,
                @Header("uid") uid:String,
                @Path("id") cityId:Int): Call<CityEntity>

    @POST("cities")
    fun createCity(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Body cityParams:CityParams): Call<CityEntity>

    @DELETE("cities/{id}")
    fun deleteCity(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Path("id") cityId:Int): Call<Any?>

    @PUT("cities/{id}")
    fun updateCity(@Header("access-token") token: String,
                   @Header("client") client:String,
                   @Header("uid") uid:String,
                   @Path("id") cityID:Int,
                   @Body cityParams: CityParams): Call<CityEntity>
}
data class CityParams(val name:String)