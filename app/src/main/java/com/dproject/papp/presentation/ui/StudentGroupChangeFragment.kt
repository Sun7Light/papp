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

class StudentGroupChangeFragment:BaseFragment() {

    override val layoutId: Int = R.layout.fragment_edit_student_group
    override val titleToolbar: Int
        get() = R.string.change_student_group_screen

    private lateinit var studentGroupViewModel: StudentGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        studentGroupViewModel = viewModel {
            observe(studentGroupData, ::handleStudentGroupData)
            observe(changeStydentGroupData, ::handleEditStudentGroup)
            onFailure(failureData, ::handleFailure)
        }
        val params = activity!!.intent.getBundleExtra("args")
        val studentGroupId = params.getInt(StudentGroupChangeActivity.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID)

        studentGroupViewModel.getStudentGroup(studentGroupId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmStudGroupButton.text = "Сохранить"
        confirmStudGroupButton.setOnClickListener {
            studentGroupViewModel.editStudentGroup(editLetter.text.toString(), editDate.text.toString())
        }
    }

    private fun handleFailure(faulire: Failure?) {

    }

    private fun handleEditStudentGroup(studentGroupEntity: StudentGroupEntity?) {
        activity?.finish()
    }

    private fun handleStudentGroupData(studentGroupEntity: StudentGroupEntity?) {
        editLetter.setText(studentGroupEntity?.letter)
        editDate.setText(studentGroupEntity?.formDate)
    }
}