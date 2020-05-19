package com.dproject.papp.domain.account

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.RegisterParams>() {

    override suspend fun run(params: RegisterParams): Either<Failure, None> {
       return repository.register(params.login, params.password, params.token)
    }

   data class RegisterParams(val login:String, val password:String, val token:String)
}