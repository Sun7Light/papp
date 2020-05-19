package com.dproject.papp.domain.characteristic

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetCharacteristic @Inject constructor(
    private val repository: CharacteristicRepository
) : UseCase<CharacteristicEntity, Int>() {

    override suspend fun run(params: Int): Either<Failure, CharacteristicEntity> {
        return repository.getCharacteristic(params)
    }
}