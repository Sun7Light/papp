package com.dproject.papp.domain.account

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface AccountRepository {
    fun register(login: String, password: String, token: String): Either<Failure, None>
    fun login(login: String, password: String): Either<Failure, AccountEntity>
    fun logout(): Either<Failure, None>
    fun getCurrentAccount(): Either<Failure, AccountEntity>
}