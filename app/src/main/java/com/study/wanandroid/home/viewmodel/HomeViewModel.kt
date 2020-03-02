package com.study.wanandroid.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.common.article.viewmodel.ArticleViewModel
import com.study.wanandroid.home.data.BannerRsp
import com.study.wanandroid.home.data.HomeArticleRsp
import com.study.wanandroid.home.data.HomeRepository

class HomeViewModel(application: Application) : ArticleViewModel<HomeRepository>(application) {
    val mHomeArticleDate : MutableLiveData<BaseResponse<HomeArticleRsp>> = MutableLiveData()
    val mBannerData : MutableLiveData<BaseResponse<List<BannerRsp>>> = MutableLiveData()

    fun getArticle(page:Int){
        mRespository.getArticle(page,mHomeArticleDate)
    }
    fun getBanner(){
        mRespository.getBanner(mBannerData)
    }
}
