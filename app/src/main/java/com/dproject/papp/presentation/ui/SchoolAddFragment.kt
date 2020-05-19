package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.school.SchoolEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.SchoolViewModel
import kotlinx.android.synthetic.main.fragment_create_city.*

class SchoolAddFragment: BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_create_city

    override val titleToolbar: Int
        get() = R.string.create_school_screen

    private lateinit var schoolViewModel: SchoolViewModel
    companion object {
        const val INTENT_EXTRA_PARAM_CITY_ID = "INTENT_PARAM_CITY_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        schoolViewModel = viewModel {
            observe(addSchoolData, ::handleAddSchoolData)
            onFailure(failureData, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editCityText.setHint("Название школы")
        val params = activity!!.intent.getBundleExtra("args")
        val cityId = params.getInt(SchoolAddFragment.INTENT_EXTRA_PARAM_CITY_ID)
        createCityButton.setOnClickListener { schoolViewModel.addSchool(editCityText.text.toString(), cityId) }
    }


    private fun handleFailure(failure: Failure?) {

    }

    private fun handleAddSchoolData(schoolEntity: SchoolEntity?) {
        activity?.finish()
    }
}