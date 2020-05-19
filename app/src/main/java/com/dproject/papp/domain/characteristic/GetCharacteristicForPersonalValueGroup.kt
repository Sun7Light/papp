package com.dproject.papp.domain.characteristic

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetCharacteristicForPersonalValueGroup @Inject constructor(
    private val repository: CharacteristicRepository
) : UseCase<List<CharacteristicEntity>, Int>() {

    override suspend fun run(params: Int): Either<Failure, List<CharacteristicEntity>> {
        return repository.getCharacteristicForPersonalValueGroup(params)
    }
}