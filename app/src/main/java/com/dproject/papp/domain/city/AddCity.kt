package com.dproject.papp.domain.city

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class AddCity @Inject constructor(
    private val repository: CityRepository
) : UseCase<CityEntity, AddCity.CityParams>() {

    override suspend fun run(params: CityParams): Either<Failure, CityEntity> {
        return repository.addCity(CityEntity(0, params.name))
    }

    data class CityParams(val name: String)
}