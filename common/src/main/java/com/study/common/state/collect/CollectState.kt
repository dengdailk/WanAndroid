package com.study.common.state.collect

/**
 * @author dengdai
 * @description 收藏页更新回调首页更新
 */
object CollectState {

    private var listeners = ArrayList<CollectRefreshListener>()

    fun addListener(listener: CollectRefreshListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: CollectRefreshListener) {
        listeners.remove(listener)
    }

    fun notifyCollectState(id : Int,originId : Int) {
        for (listener in listeners) {
            listener.onCollectRefresh(id, originId)
        }
    }
}