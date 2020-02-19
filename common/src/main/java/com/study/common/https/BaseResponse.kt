package com.study.common.https

/**
 * @author dengdai
 * @description
 */
open class BaseResponse<T>(var data: T, var errorCode: Int = -1, var errorMsg: String = "")
