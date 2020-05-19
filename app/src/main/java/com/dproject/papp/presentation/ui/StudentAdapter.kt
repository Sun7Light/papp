package com.dproject.papp.presentation.ui

import android.view.View
import com.dproject.papp.R
import com.dproject.papp.domain.student.StudentEntity
import com.dproject.papp.presentation.ui.core.BaseCrudAdapter
import kotlinx.android.synthetic.main.lest_element_student_group.view.btnRemove
import kotlinx.android.synthetic.main.list_element_student.view.*

class StudentAdapter: BaseCrudAdapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View) : BaseCrudAdapter.BaseCrudViewHolder(view) {

        init {
            view.btnRemove.setOnClickListener {
                onClick?.onBtnClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? StudentEntity)?.let {

                view.nameTextView.text = "Имя: "+ item.name
                view.sexTextView.text = "Пол: " + item.sex.sexName
                view.tokenTextView.text ="Токен: " + item.token
            }
        }
    }

    override val layoutRes: Int
        get() =  R.layout.list_element_student

    override fun createHolder(view: View, viewType: Int): StudentViewHolder {
        return StudentViewHolder(view)
    }
}