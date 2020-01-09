package com.study.wanandroid.account.data.login

import android.content.Context
import com.study.common.state.UserState
import com.study.common.state.collect.CollectListener
import com.study.wanandroid.collect.view.CollectActivity
import org.jetbrains.anko.startActivity

/**
 * @author Laizexin on 2019/12/3
 * @description 登录状态
 */
class LoginState : UserState {

    override fun goCollectActivity(context: Context?) {
        context?.startActivity<CollectActivity>()
    }

    override fun login(context: Context?) {
    }

    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        listener.collect(position)
    }
}