package com.study.wanandroid.account.data

import androidx.lifecycle.MutableLiveData
import com.kkaka.wanandroid.account.data.regist.RegisterRsp
import com.study.common.common.State
import com.study.common.ext.execute
import com.study.common.https.BaseResponse
import com.study.wanandroid.account.data.login.LoginRsp
import com.study.wanandroid.api.ApiRepository
import com.study.wanandroid.api.BaseObserver


/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class AccountRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun login(userName :String,password :String,liveData: MutableLiveData<BaseResponse<LoginRsp>>){
        apiService.getLogin(userName,password).execute(BaseObserver(liveData,loadState,this))
    }

    fun regist(userName: String,password: String,repassword :String ,liveData: MutableLiveData<BaseResponse<RegisterRsp>>){
        apiService.getRegister(userName,password,repassword).execute(BaseObserver(liveData,loadState,this))
    }
}