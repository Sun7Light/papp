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

class SchoolChangeFragment:BaseFragment() {
    override val layoutId: Int = R.layout.fragment_create_city
    override val titleToolbar: Int
        get() = R.string.change_school_screen

    private lateinit var schoolViewModel: SchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        schoolViewModel = viewModel {
            observe(schoolData, ::handleSchoolData)
            observe(changeSchoolData, ::handleEditSchool)
            onFailure(failureData, ::handleFailure)
        }
        val params = activity!!.intent.getBundleExtra("args")
        val cityId = params.getInt(SchoolChangeActivity.INTENT_EXTRA_PARAM_SCHOOL_ID)

        schoolViewModel.getSchool(cityId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCityButton.text = "Сохранить"
        createCityButton.setOnClickListener {
            schoolViewModel.editSchool(editCityText.text.toString())
        }
    }

    private fun handleFailure(faulire: Failure?) {

    }

    private fun handleEditSchool(schoolEntity: SchoolEntity?) {
        activity?.finish()
    }

    private fun handleSchoolData(schoolEntity: SchoolEntity?) {
        editCityText.setText(schoolEntity?.name)
    }
}