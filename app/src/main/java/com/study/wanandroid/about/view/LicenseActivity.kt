package com.study.wanandroid.about.view

import com.mikepenz.aboutlibraries.LibsBuilder
import com.study.common.base.BaseActivity
import com.study.wanandroid.R
import kotlinx.android.synthetic.main.layout_toolbar.*


class LicenseActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_license

    override fun initView() {
        super.initView()
//        setToolBar(toolbar,getString(R.string.license))

        val fragment = LibsBuilder()
            .withVersionShown(true)
            .withLicenseShown(true)
            .supportFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.about_frame_container, fragment)
            .commit()
    }
}
