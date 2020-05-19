package com.dproject.papp.domain.characteristic

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface CharacteristicRepository {
    fun addCharacteristic(characteristicEntity: CharacteristicEntity): Either<Failure, CharacteristicEntity>
    fun getCharacteristicForPersonalValueGroup(personalValueGroupId: Int): Either<Failure, List<CharacteristicEntity>>
    fun getCharacteristics(): Either<Failure, List<CharacteristicEntity>>
    fun getCharacteristic(characteristicEntityId: Int): Either<Failure, CharacteristicEntity>
    fun removeCharacteristic(characteristicEntityId: Int): Either<Failure, None>
    fun updateCharacteristic(characteristicEntity: CharacteristicEntity): Either<Failure, CharacteristicEntity>
}