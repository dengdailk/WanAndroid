package com.study.common.https

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
open class BaseResponse<T>(var data: T, var errorCode: Int, var errorMsg: String = "")
