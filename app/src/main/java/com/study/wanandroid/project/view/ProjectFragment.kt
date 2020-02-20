package com.study.wanandroid.project.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.study.common.base.LifecycleFragment
import com.study.wanandroid.R
import com.study.wanandroid.common.adapter.ProjectTabAdapter
import com.study.wanandroid.project.data.ProjectTabRsp
import com.study.wanandroid.project.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.project_fragment.*

class ProjectFragment : LifecycleFragment<ProjectViewModel>() {

    override fun getLayoutId(): Int = R.layout.project_fragment

    override fun initView() {
        super.initView()
        mTabLayout.setupWithViewPager(mContent)
    }

    override fun initData() {
        super.initData()
        mViewModel.getProjectTab()
    }

    override fun dataObserver() {
        mViewModel.mProjectTapData.observe(this, Observer {
            it?.let {
                buildViewPage(it.data)
            }
        })
    }

    private fun buildViewPage(data: List<ProjectTabRsp>) {
        val tabs = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()

        data.forEach {
            tabs.add(it.name)
            fragments.add(ProjectListFragment.newInstance(it.id))
        }
        mContent.adapter = ProjectTabAdapter(childFragmentManager, tabs, fragments)
    }

}
