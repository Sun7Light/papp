package com.dproject.papp.domain.student

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class RemoveStudent @Inject constructor(
    private val repository: StudentRepository
) : UseCase<None, Int>() {

    override suspend fun run(params: Int): Either<Failure, None> {
        return repository.removeStudent(params)
    }
}