package com.study.wanandroid.nagivation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseViewModel
import com.study.common.https.BaseResponse
import com.study.wanandroid.nagivation.data.NagivationCategoryRsp
import com.study.wanandroid.nagivation.data.NagivationRepository

class NagivationViewModel(application: Application) :
    BaseViewModel<NagivationRepository>(application) {
    val mCategoryData: MutableLiveData<BaseResponse<List<NagivationCategoryRsp>>> =
        MutableLiveData()

    fun getCategory() {
        mRespository.getCategory(mCategoryData)
    }
}
