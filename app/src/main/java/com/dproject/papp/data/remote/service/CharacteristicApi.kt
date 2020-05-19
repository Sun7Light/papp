package com.dproject.papp.data.remote.service

import com.dproject.papp.data.DataCharacteristicEntity
import retrofit2.Call
import retrofit2.http.*

interface CharacteristicApi {
    @GET("characteristics")
    fun getCharacteristics(@Header("access-token") token: String,
                           @Header("client") client:String,
                           @Header("uid") uid:String): Call<List<DataCharacteristicEntity>>

    @GET("characteristics/{id}")
    fun getCharacteristic(@Header("access-token") token: String,
                          @Header("client") client:String,
                          @Header("uid") uid:String,
                          @Path("id") characteristicId:Int): Call<DataCharacteristicEntity>

    @POST("characteristics")
    fun createCharacteristic(@Header("access-token") token: String,
                             @Header("client") client:String,
                             @Header("uid") uid:String,
                             @Body characteristicParams:CharacteristicParams): Call<DataCharacteristicEntity>

    @DELETE("characteristics/{id}")
    fun deleteCharacteristic(@Header("access-token") token: String,
                             @Header("client") client:String,
                             @Header("uid") uid:String,
                             @Path("id") characteristicId: Int): Call<Any?>

    @PUT("characteristics/{id}")
    fun updateCharacteristic(@Header("access-token") token: String,
                             @Header("client") client:String,
                             @Header("uid") uid:String,
                             @Path("id") characteristicIdId:Int,
                             @Body characteristicParams: CharacteristicParams): Call<DataCharacteristicEntity>
}

data class CharacteristicParams(val name:String, val positivity:Int, val personal_value_group_id:Int) {

}