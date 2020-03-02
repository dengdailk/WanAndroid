package com.study.wanandroid.api

/**
 * @author dengdai
 * @description
 */
open class BaseResponse<T>(var data: T, var errorCode: Int = -1, var errorMsg: String = "")
