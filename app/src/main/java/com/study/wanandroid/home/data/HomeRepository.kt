package com.study.wanandroid.home.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.common.https.BaseResponse
import com.study.wanandroid.api.BaseObserver
import com.study.wanandroid.common.article.data.ArticleRepository

/**
 * @author dengdai
 * @date 2020/1/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class HomeRepository(loadState:MutableLiveData<State>) : ArticleRepository(loadState) {
    fun getArticle(page:Int,liveDate:MutableLiveData<BaseResponse<HomeArticleRsp>>){
        apiService.getHomeArticle(page).execute(BaseObserver(liveDate,loadState,this))
    }
    fun getBanner(liveDate : MutableLiveData<BaseResponse<List<BannerRsp>>>){
        apiService.getBanner().execute(BaseObserver(liveDate,loadState,this))
    }
}