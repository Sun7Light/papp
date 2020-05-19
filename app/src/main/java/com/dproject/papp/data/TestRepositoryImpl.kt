package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.TestApiService
import com.dproject.papp.data.remote.service.TestParams
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.studentGroup.StudentGroupRepository
import com.dproject.papp.domain.test.TestEntity
import com.dproject.papp.domain.test.TestRepository

class TestRepositoryImpl(private val studentGroupRepository: StudentGroupRepository, private val testApiService: TestApiService, private val request: Request, private val accountCache: AccountCache): TestRepository {
    override fun getTestsForSchool(schoolId: Int): Either<Failure, List<TestEntity>> {
        return studentGroupRepository.getStudentGroupForSchool(schoolId)
            .flatMap { (it.map {getTestForStudentGroup(it.id)})
            }


        return Either.Left(Failure.ServerError)
    }

    override fun addTest(testEntity: TestEntity): Either<Failure, TestEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(testApiService.createTest(it.accessToken, it.client, it.uid, TestParams(testEntity.studentGroupId, testEntity.start, testEntity.finish)), TestEntity.empty(), {it}) }
    }

    override fun getTestForStudentGroup(studentGroupId: Int): Either<Failure, List<TestEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(testApiService.getTestsForStudentGroup(it.accessToken, it.client, it.uid, studentGroupId), emptyList(), {it}) }

    }

    override fun getTest(testId: Int): Either<Failure, TestEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(testApiService.getTest(it.accessToken, it.client, it.uid, testId), TestEntity.empty(), {it}) }
    }

    override fun removeTest(testId: Int): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { request.make(testApiService.deleteTest(it.accessToken, it.client, it.uid, testId), TestEntity.empty(), { None() }) }
    }

    override fun updateTest(testEntity: TestEntity): Either<Failure, TestEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(testApiService.updateTest(it.accessToken, it.client, it.uid,testEntity.id, TestParams(testEntity.studentGroupId, testEntity.start, testEntity.finish)), TestEntity.empty(), {it}) }

    }
}