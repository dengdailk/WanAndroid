package com.study.common.base

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.study.common.callback.EmptyCallback
import com.study.common.callback.ErrorCallback
import com.study.common.callback.LoadingCallback
import com.study.common.utils.Preference

/**
 * @author dengdai
 * @date 2020/1/6.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(LoadingCallback())
            .addCallback(EmptyCallback())
            .commit()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("WanAndroid >>> ")
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))


        Preference.setContext(applicationContext)
        }

}