package com.study.common.common

import android.app.Activity
import java.util.*

/**
 * @author dengdai
 * @date 2020/1/6.
 * GitHub：
 * email：291996307@qq.com
 * description：activity管理类
 */
class AppManager {
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance by lazy { AppManager() }
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }fun removeActivity(activity:Activity){}
}

