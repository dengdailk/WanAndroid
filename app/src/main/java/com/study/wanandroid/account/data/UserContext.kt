package com.study.wanandroid.account.data

import android.content.Context
import com.study.common.constant.Constant
import com.study.common.state.UserState
import com.study.common.state.collect.CollectListener
import com.study.common.state.collect.CollectState
import com.study.common.state.login.LoginSucState
import com.study.common.utils.Preference
import com.study.wanandroid.account.data.login.LoginState
import com.study.wanandroid.account.data.logout.LogoutState

/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class UserContext private constructor(){

    private var isLogin : Boolean by Preference(Constant.LOGIN_KEY,false)

    private var mState: UserState = LoginState()//if (isLogin) LoginState() else LogoutState()

    companion object {
        val instance by lazy { UserContext() }
    }

    fun getInstance() : UserContext {
        return instance
    }

    fun login(context: Context?){
        mState.login(context)
    }

    fun collect(context: Context?, position: Int, listener: CollectListener){
        mState.collect(context,position,listener)
    }

    fun goCollectActivity(context: Context?){
        mState.goCollectActivity(context)
    }

    fun collectRefresh(id : Int,originId : Int){
        CollectState.notifyCollectState(id,originId)
    }

    fun loginSuccess(userName :String,collectIds: List<Int>?){
        isLogin = true
        mState = LoginState()
        LoginSucState.notifyLoginState(userName,collectIds)
    }

    fun logoutSuccess(){
        isLogin = false
        Preference.clear()
        mState = LogoutState()
        LoginSucState.notifyLoginState("未登录",null)
    }

}