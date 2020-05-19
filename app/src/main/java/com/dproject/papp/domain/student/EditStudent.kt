package com.dproject.papp.domain.student

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class EditStudent @Inject constructor(
    private val repository: StudentRepository
) : UseCase<StudentEntity, StudentEntity>() {

    override suspend fun run(params: StudentEntity): Either<Failure, StudentEntity> {
        return repository.updateStudent(params)
    }
}