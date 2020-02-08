package com.study.wanandroid.api

import com.study.common.base.BaseRepository
import com.study.common.https.RetrofitFactory


/**
 * @author Laizexin on 2019/12/2
 * @description
 */
open class ApiRepository : BaseRepository() {

    val apiService: ApiService by lazy {
        RetrofitFactory.instance.create(ApiService::class.java)
    }

}