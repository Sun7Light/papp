package com.dproject.papp.domain.characteristic

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class RemoveCharacteristic @Inject constructor(
    private val repository: CharacteristicRepository
) : UseCase<None, Int>() {

    override suspend fun run(params: Int): Either<Failure, None> {
        return repository.removeCharacteristic(params)
    }
}