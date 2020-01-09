package com.study.common.base

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.study.common.common.AppManager
import io.reactivex.disposables.Disposable


abstract class BaseActivity : AppCompatActivity() {
    var disposable: Disposable? = null
    val loadService : LoadService<*> by lazy{
        LoadSir.getDefault().register(this){
            reLoad()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AppManager.instance.addActivity(this)
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(Color.TRANSPARENT)
        initView()
        initData()
    }

    open fun initView(){
    }
    open fun initData(){
    }
    abstract fun getLayoutId():Int
    open fun reLoad(){}
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
}
