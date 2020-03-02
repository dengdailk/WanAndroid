package com.study.wanandroid.api

import androidx.lifecycle.MutableLiveData
import com.study.common.base.BaseRepository
import com.study.common.common.State
import com.study.common.common.StateType
import com.study.common.constant.Constant
import com.study.common.utils.LogUtil
import com.study.wanandroid.account.data.UserContext
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author dengdai
 * @description
 */
class BaseObserver<T : BaseResponse<*>>(
    private val liveData: MutableLiveData<T>,
    private val loadState: MutableLiveData<State>,
    private val repository: BaseRepository
) : Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        repository.subscribe(d)
    }

    override fun onNext(response: T) {
        when (response.errorCode) {
            Constant.RESPONSE_SUCCESS -> {
                if (response.data is List<*>) {
                    if ((response.data as List<*>).isEmpty()) {
                        loadState.postValue(State(StateType.EMPTY))
                        return
                    }
                }
                loadState.postValue(State(StateType.SUCCESS))
                liveData.postValue(response)
                LogUtil.d("test1******3"+loadState.value)
            }
            Constant.RESPONSE_NOT_LOGIN -> {
                UserContext.instance.logoutSuccess()
                loadState.postValue(State(StateType.ERROR, msg = "登录过期"))
                LogUtil.d("test1******4"+loadState.value)

            }
            else -> {
                LogUtil.d("test1******5"+response.errorMsg)
                loadState.postValue(State(StateType.ERROR, msg = response.errorMsg))
            }

        }
    }

    override fun onError(e: Throwable) {
//        if (BuildConfig.DEBUG) {
//            e.message?.let { Logger.e(it) }
//        }
        loadState.postValue(State(StateType.NETWORK_ERROR))
    }


}