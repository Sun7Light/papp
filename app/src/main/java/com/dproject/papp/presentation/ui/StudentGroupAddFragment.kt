package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.studentGroup.StudentGroupEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.StudentGroupViewModel
import kotlinx.android.synthetic.main.fragment_edit_student_group.*

class StudentGroupAddFragment: BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_edit_student_group

    override val titleToolbar: Int
        get() = R.string.create_student_group_screen

    private lateinit var studentGroupViewModel: StudentGroupViewModel
    companion object {
        const val INTENT_EXTRA_PARAM_SCHOOL_ID = "INTENT_PARAM_SCHOOL_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        studentGroupViewModel = viewModel {
            observe(addStudentGroupData, ::handleAddStudentGroupData)
            onFailure(failureData, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmStudGroupButton.text = "Создать"
        val params = activity!!.intent.getBundleExtra("args")
        val schoolId = params.getInt(StudentGroupAddFragment.INTENT_EXTRA_PARAM_SCHOOL_ID)
        confirmStudGroupButton.setOnClickListener { studentGroupViewModel.addStudentGroup(schoolId, editLetter.text.toString(), editDate.text.toString())}
    }


    private fun handleFailure(failure: Failure?) {

    }

    private fun handleAddStudentGroupData(studentGroupEntity: StudentGroupEntity?) {
        activity?.finish()
    }
}