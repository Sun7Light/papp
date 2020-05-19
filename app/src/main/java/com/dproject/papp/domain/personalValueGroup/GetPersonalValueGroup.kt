package com.dproject.papp.domain.personalValueGroup

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetPersonalValueGroup @Inject constructor(
    private val repository: PersonalValueGroupRepository
) : UseCase<PersonalValueGroupEntity, Int>() {

    override suspend fun run(params: Int): Either<Failure, PersonalValueGroupEntity> {
        return repository.getPersonalValueGroup(params)
    }
}