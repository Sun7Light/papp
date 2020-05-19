package com.dproject.papp.presentation.ui

import android.view.View
import com.dproject.papp.R
import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupEntity
import com.dproject.papp.presentation.ui.core.BaseCrudAdapter
import kotlinx.android.synthetic.main.list_element_with_button.view.*

class PersonalValueGroupAdapter: BaseCrudAdapter<PersonalValueGroupAdapter.PersonalValueGroupHolder>() {
    class PersonalValueGroupHolder(view: View) : BaseCrudAdapter.BaseCrudViewHolder(view) {

        override fun onBind(item: Any) {
            (item as? PersonalValueGroupEntity)?.let {
                view.elementTextView.text = it.name
            }
        }
    }

    override val layoutRes: Int
    get() =  R.layout.list_item

    override fun createHolder(view: View, viewType: Int): PersonalValueGroupHolder {
        return PersonalValueGroupHolder(view)
    }
}