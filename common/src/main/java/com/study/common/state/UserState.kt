package com.study.common.state

import android.content.Context
import com.study.common.state.collect.CollectListener

/**
 * @author Laizexin on 2019/12/3
 * @description
 */
interface UserState {

    fun collect(context: Context?,position : Int,listener: CollectListener)

    fun login(context: Context?)

    fun goCollectActivity(context: Context?)

}