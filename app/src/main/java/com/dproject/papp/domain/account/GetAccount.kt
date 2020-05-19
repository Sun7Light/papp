package com.dproject.papp.domain.account

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val repository: AccountRepository
) : UseCase<AccountEntity, None>() {

    override suspend fun run(params: None): Either<Failure, AccountEntity> {
        return repository.getCurrentAccount()
    }
}