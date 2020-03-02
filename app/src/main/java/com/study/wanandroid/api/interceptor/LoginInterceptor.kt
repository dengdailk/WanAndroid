package com.study.wanandroid.api.interceptor

import com.study.common.constant.Constant
import com.study.common.utils.Preference
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author dengdai
 * @date 2020/3/2.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
public class LoginInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val domain = request.url.host

        if(domain.isNotEmpty()){
            val mCookie by Preference(domain,"")
            if(mCookie.isNotEmpty()){
                builder.addHeader(Constant.COOKIE_NAME,mCookie)
            }
        }
        return chain.proceed(builder.build())
    }
}