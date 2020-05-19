package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import com.dproject.papp.presentation.ui.core.BaseActivity
import com.dproject.papp.presentation.ui.core.BaseFragment

class StudentActivity: BaseActivity() {

    override var fragment: BaseFragment = StudentFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}