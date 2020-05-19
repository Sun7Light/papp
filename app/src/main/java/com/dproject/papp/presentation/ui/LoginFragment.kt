package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.DialogType
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment() {
    override val layoutId = R.layout.fragment_login
    override val titleToolbar = R.string.login_screen

    private lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        loginViewModel = viewModel {
            observe(loginData, ::handleLogin)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationTextView.setOnClickListener {
            activity?.let {
                navigator.showSignUp(it)
            }
        }
        loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        loginViewModel.login(loginEditText.text.toString(), passwordEditText.text.toString())
    }

    private fun handleFailure(failure: Failure?) {
        when(failure) {
            Failure.NetworkConnectionError -> navigator.showDialog(context,"Ошибка соединения, проверьте настройки сети", DialogType.Error)
            Failure.ServerError -> navigator.showDialog(context,"Ошибка сервера", DialogType.Error)
            Failure.WrongLoginOrPassword -> navigator.showDialog(context,"Неверный логин или пароль", DialogType.Error)
        }
    }

    private fun handleLogin(accountEntity: AccountEntity?) {
        navigator.showHome(context)
    }
}