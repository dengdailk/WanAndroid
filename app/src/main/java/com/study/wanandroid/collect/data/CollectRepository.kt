package com.study.wanandroid.collect.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.common.https.BaseResponse
import com.study.wanandroid.api.BaseObserver
import com.study.wanandroid.common.article.data.ArticleRepository


/**
 * @author dengdai
 * @description
 */
class CollectRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getCollectArticles(page : Int, liveData: MutableLiveData<BaseResponse<CollectRsp>>){
        apiService.getCollectArticle(page).execute(BaseObserver(liveData,loadState,this))
    }

    fun unCollect(id: Int, originId: Int, liveData: MutableLiveData<BaseResponse<Any>>){
        apiService.unMyCollect(id,originId).execute(BaseObserver(liveData,loadState,this))
    }

}