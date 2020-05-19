package com.dproject.papp.domain.city

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class EditCity @Inject constructor(
    private val repository: CityRepository
) : UseCase<CityEntity, CityEntity>() {

    override suspend fun run(params: CityEntity): Either<Failure, CityEntity> {
        return repository.updateCity(params)
    }
}