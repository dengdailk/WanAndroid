package com.study.common.utils

import android.util.Log

/**
 * @author dengdai
 * @date 2020/2/8.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class LogUtil {
    companion object {

        /**Log的前缀*/
        private var tagPrefix: String = "sunbufuLogUtil"

        /**日志是否打印的标识*/
        val flag: Boolean = true

        fun d(a: Any) = log("d", a)
        fun i(a: Any) = log("i", a)
        fun w(a: Any) = log("w", a)
        fun e(a: Any) = log("e", a)

        /**
         * @param type   级别
         * @param any   打印的对象
         */
        private fun log(type: String, any: Any) {
            val msg = any.toString()
            val tag = getTag(getCallerStackTraceElement())
            when (type) {
                "d" -> Log.d(tag, msg)
                "i" -> Log.i(tag, msg)
                "w" -> Log.w(tag, msg)
                "e" -> Log.e(tag, msg)
            }
        }

        /**生成TAG*/
        private fun getTag(element: StackTraceElement): String {
            //获取类名（去掉包名）
            var callerClazzName: String = element.className
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
            //生成TAG
            return "$tagPrefix:$callerClazzName.${element.methodName}(${element.lineNumber})"
        }

        /**获取函数堆栈*/
        private fun getCallerStackTraceElement(): StackTraceElement = Thread.currentThread().stackTrace[5]
    }
}