package com.study.wanandroid.collect.view


import androidx.lifecycle.Observer
import com.study.wanandroid.R
import com.study.wanandroid.account.data.UserContext
import com.study.wanandroid.collect.viewmodel.CollectViewModel
import com.study.wanandroid.common.article.view.ArticleActivity


class CollectActivity : ArticleActivity<CollectViewModel>() {

    override fun isAddToolbar(): Boolean = true

    private var index = 0
    private var id : Int = 0
    private var originId : Int = 0

    override fun headTitle(): Int {
        return R.string.collect
    }

    private var page = 0

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getCollectArticles(page)
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.getCollectArticles(page)
    }

    override fun onLoadMore() {
        mViewModel.getCollectArticles(++page)
    }

    override fun dataObserver() {
        mViewModel.mConnectArticleData.observe(this, Observer {
            it?.let {
                it.data.datas.forEach { article ->
                    article.collect = true
                }
                addData(it.data.datas)
            }
        })

        mViewModel.mUnConnectArticleData.observe(this, Observer {
            mArticleAdapter.remove(index)
            UserContext.instance.collectRefresh(id,originId)
        })
    }

    override fun collect(position: Int) {
        val item = mArticleAdapter.getItem(position)
        item?.let {
            index = position
            id = it.id
            originId = it.originId
            mViewModel.unCollect(it.id,it.originId)
        }
    }


}
