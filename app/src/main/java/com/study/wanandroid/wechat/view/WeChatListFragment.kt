package com.study.wanandroid.wechat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.study.wanandroid.common.article.view.ArticleFragment
import com.study.wanandroid.wechat.viewmoodel.WeChatViewModel


/**
 * @author Laizexin on 2019/12/9
 * @description
 */
class WeChatListFragment : ArticleFragment<WeChatViewModel>() {

    private var page = 0
    private val uid by lazy { arguments?.getInt("uid") ?: -1 }

    companion object {
        fun newInstance(uid: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("uid", uid)
            val fragment = WeChatListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getWeChatList(uid, page)
    }

    override fun onLoadMore() {
        mViewModel.getWeChatList(uid, ++page)
    }

    override fun onRefreshData() {
        page = 1
        mViewModel.getWeChatList(uid, page)
    }

    override fun dataObserver() {
        super.dataObserver()

        mViewModel.mWeChatListData.observe(this, Observer {
            it?.let {
                addData(it.data.datas)
            }
        })
    }
}