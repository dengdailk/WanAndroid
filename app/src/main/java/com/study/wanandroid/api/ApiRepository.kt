package com.study.wanandroid.api

import com.study.common.base.BaseRepository


/**
 * @author dengdai
 * @description
 */
open class ApiRepository : BaseRepository() {

    val apiService: ApiService by lazy {
        RetrofitFactory.instance.create(ApiService::class.java)
    }

}