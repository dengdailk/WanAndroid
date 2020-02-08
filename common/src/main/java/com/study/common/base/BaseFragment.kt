package com.study.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.study.common.callback.EmptyCallback
import com.study.common.callback.ErrorCallback
import com.study.common.callback.LoadingCallback

/**
 * @author Laizexin on 2019/11/29
 * @description
 */
abstract class BaseFragment : Fragment() {

    lateinit var loadService : LoadService<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(), null)
        val loadSir = LoadSir.Builder()
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .build()
        loadService = loadSir.register(rootView) {reLoad()}
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    open fun reLoad(){
        initData()
    }

    open fun initData() {
    }

    abstract fun getLayoutId() :Int

}