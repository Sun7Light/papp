package com.dproject.papp.domain.characteristic

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class AddCharacteristic @Inject constructor(
    private val repository: CharacteristicRepository
) : UseCase<CharacteristicEntity, AddCharacteristic.CharacteristicParams>() {

    override suspend fun run(params: AddCharacteristic.CharacteristicParams): Either<Failure, CharacteristicEntity> {
        return repository.addCharacteristic(CharacteristicEntity(0, params.name, params.positivity, params.personalValueGroup))
    }
    data class CharacteristicParams(val name: String, val positivity: Positivity, val personalValueGroup: Int)
}