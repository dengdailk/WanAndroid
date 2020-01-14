package com.study.wanandroid.home.data

import com.study.wanandroid.common.article.data.Article

data class HomeArticleRsp (
    var curPage:Int,
    var datas:List<Article>,
    var offset:Int,
    var pageCount:Int,
    var size:Int,
    var total:Int
)
