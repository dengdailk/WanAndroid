package com.study.wanandroid.system.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.study.common.base.LifecycleActivity
import com.study.wanandroid.R
import com.study.wanandroid.common.adapter.SystemArticleAdapter
import com.study.wanandroid.system.viewmodel.SystemViewModel
import kotlinx.android.synthetic.main.activity_system_article.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SystemArticleActivity : LifecycleActivity<SystemViewModel>() {

    private val titles: ArrayList<String>? by lazy { intent?.getStringArrayListExtra("titls") }
    private val ids: ArrayList<Int>? by lazy { intent?.getIntegerArrayListExtra("ids") }
    private val topTitle: String? by lazy { intent?.getStringExtra("topTitle") }

    override fun getLayoutId(): Int {
        return R.layout.activity_system_article
    }

    override fun initView() {
        super.initView()
        mTabLayout.setupWithViewPager(mContent)
        toolbar.let {
            it.title = topTitle
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        buildViewPage(ids)
        showSuccess()
    }

    override fun dataObserver() {
    }

    private fun buildViewPage(ids: ArrayList<Int>?) {
        val fragments = mutableListOf<Fragment>()

        ids?.forEach {
            fragments.add(SystemArticleFragment.newInstance(it))
        }

        mContent.adapter = SystemArticleAdapter(supportFragmentManager,titles!!,fragments)
    }
}
