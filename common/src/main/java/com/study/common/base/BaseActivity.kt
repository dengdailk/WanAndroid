package com.study.common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
    fun setToolaBar(toolbar: Toolbar, title:String){
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
        disposable?.isDisposed()
        AppManager.instance.removeActivity(this)
    }
}
