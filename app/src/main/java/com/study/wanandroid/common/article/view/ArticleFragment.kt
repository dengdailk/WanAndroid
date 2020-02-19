package com.study.wanandroid.common.article.view

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.common.base.LifecycleFragment
import com.study.common.state.collect.CollectListener
import com.study.common.state.collect.CollectRefreshListener
import com.study.common.state.collect.CollectState
import com.study.common.state.login.LoginSucListener
import com.study.common.state.login.LoginSucState
import com.study.wanandroid.MainActivity
import com.study.wanandroid.R
import com.study.wanandroid.WebActivity
import com.study.wanandroid.account.data.UserContext
import com.study.wanandroid.common.adapter.ArticleAdapter
import com.study.wanandroid.common.article.data.Article
import com.study.wanandroid.common.article.viewmodel.ArticleViewModel
import com.study.wanandroid.common.behavior.HideScrollListener
import kotlinx.android.synthetic.main.fragment_article.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * @author dengdai
 * @description
 */
abstract class ArticleFragment<T : ArticleViewModel<*>> : LifecycleFragment<T>(), CollectListener,
    LoginSucListener,
    CollectRefreshListener {

    private var current = 0
    private var collectState = false
    private lateinit var mActivity : Activity

    lateinit var mArticleAdapter : ArticleAdapter

    override fun getLayoutId(): Int = R.layout.fragment_article

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = activity as Activity
    }

    override fun initView() {
        super.initView()
        initRefresh()
        initRecycleView()
    }

    private fun initRecycleView() {
        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mArticleAdapter = ArticleAdapter(R.layout.item_article,null)
        mRvArticle.adapter = mArticleAdapter

        //加载更多
        mArticleAdapter.setEnableLoadMore(true)
        mArticleAdapter.setOnLoadMoreListener({onLoadMore()},mRvArticle)

        mArticleAdapter.setOnItemClickListener { adapter, view, position ->

            val item = mArticleAdapter.getItem(position)
            item?.let {
                startActivity<WebActivity>("url" to it.link, "title" to it.title)
            }
        }

        mArticleAdapter.setOnItemChildClickListener { adapter, view, position ->
            run {
                if (view.id == R.id.mIvCollect) {
                    UserContext.instance.collect(activity, position, this)
                }
            }
        }

        mRvArticle.addOnScrollListener(object : HideScrollListener(){
            override fun onHide() {
                if(activity is MainActivity){
                    (mActivity as MainActivity).onHide()
                }
            }

            override fun onShow() {
                if(activity is MainActivity){
                    (mActivity as MainActivity).onShow()
                }
            }

        })

        LoginSucState.addListener(this)
        CollectState.addListener(this)
    }

    abstract fun onRefreshData()

    abstract fun onLoadMore()

    private fun initRefresh() {
        msrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        msrlRefresh.setOnRefreshListener {
            onRefreshData()
        }
    }

    override fun dataObserver() {
        mViewModel.mCollectData.observe(this, Observer {
            val item = mArticleAdapter.getItem(current)
            item?.let {
                it.collect = !collectState
                mArticleAdapter.refreshNotifyItemChanged(current)
            }
        })
    }

    fun addData(dates: List<Article>) {
        if (dates.isEmpty()) {
            mArticleAdapter.loadMoreEnd()
            return
        }

        //下拉刷新 注意完成加载更多(存在加载更多时刷新的情况)
        if(msrlRefresh.isRefreshing){
            msrlRefresh.isRefreshing = false
            mArticleAdapter.setNewData(dates)
            mArticleAdapter.loadMoreComplete()
            return
        }

        //加载更多
        mArticleAdapter.addData(dates)
        mArticleAdapter.loadMoreComplete()
    }

    override fun collect(position: Int) {
        val item = mArticleAdapter.getItem(position)
        item?.let {
            current = position
            collectState = it.collect
            if(it.collect) mViewModel.unCollect(it.id) else mViewModel.collect(it.id)
        }
    }

    override fun loginSuccess(username: String, collectIds: List<Int>?) {
        //更新收藏 如果collectIds存在不存在都要做

        collectIds?.let { it ->
            it.forEach {id ->
                mArticleAdapter.data.forEach {
                    if(it.id == id){
                        it.collect = true
                    }
                }
            }
        } ?: let {
            mArticleAdapter.data.forEach {
                if(it.collect){
                    it.collect = false
                }
            }
        }
        mArticleAdapter.notifyDataSetChanged()
    }

    override fun onCollectRefresh(id: Int, originId: Int) {
        msrlRefresh.isRefreshing = true
        reLoad()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSucState.removeListener(this)
        CollectState.removeListener(this)
    }
}