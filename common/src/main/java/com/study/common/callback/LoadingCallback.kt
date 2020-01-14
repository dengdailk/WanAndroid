package com.study.common.callback

import com.kingja.loadsir.callback.Callback
import com.study.common.R

/**
 * @author dengdai
 * @date 2020/1/6.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int{
       return R.layout.layout_loading
    }
}