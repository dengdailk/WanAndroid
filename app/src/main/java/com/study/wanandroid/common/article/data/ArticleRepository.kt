package com.study.wanandroid.common.article.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.api.ApiRepository
import com.study.wanandroid.api.BaseObserver


/**
 * @author dengdai
 * @description
 */
open class ArticleRepository(val loadState : MutableLiveData<State>) : ApiRepository(){

    fun collect(position: Int,liveData : MutableLiveData<BaseResponse<Any>>){
        apiService.collect(position).execute(BaseObserver(liveData,loadState,this))
    }

    fun unCollect(position: Int,liveData: MutableLiveData<BaseResponse<Any>>){
        apiService.unCollect(position).execute(BaseObserver(liveData,loadState,this))
    }

}