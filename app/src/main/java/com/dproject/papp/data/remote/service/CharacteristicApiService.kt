package com.dproject.papp.data.remote.service

import com.dproject.papp.data.DataCharacteristicEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacteristicApiService @Inject constructor(retrofit: Retrofit): CharacteristicApi {
    private val characteristicApi: CharacteristicApi by lazy {retrofit.create(CharacteristicApi::class.java)}

    override fun getCharacteristics(
        token: String,
        client: String,
        uid: String
    ): Call<List<DataCharacteristicEntity>> {
        return characteristicApi.getCharacteristics(token, client, uid)
    }

    override fun getCharacteristic(
        token: String,
        client: String,
        uid: String,
        characteristicId: Int
    ): Call<DataCharacteristicEntity> {
        return characteristicApi.getCharacteristic(token, client, uid, characteristicId)
    }

    override fun createCharacteristic(
        token: String,
        client: String,
        uid: String,
        characteristicParams: CharacteristicParams
    ): Call<DataCharacteristicEntity> {
        return characteristicApi.createCharacteristic(token, client, uid, characteristicParams)
    }

    override fun deleteCharacteristic(
        token: String,
        client: String,
        uid: String,
        characteristicId: Int
    ): Call<Any?> {
        return characteristicApi.deleteCharacteristic(token, client, uid, characteristicId)
    }

    override fun updateCharacteristic(
        token: String,
        client: String,
        uid: String,
        characteristicIdId: Int,
        characteristicParams: CharacteristicParams
    ): Call<DataCharacteristicEntity> {
        return characteristicApi.updateCharacteristic(token, client, uid, characteristicIdId, characteristicParams)
    }
}