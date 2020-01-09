package com.study.common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.study.common.common.State
import com.study.common.utils.Util


/**
 * @author Laizexin on 2019/11/28
 * @description
 */
open class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application){

    val loadState by lazy { MutableLiveData<State>() }

    val mRespository : T by lazy {
        (Util.getClass<T>(this)).getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        mRespository.unSubscribe()
    }

}