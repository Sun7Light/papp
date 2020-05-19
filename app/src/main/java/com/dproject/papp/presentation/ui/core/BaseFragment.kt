package com.dproject.papp.presentation.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dproject.papp.R

import javax.inject.Inject

@Suppress("DEPRECATION")
abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int
    open val titleToolbar = R.string.app_name
    open val showToolbar = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
            with(activity) {
                if(this is BaseActivity)
                {
                    if (showToolbar) supportActionBar?.show() else supportActionBar?.hide()
                    supportActionBar?.title = getString(titleToolbar)
                }
            }
    }
    open fun onBackPressed() {}

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }

    fun close() = fragmentManager?.popBackStack()
}