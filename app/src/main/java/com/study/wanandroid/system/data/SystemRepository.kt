package com.study.wanandroid.system.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.api.BaseObserver
import com.study.wanandroid.common.article.data.ArticleRepository

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class SystemRepository(loadState:MutableLiveData<State>):ArticleRepository(loadState) {

    fun getTopMenu(liveData:MutableLiveData<BaseResponse<List<TopMenuRsp>>>){
        apiService.getTopMenu().execute(BaseObserver(liveData,loadState,this))
    }
    fun getSystemArticles(page:Int,id:Int,liveData:MutableLiveData<BaseResponse<SystemAtricleRsp>>){
        apiService.getSystemArticles(page,id).execute(BaseObserver(liveData,loadState,this))
    }
}
