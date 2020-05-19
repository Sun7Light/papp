package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.student.StudentEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentApiService @Inject constructor(retrofit: Retrofit): StudentApi {
    private val studentApi: StudentApi by lazy {retrofit.create(StudentApi::class.java)}
    override fun getStudentsForStudentGroup(
        token: String,
        client: String,
        uid: String,
        studentGroupId: Int
    ): Call<List<StudentEntity>> {
        return studentApi.getStudentsForStudentGroup(token, client, uid, studentGroupId)
    }

    override fun getStudents(
        token: String,
        client: String,
        uid: String
    ): Call<List<StudentEntity>> {
        return studentApi.getStudents(token, client, uid)
    }

    override fun getStudent(
        token: String,
        client: String,
        uid: String,
        studentId: Int
    ): Call<StudentEntity> {
        return studentApi.getStudent(token, client, uid, studentId)
    }

    override fun createStudent(
        token: String,
        client: String,
        uid: String,
        studentParams: StudentParams
    ): Call<StudentEntity> {
        return studentApi.createStudent(token, client, uid, studentParams)
    }

    override fun deleteStudent(
        token: String,
        client: String,
        uid: String,
        studentId: Int
    ): Call<Any?> {
        return studentApi.deleteStudent(token, client, uid, studentId)
    }

    override fun updateStudent(
        token: String,
        client: String,
        uid: String,
        studentId: Int,
        studentParams: StudentParams
    ): Call<StudentEntity> {
        return studentApi.updateStudent(token, client, uid, studentId, studentParams)
    }


}