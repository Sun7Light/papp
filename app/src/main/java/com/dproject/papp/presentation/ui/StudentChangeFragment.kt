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

class StudentChangeFragment: BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_edit_student

    override val titleToolbar: Int
        get() = R.string.change_student_screen

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var sp_s: AppCompatSpinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        studentViewModel = viewModel {
            observe(studentData, ::handleStudentData)
            observe(changeStydentData, ::handleEditStudent)
            onFailure(failureData, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmStudButton.text = "Сохранить"
        confirmStudButton.setOnClickListener {
            studentViewModel.editStudent(editStudName.text.toString(), sp_s.selectedItem as Sex)
        }
        sp_s = sSpinner
        val sexList = listOf<Sex>(Sex.male, Sex.female)
        var adapter = ArrayAdapter(activity!!, R.layout.support_simple_spinner_dropdown_item, sexList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_s.adapter = adapter

        val params = activity!!.intent.getBundleExtra("args")
        val studentId = params.getInt(StudentChangeActivity.INTENT_EXTRA_PARAM_STUDENT_ID)

        studentViewModel.getStudent(studentId)
    }

    private fun handleFailure(faulire: Failure?) {

    }

    private fun handleEditStudent(studentEntity: StudentEntity?) {
        activity?.finish()
    }

    private fun handleStudentData(studentEntity: StudentEntity?) {
        editStudName.setText(studentEntity?.name)

        if(studentEntity?.sex == Sex.female)
            sp_s.setSelection(1)
        else
            sp_s.setSelection(0)
    }
}