package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.city.CityEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.CityViewModel
import kotlinx.android.synthetic.main.fragment_cities.*

class CitiesFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_cities
    override val titleToolbar: Int = R.string.cities_screen

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var cityViewModel: CityViewModel
    private val viewAdapter = CityAdapter()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        cityViewModel = viewModel {
            observe(citiesData, ::handleCitiesData)
            onFailure(failureData, ::handleFailure)
        }

        lm = LinearLayoutManager(context)

        recyclerView.layoutManager = lm
        recyclerView.adapter = viewAdapter

        viewAdapter.setOnClick(
            {cityEntity, view -> (cityEntity as? CityEntity)?.let { navigator.showSchoolActivity(activity!!, cityEntity.id) } },
            {cityEntity, view -> (cityEntity as? CityEntity)?.let { navigator.showChangeCityActivity(activity!!, cityEntity.id)  } })



        fabCreate.setOnClickListener {
            navigator.showCreateCity(activity!!)
        }
    }


    override fun onResume() {
        super.onResume()
        cityViewModel.getCities()
    }
    fun handleFailure(failure: Failure?) {

    }

    private fun handleCitiesData(cities: List<CityEntity>?) {
        viewAdapter.clear()
        viewAdapter.add(cities!!)
        viewAdapter.notifyDataSetChanged()
    }
}