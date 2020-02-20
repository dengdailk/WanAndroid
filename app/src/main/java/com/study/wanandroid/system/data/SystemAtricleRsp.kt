package com.study.wanandroid.system.data

import com.study.wanandroid.common.article.data.Article

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class SystemAtricleRsp (
    var curPage:Int,
    var datas:List<Article>,
    var pageCount:Int,
    var total:Int
)