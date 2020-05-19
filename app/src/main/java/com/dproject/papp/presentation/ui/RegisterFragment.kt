package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.DialogType
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: BaseFragment() {
    override val layoutId = R.layout.fragment_register
    override val titleToolbar = R.string.register_screen


    private lateinit var registerViewModel:RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        registerViewModel = viewModel {
            observe(registerData, ::handleRegister)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            registerViewModel.register(loginEditText.text.toString(), passwordEditText.text.toString(), tokenEditText.text.toString())
        }
    }

    private fun handleFailure(failure: Failure?) {
        when(failure) {
            Failure.NetworkConnectionError -> navigator.showDialog(context,"Ошибка соединения, проверьте настройки сети", DialogType.Error)
            Failure.ServerError -> navigator.showDialog(context,"Ошибка сервера", DialogType.Error)
            Failure.StudentAlreadyExists -> navigator.showDialog(context,"Школьник с таким токеном уже зарегистрирован", DialogType.Error)
            Failure.InvalidToken -> navigator.showDialog(context,"Неверный токен", DialogType.Error)
        }
    }

    private fun handleRegister(none: None?) {
        navigator.showDialogWithAction(context, "Вы успешно зарегестрировались", DialogType.Done) {
            activity?.finish()
        }
    }
}