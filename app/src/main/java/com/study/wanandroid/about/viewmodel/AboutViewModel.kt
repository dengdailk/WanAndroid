package com.study.wanandroid.about.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseViewModel
import com.study.wanandroid.about.data.AboutRepository

/**
 * @author Laizexin on 2019/12/31
 * @description
 */
class AboutViewModel (application: Application) : BaseViewModel<AboutRepository>(application){

    val mCacheData : MutableLiveData<String> = MutableLiveData()

    fun getCacheSize() {
        mRespository.getCacheSize(mCacheData,getApplication())
    }

    fun clearCache() {
        mRespository.clearCache(mCacheData,getApplication())
    }

}