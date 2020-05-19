package com.dproject.papp.domain.city

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetCity @Inject constructor(
    private val repository: CityRepository
) : UseCase<CityEntity, Int>() {

    override suspend fun run(params: Int): Either<Failure, CityEntity> {
        return repository.getCity(params)
    }
}