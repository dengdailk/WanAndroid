package com.study.wanandroid.search.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.common.constant.Constant
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.common.article.viewmodel.ArticleViewModel
import com.study.wanandroid.search.data.HotSearchRsp
import com.study.wanandroid.search.data.SearchRepository
import com.study.wanandroid.search.data.SearchResultRsp
import com.study.wanandroid.search.data.db.Record
import com.study.wanandroid.search.data.db.RecordDatabase

/**
 * @author dengdai
 * @description
 */
class SearchViewModel(application: Application) : ArticleViewModel<SearchRepository>(application){

    val mSearchData : MutableLiveData<BaseResponse<SearchResultRsp>> = MutableLiveData()
    val mHotKeyData : MutableLiveData<BaseResponse<List<HotSearchRsp>>> = MutableLiveData()
    val mSearchHistory : MutableLiveData<List<Record>> = MutableLiveData()

    private var dataBase = RecordDatabase(application)

    fun search(page: Int, str: String){
        mRespository.search(page,str,mSearchData)
    }

    fun getHotSearch() {
        mRespository.getHotSearch(mHotKeyData)
    }

    fun getHistorySearch() {
        mSearchHistory.postValue(dataBase.recordDao().getAll())
    }

    fun addHistorySearch(str: String) {
        val datas = dataBase.recordDao().getAll()
        /**
         * 这里筛选 如果存在关键字了 则删除能筛选到的关键字记录
         * 如果不存在 判断现在记录个数 如果 超过了最大限制 则取出最后一条(指数组中)删除
         */

        val data = datas.filter { record ->
            return@filter record.name == str
        }.getOrElse(0) {
            return@getOrElse if (datas.size >= Constant.SEARCH_HISTORY_MAX) datas[0] else Record(
                -1,
                ""
            )
        }

        dataBase.recordDao().delete(data)
        dataBase.recordDao().insert(Record(0,str))
        mSearchHistory.postValue(dataBase.recordDao().getAll())
    }

    fun clearHistorySearch(){
        val datas = dataBase.recordDao().getAll()
        datas.forEach {
            dataBase.recordDao().delete(it)
        }
        mSearchHistory.postValue(dataBase.recordDao().getAll())
    }

}