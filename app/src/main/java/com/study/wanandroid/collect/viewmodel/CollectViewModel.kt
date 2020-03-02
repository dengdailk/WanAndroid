package com.study.wanandroid.collect.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.collect.data.CollectRepository
import com.study.wanandroid.collect.data.CollectRsp
import com.study.wanandroid.common.article.viewmodel.ArticleViewModel

/**
 * @author dengdai
 * @description
 */
class CollectViewModel(application: Application) : ArticleViewModel<CollectRepository>(application) {

    val mConnectArticleData : MutableLiveData<BaseResponse<CollectRsp>> = MutableLiveData()
    val mUnConnectArticleData :MutableLiveData<BaseResponse<Any>> = MutableLiveData()

    fun getCollectArticles(page :Int){
        mRespository.getCollectArticles(page,mConnectArticleData)
    }

    fun unCollect(id : Int,originId : Int){
        mRespository.unCollect(id,originId,mUnConnectArticleData)
    }

}