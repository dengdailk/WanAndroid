package com.study.common.base

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.callback.SuccessCallback
import com.study.common.callback.EmptyCallback
import com.study.common.callback.ErrorCallback
import com.study.common.callback.LoadingCallback
import com.study.common.common.State
import com.study.common.common.StateType
import com.study.common.utils.Util

/**
 * @author dengdai
 * @description
 */
abstract class LifecycleFragment<T : BaseViewModel<*>> : BaseFragment() {

    lateinit var mViewModel : T
    override fun initView() {
        showLoading()
        mViewModel = ViewModelProvider(this).get(Util.getClass(this))
        mViewModel.loadState.observe(this,observer)
        //设置view的observer
        dataObserver()
    }

    abstract fun dataObserver()

    override fun reLoad() {
        super.reLoad()
        showLoading()


    }

    private fun showLoading() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    private fun showSuccess(){
        loadService.showCallback(SuccessCallback::class.java)
    }

    private fun showEmpty(){
        loadService.showCallback(EmptyCallback::class.java)
    }

    @SuppressLint("ShowToast")
    private fun showError(msg : String){
        if(!TextUtils.isEmpty(msg)){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        }
        loadService.showCallback(ErrorCallback::class.java)
    }

    @SuppressLint("ShowToast")
    open fun showTip(msg: String){
        if(!TextUtils.isEmpty(msg)){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT)
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