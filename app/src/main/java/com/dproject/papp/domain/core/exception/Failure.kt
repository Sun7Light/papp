package com.dproject.papp.domain.core.exception

sealed class Failure {
    object NetworkConnectionError: Failure()
    object ServerError: Failure()
    object InvalidToken: Failure()
    object StudentAlreadyExists: Failure()
    object NoSavedAccountsError: Failure()
    object WrongLoginOrPassword: Failure()
}