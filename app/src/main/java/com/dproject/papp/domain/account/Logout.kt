package com.dproject.papp.domain.account

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class Logout @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, None>() {

    override suspend fun run(params: None): Either<Failure, None> {
        return repository.logout()
    }
}