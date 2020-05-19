package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.student.StudentEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_cities.*

class StudentFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_cities

    override val titleToolbar: Int
        get() = R.string.student_screen

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var studentViewModel: StudentViewModel
    private val viewAdapter = StudentAdapter()

    companion object {
        const val INTENT_EXTRA_PARAM_STUDENT_GROUP_ID = "INTENT_PARAM_STUDENT_GROUP_ID"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)

        studentViewModel = viewModel {
            observe(studentsData, ::handleStudents)
            onFailure(failureData, ::handleFailure)
        }

        lm = LinearLayoutManager(context)

        recyclerView.layoutManager = lm
        recyclerView.adapter = viewAdapter

        viewAdapter.setOnClick(
            {studentEntity, view -> (studentEntity as? StudentEntity)?.let {  } },
            {studentEntity, view -> (studentEntity as? StudentEntity)?.let { navigator.showStudentChangeActivity(activity!!, studentEntity.id)  } })

        fabCreate.setOnClickListener {
            val params = activity!!.intent.getBundleExtra("args")
            val studentGroupId = params.getInt(StudentFragment.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID)
            navigator.showStudentAddActivity(activity!!, studentGroupId)
        }
    }

    override fun onResume() {
        super.onResume()
        val params = activity!!.intent.getBundleExtra("args")
        val studentGroupId = params.getInt(StudentFragment.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID)
        studentViewModel.getStudentForStudentGroup(studentGroupId)
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handleStudents(students: List<StudentEntity>?) {
        viewAdapter.clear()
        viewAdapter.add(students!!)
        viewAdapter.notifyDataSetChanged()
    }
}