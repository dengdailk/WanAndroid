package com.study.wanandroid.account.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kkaka.wanandroid.account.data.regist.RegisterRsp
import com.study.common.base.BaseViewModel
import com.study.common.common.State
import com.study.common.common.StateType
import com.study.common.https.BaseResponse
import com.study.wanandroid.R
import com.study.wanandroid.account.data.AccountRepository
import com.study.wanandroid.account.data.login.LoginRsp


/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class AccountViewModel(application: Application) : BaseViewModel<AccountRepository>(application) {

    val mLoginData = MutableLiveData<BaseResponse<LoginRsp>>()
    val mRegistData = MutableLiveData<BaseResponse<RegisterRsp>>()

    fun login(userName :String,password :String){
        mRespository.login(userName,password,mLoginData)
    }

    fun regist(userName :String,password: String,repassword: String){
        if(password != repassword){
            loadState.postValue(State(StateType.TIP,msg = getApplication<Application>().resources.getString(
                R.string.password_ensure_error)))
        }else{
            mRespository.regist(userName,password,repassword,mRegistData)
        }
    }
}