package com.study.wanandroid.system.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.common.https.BaseResponse
import com.study.wanandroid.common.article.viewmodel.ArticleViewModel
import com.study.wanandroid.system.data.SystemAtricleRsp
import com.study.wanandroid.system.data.SystemRepository
import com.study.wanandroid.system.data.TopMenuRsp

class SystemViewModel(application: Application) : ArticleViewModel<SystemRepository>(application) {

    val mTopMenuData:MutableLiveData<BaseResponse<List<TopMenuRsp>>> = MutableLiveData()
    val mArticleData:MutableLiveData<BaseResponse<SystemAtricleRsp>> = MutableLiveData()
    fun getTopMenu(){
        mRespository.getTopMenu(mTopMenuData)
    }
    fun getSystemArticles(id:Int,page:Int){
        mRespository.getSystemArticles(page,id,mArticleData)
    }
}
