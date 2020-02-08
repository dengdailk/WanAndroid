package com.study.wanandroid.account.data.regist

data class RegisterRsp(
        var username: String,
        var id: Int,
        var icon: String,
        var type: Int,
        var collectIds: List<Int>
)