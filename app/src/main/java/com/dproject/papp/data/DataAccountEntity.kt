package com.dproject.papp.data

import com.google.gson.annotations.SerializedName

class DataAccountEntity(var id:Int, var uid:String, var accessToken:String, var client:String, var type: AccountType, @SerializedName("school_id") var schoolId:Int, @SerializedName("student_id") var studentId:Int) {
    companion object {
        fun empty() = DataAccountEntity(0,"","","",AccountType.Admin,0,0)
    }
}
enum class AccountType {
    Admin, SchoolAdmin, StudentUser, SchoolPsychologist, Psychologist
}