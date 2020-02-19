package com.study.wanandroid.about.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseRepository
import com.study.common.common.State
import com.study.wanandroid.common.CacheManager


/**
 * @author Laizexin on 2019/12/31
 * @description
 */
class AboutRepository(val loadState : MutableLiveData<State>) : BaseRepository() {

    fun getCacheSize(liveData: MutableLiveData<String>,context: Context) {
        liveData.postValue(CacheManager.getCacheSize(context))
    }

    fun clearCache(liveData: MutableLiveData<String>, context: Context) {
        CacheManager.clearCache(context)
        liveData.postValue(CacheManager.getCacheSize(context))
    }

}