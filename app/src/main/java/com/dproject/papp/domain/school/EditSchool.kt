package com.dproject.papp.domain.school

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class EditSchool @Inject constructor(
    private val repository: SchoolRepository
) : UseCase<SchoolEntity, SchoolEntity>() {

    override suspend fun run(params: SchoolEntity): Either<Failure, SchoolEntity> {
        return repository.updateSchool(params)
    }
}