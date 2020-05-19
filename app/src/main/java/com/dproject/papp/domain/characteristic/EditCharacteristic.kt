package com.dproject.papp.domain.characteristic

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class EditCharacteristic @Inject constructor(
    private val repository: CharacteristicRepository
) : UseCase<CharacteristicEntity, CharacteristicEntity>() {

    override suspend fun run(params: CharacteristicEntity): Either<Failure, CharacteristicEntity> {
        return repository.updateCharacteristic(params)
    }
}