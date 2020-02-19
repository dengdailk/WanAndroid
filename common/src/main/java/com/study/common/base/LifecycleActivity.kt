package com.study.common.base

import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.study.common.callback.EmptyCallback
import com.study.common.callback.ErrorCallback
import com.study.common.callback.LoadingCallback
import com.study.common.common.State
import com.study.common.common.StateType
import com.study.common.utils.Util
import org.jetbrains.anko.toast

/**
 * @author Laizexin on 2019/12/3
 * @description
 */
abstract class LifecycleActivity <T : BaseViewModel<*>> : BaseActivity() {

    lateinit var mViewModel : T
    lateinit var loadService : LoadService<*>
    override fun initView() {
        val loadSir = LoadSir.Builder()
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .setDefaultCallback(LoadingCallback::class.java)
            .build()
        loadService = loadSir.register(this) {reLoad()}
        showLoading()
        mViewModel = ViewModelProvider(this).get(Util.getClass(this))
        mViewModel.loadState.observe(this,observer)

        //设置view的observer
        dataObserver()
    }

    abstract fun dataObserver()

    private fun reLoad() {
        showLoading()
    }

    open fun showLoading() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    open fun showSuccess(){
        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showEmpty(){
        loadService.showCallback(EmptyCallback::class.java)
    }

    open fun showError(msg : String){
        if(!TextUtils.isEmpty(msg)){
            toast(msg)
        }
        loadService.showCallback(ErrorCallback::class.java)
    }

    open fun showTip(msg: String){
        if(!TextUtils.isEmpty(msg)){
            toast(msg)
        }
        loadService.showCallback(SuccessCallback::class.java)
    }

    private val observer by lazy {
        Observer<State> {
            it?.let {
                when(it.code){
                    StateType.EMPTY -> showEmpty()
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showTip(it.msg)
                    StateType.NETWORK_ERROR -> showError("网络异常")
                    StateType.TIP -> showTip(it.msg)
                }
            }
        }
    }
}