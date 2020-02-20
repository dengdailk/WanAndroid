package com.study.wanandroid.search.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.common.https.BaseResponse
import com.study.wanandroid.api.BaseObserver
import com.study.wanandroid.common.article.data.ArticleRepository

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class SearchRepository(loadState:MutableLiveData<State>):ArticleRepository(loadState) {
    fun search(page: Int, str: String, liveData: MutableLiveData<BaseResponse<SearchResultRsp>>) {
        apiService.search(page,str).execute(BaseObserver(liveData,loadState,this))
    }

    fun getHotSearch(liveData: MutableLiveData<BaseResponse<List<HotSearchRsp>>>) {
        apiService.getHotKey().execute(BaseObserver(liveData,loadState,this))
    }
}