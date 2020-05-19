package com.dproject.papp.domain.city

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetCities @Inject constructor(
    private val repository: CityRepository
) : UseCase<List<CityEntity>, None>() {

    override suspend fun run(params: None): Either<Failure, List<CityEntity>> {
        return repository.getCities()
    }
}