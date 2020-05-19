package com.dproject.papp.data.remote

import com.dproject.papp.data.DataAccountEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {
    fun <T,R> make(call: Call<T>, default: T, transform: (T) -> R): Either<Failure, R> {
        return when(networkHandler.isConnected()) {
            true -> execute(call, default, transform)
            false -> Either.Left(Failure.NetworkConnectionError)
        }
    }
    fun<R> makeLogin(call:Call<DataAccountEntity>, transform: (DataAccountEntity) -> R): Either<Failure, R> {
        if(networkHandler.isConnected()) {
            return try {
                val response = call.execute()
                if(response.isSucceed()) {
                    var account = response.body()!!
                    account.uid = response.headers()["uid"]!!
                    account.accessToken = response.headers()["access-token"]!!
                    account.client = response.headers()["client"]!!
                    return Either.Right(transform(account))
                }
                return Either.Left(response.parseError())
            }
            catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
        return Either.Left(Failure.NetworkConnectionError)
    }
    private fun <T,R> execute(call: Call<T>, default: T, transform: (T) -> R) :Either<Failure, R> {
        return try {
            val response = call.execute()
            if(response.isSucceed()) {
                var resObj = response.body()?:default
                return Either.Right(transform(resObj))
            }
            return Either.Left(response.parseError())
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}

fun<T> Response<T>.parseError(): Failure {
    val error:String = JSONObject(errorBody()!!.string()).getString("errors")
    return when(error.trim('[',']','"')) {
        "Неверный токен" -> Failure.InvalidToken
        "Неверный логин или пароль" -> Failure.WrongLoginOrPassword
        "Вы уже регистрировались." -> Failure.StudentAlreadyExists
        else -> Failure.ServerError
    }
}

fun <T> Response<T>.isSucceed(): Boolean {
    return isSuccessful && errorBody() == null
}