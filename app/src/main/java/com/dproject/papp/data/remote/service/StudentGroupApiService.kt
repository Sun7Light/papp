package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.studentGroup.StudentGroupEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentGroupApiService @Inject constructor(retrofit: Retrofit): StudentGroupApi {
    private val studentGroupApi: StudentGroupApi by lazy {retrofit.create(StudentGroupApi::class.java)}

    override fun getStudentGroupsForSchool(
        token: String,
        client: String,
        uid: String,
        schoolId: Int
    ): Call<List<StudentGroupEntity>> {
        return studentGroupApi.getStudentGroupsForSchool(token, client, uid, schoolId)
    }

    override fun getStudentGroups(
        token: String,
        client: String,
        uid: String
    ): Call<List<StudentGroupEntity>> {
        return studentGroupApi.getStudentGroups(token,client,uid)
    }

    override fun getStudentGroup(
        token: String,
        client: String,
        uid: String,
        studentGroupId: Int
    ): Call<StudentGroupEntity> {
        return studentGroupApi.getStudentGroup(token, client, uid, studentGroupId)
    }

    override fun createStudentGroup(
        token: String,
        client: String,
        uid: String,
        studentGroupParams: StudentGroupParams
    ): Call<StudentGroupEntity> {
        return studentGroupApi.createStudentGroup(token, client, uid, studentGroupParams)
    }

    override fun deleteStudentGroup(
        token: String,
        client: String,
        uid: String,
        studentGroupId: Int
    ): Call<Any?> {
        return studentGroupApi.deleteStudentGroup(token, client, uid, studentGroupId)
    }

    override fun updateStudentGroup(
        token: String,
        client: String,
        uid: String,
        studentGroupId: Int,
        studentGroupParams: StudentGroupParams
    ): Call<StudentGroupEntity> {
        return studentGroupApi.updateStudentGroup(token, client, uid, studentGroupId, studentGroupParams)
    }
}