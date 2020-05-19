package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.school.SchoolEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.SchoolViewModel
import kotlinx.android.synthetic.main.fragment_cities.*

class SchoolsFragment: BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_cities

    override val titleToolbar: Int
        get() = R.string.schools_screen

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var schoolViewModel: SchoolViewModel
    private val viewAdapter = SchoolAdapter()

    companion object {
        const val INTENT_EXTRA_PARAM_CITY_ID = "INTENT_PARAM_CITY_ID"
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)

        schoolViewModel = viewModel {
            observe(schoolsData, ::handleSchools)
            onFailure(failureData, ::handleFailure)
        }

        lm = LinearLayoutManager(context)

        recyclerView.layoutManager = lm
        recyclerView.adapter = viewAdapter

        viewAdapter.setOnClick(
            {schoolEntity, view -> (schoolEntity as? SchoolEntity)?.let { navigator.showStudentGroupActivity(activity!!, schoolEntity.id) } },
            {schoolEntity, view -> (schoolEntity as? SchoolEntity)?.let { navigator.showSchoolChangeActivity(activity!!, schoolEntity.id)  } })

        fabCreate.setOnClickListener {
            val params = activity!!.intent.getBundleExtra("args")
            val cityId = params.getInt(ChangeCityActivity.INTENT_EXTRA_PARAM_CITY_ID)
            navigator.showSchoolAddActivity(activity!!, cityId)
        }
    }

    override fun onResume() {
        super.onResume()
        val params = activity!!.intent.getBundleExtra("args")
        val cityId = params.getInt(ChangeCityActivity.INTENT_EXTRA_PARAM_CITY_ID)
        schoolViewModel.getSchoolsForCity(cityId)
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handleSchools(schools: List<SchoolEntity>?) {
        viewAdapter.clear()
        viewAdapter.add(schools!!)
        viewAdapter.notifyDataSetChanged()
    }
}