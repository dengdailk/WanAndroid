package com.study.common.https

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.promisesBody
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit

/**
 * @author Laizexin on 2019/12/3
 * @description
 */
class LoggingInterceptor : Interceptor {
    private val UTF8 = Charset.forName("UTF-8")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body
        var body: String? = null
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset = UTF8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            body = buffer.readString(charset!!)
        }
        Logger.d(
            "发送请求\nmethod：%s\nurl:%s\nheaders:%sbody:%s",
            request.method,
            request.url,
            request.headers,
            body
        )
        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs =
            TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val responseBody = response.body
        var rBody: String? = null
        if (response.promisesBody()) {
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            var charset = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8)
                } catch (e: UnsupportedCharsetException) {
                    e.printStackTrace()
                }
            }
            rBody = buffer.clone().readString(charset!!)
        }
        Logger.d(
            "收到响应 %s%s %ss\n请求url:%s\n请求body:%s\n请求header:%s响应body:",
            response.code, response.message, tookMs, response.request.url, body, request.headers
        )
        Logger.json(rBody)
        return response
    }
}