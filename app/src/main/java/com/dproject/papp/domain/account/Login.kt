package com.dproject.papp.domain.account

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: AccountRepository
) : UseCase<AccountEntity, Login.LoginParams>() {

    override suspend fun run(params: LoginParams): Either<Failure, AccountEntity> {
        return repository.login(params.login, params.password)
    }

    data class LoginParams(val login:String, val password:String)
}