package com.dproject.papp.domain.personalValueGroup

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetPersonalValueGroups @Inject constructor(
    private val repository: PersonalValueGroupRepository
) : UseCase<List<PersonalValueGroupEntity>, None>() {

    override suspend fun run(params: None): Either<Failure, List<PersonalValueGroupEntity>> {
        return repository.getPersonalValueGroups()
    }
}