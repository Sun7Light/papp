package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseActivity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.SchoolViewModel
import com.dproject.papp.presentation.viewModel.core.ViewModelFactory
import javax.inject.Inject

@Suppress("DEPRECATION")
class SchoolChangeActivity:BaseActivity() {
    override var fragment: BaseFragment = SchoolChangeFragment()

    private lateinit var schoolViewModel: SchoolViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        const val INTENT_EXTRA_PARAM_SCHOOL_ID = "INTENT_PARAM_SCHOOL_ID"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        App.appComponent.inject(this)

        schoolViewModel = viewModel {
            observe(removeSchoolData, ::handleRemove)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_remove, menu)
        return true
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handleRemove(none: None?) {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_remove -> {
                showRemoveDialog()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showRemoveDialog() {

        AlertDialog.Builder(this)
            .setMessage("Вы действительно хотите удалить эту школу?")

            .setPositiveButton(android.R.string.yes) { dialog, which ->
                val params = this.intent.getBundleExtra("args")
                val schoolId = params.getInt(SchoolChangeActivity.INTENT_EXTRA_PARAM_SCHOOL_ID)
                schoolViewModel.removeSchool(schoolId)
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }
    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }
}