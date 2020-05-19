package com.dproject.papp.presentation.ui

import android.view.View
import com.dproject.papp.R
import com.dproject.papp.domain.school.SchoolEntity
import com.dproject.papp.presentation.ui.core.BaseCrudAdapter
import kotlinx.android.synthetic.main.list_element_with_button.view.*

class SchoolAdapter : BaseCrudAdapter<SchoolAdapter.SchoolViewHolder>() {
    class SchoolViewHolder(view: View) : BaseCrudAdapter.BaseCrudViewHolder(view) {

        init {
            view.btnRemove.setOnClickListener {
                onClick?.onBtnClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? SchoolEntity)?.let {
                view.elementTextView.text = it.name
            }
        }
    }

    override val layoutRes: Int
        get() =  R.layout.list_element_with_button

    override fun createHolder(view: View, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(view)
    }
}