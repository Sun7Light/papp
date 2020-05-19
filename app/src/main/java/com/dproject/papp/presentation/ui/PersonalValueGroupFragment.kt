package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupEntity
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.PersonalValueGroupViewModel
import kotlinx.android.synthetic.main.fragment_cities.*

class PersonalValueGroupFragment: BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_personal_value_group

    override val titleToolbar: Int
        get() = R.string.personal_value_groups_screen

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var personalValueGroupViewModel: PersonalValueGroupViewModel
    private val viewAdapter = PersonalValueGroupAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)

        personalValueGroupViewModel = viewModel {
            observe(personalValueGroupsData, ::handlePersonalValueGroupsData)
            onFailure(failureData, ::handleFailure)
        }

        lm = LinearLayoutManager(context)

        recyclerView.layoutManager = lm
        recyclerView.adapter = viewAdapter

        viewAdapter.setOnClick(
            {personalValueGroup, view -> (personalValueGroup as? PersonalValueGroupEntity)?.let { navigator.showCharacteristicActivity(activity!!, personalValueGroup.id) } },
            {personalValueGroup, view -> (personalValueGroup as? PersonalValueGroupEntity)?.let {   } })
    }

    override fun onResume() {
        super.onResume()
        personalValueGroupViewModel.getPersonalValueGroups()
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handlePersonalValueGroupsData(personalValueGroups: List<PersonalValueGroupEntity>?) {
        viewAdapter.clear()
        viewAdapter.add(personalValueGroups!!)
        viewAdapter.notifyDataSetChanged()
    }
}