package com.study.wanandroid.about.view

import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.study.common.base.BaseActivity
import com.study.wanandroid.BuildConfig
import com.study.wanandroid.R
import com.study.wanandroid.WebActivity
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity


class AboutActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        super.initView()
        setToolBar(toolbar,getString(R.string.menu_about))

        mLicense.setOnClickListener {
            startActivity<LicenseActivity>()
        }

        mGithub.setOnClickListener {
            startActivity<WebActivity>("url" to getString(R.string.github_url),"title" to "study")
        }

        mJianshu.setOnClickListener {
//            startActivity<WebActivity>("url" to getString(R.string.jianshu_url),"title" to "dengdai")
        }

        mVersion.setValue(BuildConfig.VERSION_NAME)

        mCache.setOnClickListener {
            MaterialDialog.Builder(this).title(R.string.tips)
                .content(R.string.ensure_clear_cache)
                .positiveText(R.string.ensure)
                .positiveColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .onPositive { _, _ ->
//                    mViewModel.clearCache()
                }
                .negativeText(R.string.cancel)
                .negativeColor(ContextCompat.getColor(this,R.color.gray))
                .onNeutral { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

    }

    override fun initData() {
        super.initData()
//        mViewModel.getCacheSize()
//        showSuccess()
    }

//    override fun dataObserver() {
//        mViewModel.mCacheData.observe(this, Observer {
//            it?.let {
//                changeCache(it)
//            }
//        })
//    }

    private fun changeCache(it: String) {
        mCache.setValue(it)
    }

}
