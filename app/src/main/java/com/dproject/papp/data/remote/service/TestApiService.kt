package com.dproject.papp.data.remote.service

import com.dproject.papp.domain.test.TestEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestApiService @Inject constructor(retrofit: Retrofit): TestApi{
    private val testApi: TestApi by lazy {retrofit.create(TestApi::class.java)}

    override fun getActiveTests(
        token: String,
        client: String,
        uid: String,
        activeId: Int
    ): Call<List<TestEntity>> {
        return testApi.getActiveTests(token, client, uid, activeId)
    }

    override fun getActiveTestsForStudent(
        token: String,
        client: String,
        uid: String,
        studentId: Int
    ): Call<List<TestEntity>> {
        return testApi.getActiveTestsForStudent(token, client, uid, studentId)
    }

    override fun getTestsForStudentGroup(
        token: String,
        client: String,
        uid: String,
        studentGroupId: Int
    ): Call<List<TestEntity>> {
        return testApi.getTestsForStudentGroup(token, client, uid, studentGroupId)
    }

    override fun getTest(
        token: String,
        client: String,
        uid: String,
        testId: Int
    ): Call<TestEntity> {
        return testApi.getTest(token, client, uid, testId)
    }

    override fun createTest(
        token: String,
        client: String,
        uid: String,
        testParams: TestParams
    ): Call<TestEntity> {
        return testApi.createTest(token, client, uid, testParams)
    }

    override fun deleteTest(token: String, client: String, uid: String, testId: Int): Call<Any?> {
        return testApi.deleteTest(token, client, uid, testId)
    }

    override fun updateTest(
        token: String,
        client: String,
        uid: String,
        testId: Int,
        testParams: TestParams
    ): Call<TestEntity> {
        return testApi.updateTest(token, client, uid, testId, testParams)
    }
}