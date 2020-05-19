package com.dproject.papp.domain.studentGroup

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface StudentGroupRepository {
    fun addStudentGroup(studentGroupEntity: StudentGroupEntity): Either<Failure, StudentGroupEntity>
    fun getStudentGroupForSchool(schoolId: Int): Either<Failure, List<StudentGroupEntity>>
    fun getStudentGroups(): Either<Failure, List<StudentGroupEntity>>
    fun getStudentGroup(studentGroupId: Int): Either<Failure, StudentGroupEntity>
    fun removeStudentGroup(studentGroupId: Int): Either<Failure, None>
    fun updateStudentGroup(studentGroupEntity: StudentGroupEntity): Either<Failure, StudentGroupEntity>
}