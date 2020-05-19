package com.dproject.papp.presentation.ui

import android.view.View
import com.dproject.papp.R
import com.dproject.papp.domain.characteristic.CharacteristicEntity
import com.dproject.papp.presentation.ui.core.BaseCrudAdapter
import kotlinx.android.synthetic.main.list_element_characteristic.view.*

class CharacteristicAdapter: BaseCrudAdapter<CharacteristicAdapter.CharacteristicHolder>() {
    class CharacteristicHolder(view: View) : BaseCrudAdapter.BaseCrudViewHolder(view) {

        init {
            view.btnEdit.setOnClickListener {
                onClick?.onBtnClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? CharacteristicEntity)?.let {
                view.nameTextView.text = item.name
                view.positivityTextView.text = item.positivity.info
            }
        }
    }

    override val layoutRes: Int
    get() =  R.layout.list_element_characteristic

    override fun createHolder(view: View, viewType: Int): CharacteristicAdapter.CharacteristicHolder {
        return CharacteristicHolder(view)
    }
}