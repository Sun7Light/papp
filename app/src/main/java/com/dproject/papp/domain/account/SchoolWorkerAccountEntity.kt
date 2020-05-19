package com.dproject.papp.domain.account

open class SchoolWorkerAccountEntity(id:Int, uid:String, accessToken:String, client:String, var schoolId:Int): AccountEntity(id,uid,accessToken,client) {
}