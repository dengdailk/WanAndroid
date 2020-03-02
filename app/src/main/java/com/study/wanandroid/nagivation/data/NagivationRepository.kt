package com.study.wanandroid.nagivation.data

import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.api.ApiRepository
import com.study.wanandroid.api.BaseObserver

/**
 * @author dengdai
 * @description
 */
class NagivationRepository(val loadState : MutableLiveData<State>) : ApiRepository(){

    fun getCategory(liveData :  MutableLiveData<BaseResponse<List<NagivationCategoryRsp>>>) {
        apiService.getCategory().execute(BaseObserver(liveData,loadState,this))
    }
}