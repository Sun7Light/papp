package com.dproject.papp.domain.city

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class RemoveCity @Inject constructor(
    private val repository: CityRepository
) : UseCase<None, Int>() {

    override suspend fun run(cityid: Int): Either<Failure, None> {
        return repository.removeCity(cityid)
    }
}