package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.city.CityEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityApiService @Inject constructor(retrofit: Retrofit): CityApi {
    private val cityApi: CityApi by lazy {retrofit.create(CityApi::class.java)}

    override fun getCities(
        token: String,
        client: String,
        uid: String
    ): Call<List<CityEntity>> {
        return cityApi.getCities(token, client, uid)
    }

    override fun getCity(
        token: String,
        client: String,
        uid: String,
        cityId: Int
    ): Call<CityEntity> {
        return cityApi.getCity(token,client,uid,cityId)
    }

    override fun createCity(
        token: String,
        client: String,
        uid: String,
        cityParams: CityParams
    ): Call<CityEntity> {
        return cityApi.createCity(token, client, uid, cityParams)
    }

    override fun deleteCity(token: String, client: String, uid: String, cityId: Int): Call<Any?> {
        return cityApi.deleteCity(token, client, uid, cityId)
    }

    override fun updateCity(
        token: String,
        client: String,
        uid: String,
        cityID: Int,
        cityParams: CityParams
    ): Call<CityEntity> {
        return cityApi.updateCity(token,client,uid,cityID,cityParams)
    }
}