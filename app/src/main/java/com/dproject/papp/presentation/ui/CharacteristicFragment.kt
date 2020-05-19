package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.characteristic.CharacteristicEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.CharacteristicViewModel
import kotlinx.android.synthetic.main.fragment_cities.*

class CharacteristicFragment: BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_cities

    override val titleToolbar: Int
        get() = R.string.characteristic_screen

    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var characteristicViewModel: CharacteristicViewModel
    private val viewAdapter = CharacteristicAdapter()

    companion object {
        const val INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP = "INTENT_PARAM_PERSONAL_VALUE_GROUP"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)

        characteristicViewModel = viewModel {
            observe(characteristicsData, ::handleCharacteristics)
            onFailure(failureData, ::handleFailure)
        }

        lm = LinearLayoutManager(context)

        recyclerView.layoutManager = lm
        recyclerView.adapter = viewAdapter

        viewAdapter.setOnClick(
            {charEntity, view -> (charEntity as? CharacteristicEntity)?.let {  } },
            {charEntity, view -> (charEntity as? CharacteristicEntity)?.let { navigator.showCharacteristicChangeActivity(activity!!, charEntity.id)  } })

        fabCreate.setOnClickListener {
            val params = activity!!.intent.getBundleExtra("args")
            val personalGroupId = params.getInt(CharacteristicFragment.INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP)
            navigator.showCharacteristicAddActivity(activity!!, personalGroupId)
        }
    }

    override fun onResume() {
        super.onResume()
        val params = activity!!.intent.getBundleExtra("args")
        val personValueGroupId = params.getInt(CharacteristicFragment.INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP)
        characteristicViewModel.getCharacteristicForPersonalValueGroup(personValueGroupId)
    }

    private fun handleFailure(failure: Failure?) {

    }

    private fun handleCharacteristics(characteristics: List<CharacteristicEntity>?) {
        viewAdapter.clear()
        viewAdapter.add(characteristics!!)
        viewAdapter.notifyDataSetChanged()
    }
}