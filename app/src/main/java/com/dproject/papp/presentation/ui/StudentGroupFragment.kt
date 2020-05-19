package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.studentGroup.StudentGroupEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.StudentGroupViewModel
import kotlinx.android.synthetic.main.fragment_cities.*

class StudentGroupFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_cities

    override val titleToolbar: Int
        get() = R.string.students_group_screen

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var studentGroupViewModel: StudentGroupViewModel
    private val viewAdapter = StudentGroupAdapter()

    companion object {
        const val INTENT_EXTRA_PARAM_SCHOOL_ID = "INTENT_PARAM_SCHOOL_ID"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)

        studentGroupViewModel = viewModel {
            observe(studentGroupsData, ::handleStudentGroups)
            onFailure(failureData, ::handleFailure)
        }

        lm = LinearLayoutManager(context)

        recyclerView.layoutManager = lm
        recyclerView.adapter = viewAdapter

        viewAdapter.setOnClick(
            {studentGroupEntity, view -> (studentGroupEntity as? StudentGroupEntity)?.let { navigator.showStudentActivity(activity!!, studentGroupEntity.id) } },
            {studentGroupEntity, view -> (studentGroupEntity as? StudentGroupEntity)?.let { navigator.showStudentGroupChangeActivity(activity!!, studentGroupEntity.id)  } })

        fabCreate.setOnClickListener {
            val params = activity!!.intent.getBundleExtra("args")
            val schoolId = params.getInt(StudentGroupFragment.INTENT_EXTRA_PARAM_SCHOOL_ID)
            navigator.showStudentGroupAddActivity(activity!!, schoolId)
        }
    }

    override fun onResume() {
        super.onResume()
        val params = activity!!.intent.getBundleExtra("args")
        val schoolId = params.getInt(StudentGroupFragment.INTENT_EXTRA_PARAM_SCHOOL_ID)
        studentGroupViewModel.getStudentGroupsForSchool(schoolId)
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handleStudentGroups(studentGroups: List<StudentGroupEntity>?) {
        viewAdapter.clear()
        viewAdapter.add(studentGroups!!)
        viewAdapter.notifyDataSetChanged()
    }
}