package com.dproject.papp.presentation.viewModel.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dproject.papp.domain.core.HandleOnce
import com.dproject.papp.domain.core.exception.Failure

abstract class BaseViewModel: ViewModel() {
    var failureData: MutableLiveData<HandleOnce<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failureData.value =
            HandleOnce(failure)
    }
}