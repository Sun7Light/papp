package com.dproject.papp.domain.student

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetStudent @Inject constructor(
    private val repository: StudentRepository
) : UseCase<StudentEntity, Int>() {

    override suspend fun run(params: Int): Either<Failure, StudentEntity> {
        return repository.getStudent(params)
    }
}