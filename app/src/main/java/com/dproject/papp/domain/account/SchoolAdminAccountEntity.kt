package com.dproject.papp.domain.account

class SchoolAdminAccountEntity (id:Int, uid:String, accessToken:String, client:String, schoolId:Int): SchoolWorkerAccountEntity(id,uid,accessToken,client,schoolId) {
    override fun toString(): String {
        return "Школьный администратор"
    }
}