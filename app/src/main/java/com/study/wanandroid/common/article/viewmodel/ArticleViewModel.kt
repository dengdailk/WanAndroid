package com.study.wanandroid.common.article.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseViewModel
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.common.article.data.ArticleRepository

/**
 * @author dengdai
 * @description 文章类ViewModel 具有收藏功能
 */
open class ArticleViewModel<T : ArticleRepository>(application: Application) : BaseViewModel<T>(application) {

    var mCollectData: MutableLiveData<BaseResponse<Any>> = MutableLiveData()

    fun collect(id : Int){
        mRespository.collect(id,mCollectData)
    }

    fun unCollect(id: Int){
        mRespository.unCollect(id,mCollectData)
    }

}