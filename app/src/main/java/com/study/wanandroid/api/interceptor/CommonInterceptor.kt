package com.study.wanandroid.api.interceptor

import android.os.Parcel
import android.os.Parcelable
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author dengdai
 * @date 2020/3/2.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class CommonInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("charset", "UTF-8")
            .build()

        return chain.proceed(request)
    }

}