package com.dproject.papp.domain.test

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface TestRepository {
    fun getTestsForSchool(schoolId: Int): Either<Failure, List<TestEntity>>
    fun addTest(testEntity: TestEntity): Either<Failure, TestEntity>
    fun getTestForStudentGroup(studentGroupId: Int): Either<Failure, List<TestEntity>>
    fun getTest(testId: Int): Either<Failure, TestEntity>
    fun removeTest(testId: Int): Either<Failure, None>
    fun updateTest(testEntity: TestEntity): Either<Failure, TestEntity>
}