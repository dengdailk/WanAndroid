package com.study.wanandroid

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        initView()
    }


   fun initView() {

        if (BuildConfig.DEBUG) {
            startActivity<MainActivity>()
            finish()
            return
        }
        disposable = Observable.timer(3, TimeUnit.SECONDS).subscribe {
            startActivity<MainActivity>()
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
