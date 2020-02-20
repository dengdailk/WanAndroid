package com.study.wanandroid.search.data

import com.study.wanandroid.common.article.data.Article

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class SearchResultRsp (
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)