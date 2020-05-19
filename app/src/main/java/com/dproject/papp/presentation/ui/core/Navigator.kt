package com.dproject.papp.presentation.ui.core

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.dproject.papp.presentation.ui.*
import javax.inject.Inject
import javax.inject.Singleton


enum class DialogType {
    Error , Done
}
@Singleton
class Navigator @Inject constructor(private val authenticator: Authenticator) {
    fun showMain(context: Context) {
        authenticator.accountLogIn({
            showHome(context)
        },{
            showSignIn(context)
        })
    }
    fun showHome(context: Context?) = context?.startActivity<HomeActivity>(newTask = true)
    fun showSignUp(context: Context) = context.startActivity<RegisterActivity>()
    fun showCreateCity(context: Context) = context.startActivity<CreateCityActivity>()
    fun showChangeCityActivity(context: Context, cityId:Int) {
        val bundle = Bundle()
        bundle.putInt(ChangeCityActivity.INTENT_EXTRA_PARAM_CITY_ID, cityId)
        context.startActivity<ChangeCityActivity>(args = bundle)
    }
    fun showSchoolActivity(context: Context, cityId: Int) {
        val bundle = Bundle()
        bundle.putInt(SchoolsFragment.INTENT_EXTRA_PARAM_CITY_ID, cityId)
        context.startActivity<SchoolsActivity>(args = bundle)
    }
    fun showSchoolChangeActivity(context: Context, schoolId: Int) {
        val bundle = Bundle()
        bundle.putInt(SchoolChangeActivity.INTENT_EXTRA_PARAM_SCHOOL_ID, schoolId)
        context.startActivity<SchoolChangeActivity>(args = bundle)
    }

    fun showSchoolAddActivity(context: Context, cityId: Int) {
        val bundle = Bundle()
        bundle.putInt(SchoolAddFragment.INTENT_EXTRA_PARAM_CITY_ID, cityId)
        context.startActivity<SchoolAddActivity>(args = bundle)
    }
    fun showStudentGroupActivity(context: Context, schoolId: Int) {
        val bundle = Bundle()
        bundle.putInt(StudentGroupFragment.INTENT_EXTRA_PARAM_SCHOOL_ID, schoolId)
        context.startActivity<StudentGroupActivity>(args = bundle)
    }
    fun showStudentGroupAddActivity(context: Context, schoolId: Int) {
        val bundle = Bundle()
        bundle.putInt(StudentGroupAddFragment.INTENT_EXTRA_PARAM_SCHOOL_ID, schoolId)
        context.startActivity<StudentGroupAddActivity>(args = bundle)
    }
    fun showStudentGroupChangeActivity(context: Context, studentGroupId: Int) {
        val bundle = Bundle()
        bundle.putInt(StudentGroupChangeActivity.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID, studentGroupId)
        context.startActivity<StudentGroupChangeActivity>(args = bundle)
    }
    fun showStudentActivity(context: Context, studentGroupId: Int) {
        val bundle = Bundle()
        bundle.putInt(StudentFragment.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID, studentGroupId)
        context.startActivity<StudentActivity>(args = bundle)
    }
    fun showStudentAddActivity(context: Context, studentGroupId: Int) {
        val bundle = Bundle()
        bundle.putInt(StudentAddFragment.INTENT_EXTRA_PARAM_STUDENT_GROUP_ID, studentGroupId)
        context.startActivity<StudentAddActivity>(args = bundle)
    }
    fun showStudentChangeActivity(context: Context, studentId: Int) {
        val bundle = Bundle()
        bundle.putInt(StudentChangeActivity.INTENT_EXTRA_PARAM_STUDENT_ID, studentId)
        context.startActivity<StudentChangeActivity>(args = bundle)
    }

    fun showCharacteristicActivity(context: Context, personaValueGroupId: Int) {
        val bundle = Bundle()
        bundle.putInt(CharacteristicFragment.INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP, personaValueGroupId)
        context.startActivity<CharacteristicActivity>(args = bundle)
    }
    fun showCharacteristicAddActivity(context: Context, personaValueGroupId: Int) {
        val bundle = Bundle()
        bundle.putInt(CharacteristicAddFragment.INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP_ID, personaValueGroupId)
        context.startActivity<CharacteristicAddActivity>(args = bundle)
    }
    fun showCharacteristicChangeActivity(context: Context, characteristicId: Int) {
        val bundle = Bundle()
        bundle.putInt(CharacteristicChangeActivity.INTENT_EXTRA_PARAM_CHAR_ID, characteristicId)
        context.startActivity<CharacteristicChangeActivity>(args = bundle)
    }

    fun showSignIn(context: Context) = context.startActivity<LoginActivity>(newTask = true)

    fun showDialog(context: Context?, errorMessage:String, dialogType: DialogType) {
        showDialogWithAction(context, errorMessage, dialogType) {}
    }

    fun showDialogWithAction(context: Context?, errorMessage:String, dialogType: DialogType, action: () -> Unit) {
        AlertDialog
            .Builder(context)
            .setTitle(dialogType.toString())
            .setMessage(errorMessage)
            .setPositiveButton(android.R.string.yes) { _: DialogInterface, _: Int ->
                action()
            }
            .setCancelable(false)
            .show()
    }

    private inline fun <reified T> Context.startActivity(newTask: Boolean = false, args: Bundle? = null) {
        this.startActivity(Intent(this, T::class.java).apply {
            if (newTask) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            putExtra("args", args)
        })
    }
}