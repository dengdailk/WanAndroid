package com.study.wanandroid.project.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.common.base.LifecycleFragment
import com.study.wanandroid.MainActivity

import com.study.wanandroid.R
import com.study.wanandroid.WebActivity
import com.study.wanandroid.common.adapter.ProjectListAdapter
import com.study.wanandroid.common.behavior.HideScrollListener
import com.study.wanandroid.project.data.Project
import com.study.wanandroid.project.data.ProjectEntity
import com.study.wanandroid.project.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.fragment_project_list.*
import org.jetbrains.anko.support.v4.startActivity

class ProjectListFragment : LifecycleFragment<ProjectViewModel>() {

    private val adapter : ProjectListAdapter by lazy { ProjectListAdapter(null) }

    override fun getLayoutId(): Int = R.layout.fragment_project_list

    private var page = 0
    private val uid by lazy { arguments?.getInt("uid") ?: -1 }

    companion object {
        fun newInstance(uid: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("uid", uid)
            val fragment = ProjectListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView() {
        super.initView()
        initRefresh()
        initRecycleView()
    }

    private fun initRecycleView() {
        mRvProject.layoutManager = LinearLayoutManager(activity)
        mRvProject.adapter = adapter

        mRvProject.addOnScrollListener(object : HideScrollListener(){
            override fun onHide() {
                if(activity is MainActivity){
                    (activity as MainActivity).onHide()
                }
            }

            override fun onShow() {
                if(activity is MainActivity){
                    (activity as MainActivity).onShow()
                }
            }
        })

        adapter.setOnClickListener(object : ProjectListAdapter.OnClickListener{
            override fun onClick(title: String, link: String) {
                startActivity<WebActivity>("url" to link,"title" to title)
            }
        })
    }

    private fun initRefresh() {
        msrlRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        msrlRefresh.setOnRefreshListener {
            onRefreshData()
        }

        adapter.setEnableLoadMore(true)
        adapter.setOnLoadMoreListener({onLoadMore()},mRvProject)
    }

    private fun onRefreshData() {
        page = 1
        mViewModel.getProjectList(uid,page)
    }

    private fun onLoadMore() {
        mViewModel.getProjectList(uid,++page)
    }

    override fun initData() {
        super.initData()
        page = 0
        mViewModel.getProjectList(uid,page)
    }

    override fun dataObserver() {
        mViewModel.mProjectListData.observe(this, Observer {
            it?.let {
                buildMultiItemData(it.data.datas)
            }
        })
    }

    private fun buildMultiItemData(datas: List<Project>) {

        val entities = arrayListOf<ProjectEntity>()

        datas.forEachIndexed  {index,element ->
            when(index %2){
                1 -> {
                    entities.add(ProjectEntity(ProjectEntity.RIGHT,element))
                }
                0 -> {
                    entities.add(ProjectEntity(ProjectEntity.LEFT,element))
                }
            }
        }
        addData(entities)
    }

    private fun addData(datas :List<ProjectEntity>){

        if(datas.isEmpty()){
            adapter.loadMoreEnd()
            return
        }

        //下拉刷新 注意完成加载更多(存在加载更多时刷新的情况)
        if(msrlRefresh.isRefreshing){
            msrlRefresh.isRefreshing = false
            adapter.setNewData(datas)
            adapter.loadMoreComplete()
            return
        }

        //加载更多
        adapter.addData(datas)
        adapter.loadMoreComplete()
    }



}
