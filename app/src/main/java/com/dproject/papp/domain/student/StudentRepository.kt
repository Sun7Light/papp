package com.dproject.papp.domain.student

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface StudentRepository {
    fun addStudent(studentEntity: StudentEntity): Either<Failure, StudentEntity>
    fun getStudentForStudentGroup(studentGroupId: Int): Either<Failure, List<StudentEntity>>
    fun getStudents(): Either<Failure, List<StudentEntity>>
    fun getStudent(studentId: Int): Either<Failure, StudentEntity>
    fun removeStudent(studentId: Int): Either<Failure, None>
    fun updateStudent(studentEntity: StudentEntity): Either<Failure, StudentEntity>
}