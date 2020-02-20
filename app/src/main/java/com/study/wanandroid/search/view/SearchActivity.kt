package com.study.wanandroid.search.view

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.study.common.ext.hideKeyboard
import com.study.common.ext.str
import com.study.wanandroid.R
import com.study.wanandroid.common.MyFlexboxLayoutManager
import com.study.wanandroid.common.adapter.HistorySearchAdapter
import com.study.wanandroid.common.adapter.HotSearchAdapter
import com.study.wanandroid.common.article.data.Article
import com.study.wanandroid.common.article.view.ArticleActivity
import com.study.wanandroid.search.data.HotSearchRsp
import com.study.wanandroid.search.data.db.Record
import com.study.wanandroid.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_search_head.view.*

class SearchActivity : ArticleActivity<SearchViewModel>() {
    private var page = 0
    private lateinit var tv: SearchView.SearchAutoComplete
    override fun isAddToolbar(): Boolean = false
    private val mHotSearchAdapter: HotSearchAdapter by lazy { HotSearchAdapter(R.layout.item_lable,null) }
    private val mHistorySearchAdapter: HistorySearchAdapter by lazy { HistorySearchAdapter(R.layout.item_lable,null) }

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()
        initHotSearchView()
        initSearchView()
        initSearchHistory()
    }
    override fun initData() {
        mViewModel.getHotSearch()
        mViewModel.getHistorySearch()
    }
    private fun initHotSearchView() {
        mRvHotSearch.layoutManager = MyFlexboxLayoutManager(this)
        mRvHotSearch.adapter = mHotSearchAdapter
        mHotSearchAdapter.setOnItemChildClickListener { adapter, _, position ->
            val str = (adapter.data[position] as HotSearchRsp).name
            tv.setText(str)
            showLoading()
            mViewModel.search(page,str)
        }
        val headView = View.inflate(this,R.layout.layout_search_head,null)
        headView.mTvHeadView.text = getString(R.string.hot_search)
        headView.mIvDelete.visibility = View.GONE
        mHotSearchAdapter.addHeaderView(headView)
    }

    private fun initSearchHistory() {
        mRvHistory.layoutManager = MyFlexboxLayoutManager(this)
        mRvHistory.adapter = mHistorySearchAdapter

        mHistorySearchAdapter.openLoadAnimation()

        mHistorySearchAdapter.setOnItemChildClickListener { adapter, _, position ->
            val str = (adapter.data[position] as Record).name
            tv.setText(str)
            tv.setSelection(str.length)
            showLoading()
            mViewModel.search(page,str)
        }
        val headView = View.inflate(this,R.layout.layout_search_head,null)
        headView.mTvHeadView.text = getString(R.string.search_history)
        headView.mIvDelete.setOnClickListener {
            mHistorySearchAdapter.replaceData(arrayListOf())
            mViewModel.clearHistorySearch()
        }
        mHistorySearchAdapter.addHeaderView(headView)
    }

    private fun initSearchView() {
        tv = mSearchView.findViewById(R.id.search_src_text)
        tv.setTextColor(Color.WHITE)
        tv.setLinkTextColor(Color.WHITE)

        val ivSearch = mSearchView.findViewById<ImageView>(R.id.search_mag_icon)
        ivSearch.setImageResource(R.mipmap.ic_search)

        val ivClose = mSearchView.findViewById<ImageView>(R.id.search_close_btn)
        ivClose.setImageResource(R.mipmap.ic_close)
        ivClose.setOnClickListener {
            tv.setText("")
            page = 0
            showHistory()
            showHotSearch()
            hideKeyboard()
            mArticleAdapter.setNewData(null)
        }

        val ivGo = mSearchView.findViewById<ImageView>(R.id.search_go_btn)
        ivGo.setImageResource(R.mipmap.ic_go)
        with(mSearchView){
            setIconifiedByDefault(false)
            isIconified = false
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(str: String?): Boolean {
                    hideKeyboard()
                    submitSearch(str)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })

            //这并不起效
            setOnCloseListener {
                false
            }
        }
    }
    private fun submitSearch(str: String?) {
        str?.let {
            mViewModel.search(page,str)
        }
    }

    private fun showHistory(){
        mRvHistory.visibility = View.VISIBLE
    }

    private fun hideHistory() {
        mRvHistory.visibility = View.GONE
    }

    private fun showHotSearch(){
        mRvHotSearch.visibility = View.VISIBLE
    }

    private fun hideHotSearch() {
        mRvHotSearch.visibility = View.GONE
    }

    override fun onRefreshData() {
        if(tv.str().isEmpty()){
            msrlRefresh.isRefreshing = false
            return
        }
        page = 0
        mViewModel.search(page,tv.str())
    }

    override fun onLoadMore() {
        mViewModel.search(++page,tv.str())
    }

    override fun dataObserver() {
        super.dataObserver()
        mViewModel.mSearchData.observe(this, Observer {
            it?.let {
                showSearchResult(it.data.datas)
            }
            addHistorySearch(tv.str())
        })

        mViewModel.mHotKeyData.observe(this, Observer {
            it?.let {
                showHotSearch(it.data)
            }
        })

        mViewModel.mSearchHistory.observe(this, Observer {
            showHistoryResult(it)
        })
    }

    private fun addHistorySearch(str: String) {
        mViewModel.addHistorySearch(str)
    }

    private fun showHistoryResult(data: List<Record>?) {
        data?.let {
            mHistorySearchAdapter.replaceData(data)
        }
    }

    private fun showHotSearch(data: List<HotSearchRsp>) {
        mHotSearchAdapter.replaceData(data)
    }

    private fun showSearchResult(datas: List<Article>) {
        addData(datas)
        hideHistory()
        hideHotSearch()
    }
}
