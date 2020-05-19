package com.dproject.papp.presentation.ui.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dproject.papp.R
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {
    abstract var fragment: BaseFragment
    open val contentId = R.layout.activity_layout

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentId)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    fun replaceFragment(fragment: BaseFragment) {
        this.fragment = fragment
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, fragment)
        }
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
        }
    }
    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

}
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()