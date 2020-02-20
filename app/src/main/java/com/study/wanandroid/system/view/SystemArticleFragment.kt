package com.study.wanandroid.system.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.study.wanandroid.common.article.view.ArticleFragment
import com.study.wanandroid.system.viewmodel.SystemViewModel

/**
 * @author dengdai
 * @description
 */
class SystemArticleFragment : ArticleFragment<SystemViewModel>() {

    private val uid by lazy { arguments?.getInt("uid") ?: -1 }
    private var page = 0

    companion object {
        fun newInstance(uid : Int) : Fragment {
            val bundle = Bundle()
            bundle.putInt("uid",uid)
            val fragment = SystemArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getSystemArticles(uid,page)
    }

    override fun onRefreshData() {
        page = 0
        mViewModel.getSystemArticles(uid,page)
    }

    override fun onLoadMore() {
        mViewModel.getSystemArticles(uid,++page)
    }

    override fun dataObserver() {
        super.dataObserver()
        mViewModel.mArticleData.observe(this, Observer {
            it?.let {
                addData(it.data.datas)
            }
        })
    }
}