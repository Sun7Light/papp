package com.dproject.papp.domain.school

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetSchool @Inject constructor(
    private val repository: SchoolRepository
) : UseCase<SchoolEntity, Int>() {

    override suspend fun run(params: Int): Either<Failure, SchoolEntity> {
        return repository.getSchool(params)
    }
}