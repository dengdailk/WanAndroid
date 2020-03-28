package com.study.wanandroid.system.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.wanandroid.R
import com.study.wanandroid.common.adapter.SystemMenuAdapter
import com.study.wanandroid.common.article.view.ArticleFragment
import com.study.wanandroid.system.data.SecondMenuRsp
import com.study.wanandroid.system.data.TopMenu
import com.study.wanandroid.system.data.TopMenuRsp
import com.study.wanandroid.system.viewmodel.SystemViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import org.jetbrains.anko.support.v4.startActivity

class SystemFragment : ArticleFragment<SystemViewModel>() {


    private val systemAdapter: SystemMenuAdapter by lazy {
        SystemMenuAdapter(
            R.layout.item_system_top_menu_content,
            R.layout.item_system_top_menu_head,
            null
        )
    }

    override fun initData() {
        super.initData()
        mViewModel.getTopMenu()
    }

    override fun initView() {
        super.initView()
        msrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        msrlRefresh.setOnRefreshListener {
            onRefreshData()
        }

        mRvArticle.layoutManager = GridLayoutManager(activity, 3)
        mRvArticle.adapter = systemAdapter

        systemAdapter.setOnItemChildClickListener { adapter, view, position ->
            val data = (adapter.data[position] as TopMenu).children
            val topTitle = (adapter.data[position] as TopMenu).header
            val ids = arrayListOf<Int>()
            val titles = arrayListOf<String>()
            data.forEach {
                ids.add(it.id)
                titles.add(it.name)
            }
            startActivity<SystemArticleActivity>(
                "ids" to ids,
                "titles" to titles,
                "topTitle" to topTitle
            )
        }
        //无加载更多
        systemAdapter.setEnableLoadMore(false)
    }

    override fun dataObserver() {
        super.dataObserver()
        mViewModel.mTopMenuData.observe(this, Observer {
            buildTopMenu(it?.data)
        })
    }

    private fun buildTopMenu(data: List<TopMenuRsp>?) {
        val list = arrayListOf<TopMenu>()
        data?.forEach {
            list.add(TopMenu(true, it.name, it.children.isNotEmpty(), it.children))
            it.children.forEach { secondMenuRsp ->
                list.add(TopMenu(SecondMenuRsp(secondMenuRsp.name, secondMenuRsp.id)))
            }
        }
        if (msrlRefresh.isRefreshing) {
            msrlRefresh.isRefreshing = false
        }
        systemAdapter.setNewData(list)
    }

    override fun onRefreshData() {
        mViewModel.getTopMenu()
    }

    override fun onLoadMore() {
        //无加载更多
    }

}
