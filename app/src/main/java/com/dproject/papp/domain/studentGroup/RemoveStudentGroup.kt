package com.dproject.papp.domain.studentGroup

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class RemoveStudentGroup @Inject constructor(
    private val repository: StudentGroupRepository
) : UseCase<None, Int>() {

    override suspend fun run(params: Int): Either<Failure, None> {
        return repository.removeStudentGroup(params)
    }
}