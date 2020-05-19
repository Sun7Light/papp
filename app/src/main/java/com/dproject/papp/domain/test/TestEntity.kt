package com.dproject.papp.domain.test

class TestEntity(var id:Int, var studentGroupId: Int, var start:String, var finish:String) {
    companion object {
        fun empty() = TestEntity(0,0,"","")
    }
}