package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.StudentApiService
import com.dproject.papp.data.remote.service.StudentParams
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.student.StudentEntity
import com.dproject.papp.domain.student.StudentRepository

class StudentRepositoryImpl(private val studentApiService: StudentApiService, private val request: Request, private val accountCache: AccountCache): StudentRepository {
    override fun addStudent(studentEntity: StudentEntity): Either<Failure, StudentEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(studentApiService.createStudent(it.accessToken, it.client, it.uid, StudentParams(studentEntity.studentGroupId, studentEntity.name, studentEntity.sex)), StudentEntity.empty()) {it} }
    }

    override fun getStudentForStudentGroup(studentGroupId: Int): Either<Failure, List<StudentEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(studentApiService.getStudentsForStudentGroup(it.accessToken, it.client, it.uid, studentGroupId), emptyList()) {it} }
    }

    override fun getStudents(): Either<Failure, List<StudentEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(studentApiService.getStudents(it.accessToken, it.client, it.uid), emptyList()) {it} }
    }

    override fun getStudent(studentId: Int): Either<Failure, StudentEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(studentApiService.getStudent(it.accessToken, it.client, it.uid, studentId), StudentEntity.empty()) {it} }
    }

    override fun removeStudent(studentId: Int): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { request.make(studentApiService.deleteStudent(it.accessToken, it.client, it.uid, studentId), StudentEntity.empty()) {None()} }
    }

    override fun updateStudent(studentEntity: StudentEntity): Either<Failure, StudentEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(studentApiService.updateStudent(it.accessToken, it.client, it.uid, studentEntity.id, StudentParams(studentEntity.studentGroupId, studentEntity.name, studentEntity.sex)), StudentEntity.empty()) {it} }
    }
}