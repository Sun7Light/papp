package com.dproject.papp.presentation.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseCrudAdapter<VH: BaseCrudAdapter.BaseCrudViewHolder>: RecyclerView.Adapter<VH>() {
    abstract val layoutRes: Int
    var items: ArrayList<Any> = ArrayList()
    var onClick: OnClick? = null

    interface OnClick {
        fun onClick(item: Any?, view: View)
        fun onBtnClick(item: Any?, view: View)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))

        holder.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return createHolder(v, viewType)
    }

    abstract fun createHolder(view: View, viewType: Int): VH

    fun getItem(position: Int): Any {
        return items[position]
    }


    fun add(newItem: Any) {
        items.add(newItem)
    }

    fun add(newItems: List<Any>) {
        items.addAll(newItems)
    }

    fun clear() {
        items.clear()
    }

    fun setOnClick(click: (Any?, View) -> Unit, btnClick: (Any?, View) -> Unit) {
        onClick = object : OnClick {
            override fun onClick(item: Any?, view: View) {
                click(item, view)
            }

            override fun onBtnClick(item: Any?, view: View) {
                btnClick(item, view)
            }
        }
    }

    abstract class BaseCrudViewHolder(protected val view:View): RecyclerView.ViewHolder(view) {
        var onClick: OnClick? = null
        var item: Any? = null

        init {
            view.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        protected abstract fun onBind(item: Any)

        fun bind(item: Any) {
            this.item = item

            onBind(item)
        }
    }
}