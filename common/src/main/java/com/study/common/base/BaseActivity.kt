package com.study.common.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.study.common.common.AppManager
import io.reactivex.disposables.Disposable


abstract class BaseActivity : AppCompatActivity() {
    var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AppManager.instance.addActivity(this)
        setStatusBarFullTransparent()
        initView()
        initData()
    }

    open fun initView(){
    }
    open fun initData(){
    }
    abstract fun getLayoutId():Int
//    open fun reLoad(){}
    override fun onBackPressed() = finish()


    /**
     * 设置toolbar标题
     */
    fun setToolBar(toolbar: Toolbar, title:String){
        toolbar.title = title
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        AppManager.instance.removeActivity(this)
    }

    /**
     * 全透状态栏
     */
    protected open fun setStatusBarFullTransparent() {
            val window: Window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
    }
}
