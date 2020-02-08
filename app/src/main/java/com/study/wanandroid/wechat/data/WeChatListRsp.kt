package com.study.wanandroid.wechat.data

import com.study.wanandroid.common.article.data.Article


data class WeChatListRsp(
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)