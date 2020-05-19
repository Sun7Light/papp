package com.dproject.papp.presentation.ui

import android.view.View
import com.dproject.papp.R
import com.dproject.papp.domain.studentGroup.StudentGroupEntity
import com.dproject.papp.presentation.ui.core.BaseCrudAdapter
import kotlinx.android.synthetic.main.lest_element_student_group.view.*

class StudentGroupAdapter: BaseCrudAdapter<StudentGroupAdapter.StudentGroupViewHolder>() {
    class StudentGroupViewHolder(view: View) : BaseCrudAdapter.BaseCrudViewHolder(view) {

        init {
            view.btnRemove.setOnClickListener {
                onClick?.onBtnClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? StudentGroupEntity)?.let {

                view.classTextView.text = "Класс: " + item.name
                view.letterTextView.text = "Буква: " + item.letter
                view.dateTextView.text =  "Дата поступления: " + item.formDate
            }
        }
    }

    override val layoutRes: Int
        get() =  R.layout.lest_element_student_group

    override fun createHolder(view: View, viewType: Int): StudentGroupViewHolder {
        return StudentGroupViewHolder(view)
    }
}