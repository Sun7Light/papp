package com.dproject.papp.domain.student

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class AddStudent @Inject constructor(
    private val repository: StudentRepository
) : UseCase<StudentEntity, AddStudent.StudentParams>() {

    override suspend fun run(params: StudentParams): Either<Failure, StudentEntity> {
        return repository.addStudent(StudentEntity(0,params.studentGroupId, params.name, params.sex, ""))
    }

    data class StudentParams(val studentGroupId: Int, val name: String, val sex:Sex) {
    }
}