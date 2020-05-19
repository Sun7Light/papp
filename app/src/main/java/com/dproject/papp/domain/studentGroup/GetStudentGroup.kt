package com.dproject.papp.domain.studentGroup

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetStudentGroup  @Inject constructor(
    private val repository: StudentGroupRepository
) : UseCase<StudentGroupEntity, Int>() {

    override suspend fun run(params: Int): Either<Failure, StudentGroupEntity> {
        return repository.getStudentGroup(params)
    }
}