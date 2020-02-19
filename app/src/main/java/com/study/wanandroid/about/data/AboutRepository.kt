package com.study.wanandroid.about.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseRepository
import com.study.wanandroid.common.CacheManager


/**
 * @author
 * @description
 */
class AboutRepository : BaseRepository() {

    fun getCacheSize(liveData: MutableLiveData<String>,context: Context) {
        liveData.postValue(CacheManager.getCacheSize(context))
    }

    fun clearCache(liveData: MutableLiveData<String>, context: Context) {
        CacheManager.clearCache(context)
        liveData.postValue(CacheManager.getCacheSize(context))
    }

}