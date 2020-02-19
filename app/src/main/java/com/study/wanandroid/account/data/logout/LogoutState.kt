package com.study.wanandroid.account.data.logout

import android.content.Context
import com.study.common.state.UserState
import com.study.common.state.collect.CollectListener
import com.study.wanandroid.account.view.LoginActivity
import org.jetbrains.anko.startActivity

/**
 * @author
 * @description 未登录状态
 */
class LogoutState : UserState {

    override fun goCollectActivity(context: Context?) {
        goLoginActivity(context)
    }

    override fun login(context: Context?) {
        goLoginActivity(context)
    }

    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        goLoginActivity(context)
    }

    private fun goLoginActivity(context: Context?) {
        context?.startActivity<LoginActivity>()
    }
}