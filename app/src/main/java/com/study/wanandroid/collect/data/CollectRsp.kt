package com.study.wanandroid.collect.data

import com.study.wanandroid.common.article.data.Article


data class CollectRsp(
        var curPage: Int,
        var datas: List<Article>,
        var total: Int
)