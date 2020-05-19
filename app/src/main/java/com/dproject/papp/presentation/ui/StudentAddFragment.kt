package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.student.Sex
import com.dproject.papp.domain.student.StudentEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_edit_student.*

class StudentAddFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_edit_student

    override val titleToolbar: Int
        get() = R.string.create_student_screen

    private lateinit var sp_s: AppCompatSpinner
    private lateinit var studentViewModel: StudentViewModel
    companion object {
        const val INTENT_EXTRA_PARAM_STUDENT_GROUP_ID = "INTENT_PARAM_STUDENT_GROUP_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        studentViewModel = viewModel {
            observe(addStudentData, ::handleAddStudentData)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmStudButton.text = "Создать"
        val params = activity!!.intent.getBundleExtra("args")
        val studentGroupId = params.getInt(StudentAddFragment.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID)
        confirmStudButton.setOnClickListener { studentViewModel.addStudent(studentGroupId, editStudName.text.toString(), sp_s.selectedItem as Sex)}
        sp_s = sSpinner
        val sexList = listOf<Sex>(Sex.male, Sex.female)
        var adapter = ArrayAdapter(activity!!, R.layout.support_simple_spinner_dropdown_item, sexList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_s.adapter = adapter
    }


    private fun handleFailure(failure: Failure?) {

    }

    private fun handleAddStudentData(studentEntity: StudentEntity?) {
        activity?.finish()
    }
}