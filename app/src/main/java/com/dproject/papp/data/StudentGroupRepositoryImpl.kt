package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.StudentGroupApi
import com.dproject.papp.data.remote.service.StudentGroupParams
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.studentGroup.StudentGroupEntity
import com.dproject.papp.domain.studentGroup.StudentGroupRepository

class StudentGroupRepositoryImpl(private val studentGroupApiService: StudentGroupApi, private val request: Request, private val accountCache: AccountCache): StudentGroupRepository {
    override fun addStudentGroup(studentGroupEntity: StudentGroupEntity): Either<Failure, StudentGroupEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(studentGroupApiService.createStudentGroup(it.accessToken, it.client, it.uid, StudentGroupParams(studentGroupEntity.schoolId, studentGroupEntity.letter, studentGroupEntity.formDate)), StudentGroupEntity.empty()) {it} }
    }

    override fun getStudentGroupForSchool(schoolId: Int): Either<Failure, List<StudentGroupEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(studentGroupApiService.getStudentGroupsForSchool(it.accessToken, it.client, it.uid, schoolId), emptyList()) {it} }
    }

    override fun getStudentGroups(): Either<Failure, List<StudentGroupEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(studentGroupApiService.getStudentGroups(it.accessToken, it.client, it.uid), emptyList()) {it} }
    }

    override fun getStudentGroup(studentGroupId: Int): Either<Failure, StudentGroupEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(studentGroupApiService.getStudentGroup(it.accessToken, it.client, it.uid, studentGroupId), StudentGroupEntity.empty()) {it} }
    }

    override fun removeStudentGroup(studentGroupId: Int): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { request.make(studentGroupApiService.deleteStudentGroup(it.accessToken, it.client, it.uid, studentGroupId), StudentGroupEntity.empty()) {None()} }
    }

    override fun updateStudentGroup(studentGroupEntity: StudentGroupEntity): Either<Failure, StudentGroupEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(studentGroupApiService.updateStudentGroup(it.accessToken, it.client, it.uid, studentGroupEntity.id, StudentGroupParams(studentGroupEntity.schoolId, studentGroupEntity.letter, studentGroupEntity.formDate)), StudentGroupEntity.empty()) {it} }

    }
}