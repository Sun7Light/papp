package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.school.SchoolEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolApiService @Inject constructor(retrofit: Retrofit): SchoolApi {
    private val schoolApi: SchoolApi by lazy {retrofit.create(SchoolApi::class.java)}

    override fun getSchoolsForCity(
        token: String,
        client: String,
        uid: String,
        cityId: Int
    ): Call<List<SchoolEntity>> {
        return schoolApi.getSchoolsForCity(token, client, uid, cityId)
    }


    override fun getSchools(token: String, client: String, uid: String): Call<List<SchoolEntity>> {
        return schoolApi.getSchools(token, client, uid)
    }

    override fun getSchool(
        token: String,
        client: String,
        uid: String,
        schoolId: Int
    ): Call<SchoolEntity> {
        return schoolApi.getSchool(token, client, uid, schoolId)
    }

    override fun createSchool(
        token: String,
        client: String,
        uid: String,
        schoolParams: SchoolParams
    ): Call<SchoolEntity> {
        return schoolApi.createSchool(token, client, uid, schoolParams)
    }

    override fun deleteSchool(
        token: String,
        client: String,
        uid: String,
        schoolId: Int
    ): Call<Any?> {
        return schoolApi.deleteSchool(token, client, uid, schoolId)
    }

    override fun updateSchool(
        token: String,
        client: String,
        uid: String,
        schoolId: Int,
        schoolParams: SchoolParams
    ): Call<SchoolEntity> {
        return schoolApi.updateSchool(token, client, uid, schoolId, schoolParams)
    }
}