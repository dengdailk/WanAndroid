package com.study.common.state

import android.content.Context
import com.study.common.state.collect.CollectListener

/**
 * @author dengdai
 * @description
 */
interface UserState {

    fun collect(context: Context?,position : Int,listener: CollectListener)

    fun login(context: Context?)

    fun goCollectActivity(context: Context?)

}