package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.account.SchoolPsychologistAccountEntity
import com.dproject.papp.domain.account.StudentAccountEntity
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseActivity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.HomeViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.navigation.*
import javax.inject.Inject


@Suppress("DEPRECATION")
class HomeActivity: BaseActivity() {
    override var fragment: BaseFragment = HomeFragment()
    override val contentId: Int = R.layout.activity_navigation

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)


        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        homeViewModel = viewModel {
            observe(accountData, ::handleAccount)
            observe(logoutData, ::handleLogout)
            onFailure(failureData, ::handleFailure)
        }

        if(firstTimeCreated(savedInstanceState))
            homeViewModel.getAccount()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView)
                } else {
                    drawerLayout.openDrawer(navigationView)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContent(navigationView: NavigationView, accountEntity: AccountEntity) {

        navigationView.menu.getItem(0).isChecked = true;
        val hView = navigationView.getHeaderView(0)
        var uidTextView = hView.findViewById<TextView>(R.id.uidTextView)
        var accountTypeTextView = hView.findViewById<TextView>(R.id.accountTypeTextView)
        accountTypeTextView.text = accountEntity.toString()
        uidTextView.text = accountEntity.uid

        replaceFragment(CitiesFragment())
        if(accountEntity is StudentAccountEntity) {
            navigationView.menu.findItem(R.id.accounts).isVisible = false
            navigationView.menu.findItem(R.id.cities).isVisible = false
            navigationView.menu.findItem(R.id.characteristics).isVisible = false
            navigationView.menu.findItem(R.id.tests_sessions).isVisible = true
        }
        else if(accountEntity is SchoolPsychologistAccountEntity) {
            navigationView.menu.findItem(R.id.accounts).isVisible = false
            navigationView.menu.findItem(R.id.cities).isVisible = false
            navigationView.menu.findItem(R.id.characteristics).isVisible = false
            navigationView.menu.findItem(R.id.tests).isVisible = true
            replaceFragment(HomeFragment())
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.cities -> changeFragment(CitiesFragment())
                R.id.characteristics -> changeFragment(PersonalValueGroupFragment())
                R.id.logout -> homeViewModel.logout()
            }
            true
        }
    }

    private fun changeFragment(fragment: BaseFragment) {
        replaceFragment(fragment)
        drawerLayout.closeDrawer(navigationView)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }
    private fun handleLogout(none: None?) {
        navigator.showSignIn(this)
        finish()
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handleAccount(accountEntity: AccountEntity?) {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_white_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupDrawerContent(navigationView, accountEntity!!)
    }
}