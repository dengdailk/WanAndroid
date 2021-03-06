package com.study.wanandroid

import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.google.android.material.navigation.NavigationView
import com.study.common.base.BaseActivity
import com.study.common.common.AppManager
import com.study.common.constant.Constant
import com.study.common.state.login.LoginSucListener
import com.study.common.state.login.LoginSucState
import com.study.common.utils.Preference
import com.study.wanandroid.about.view.AboutActivity
import com.study.wanandroid.account.data.UserContext
import com.study.wanandroid.home.view.HomeFragment
import com.study.wanandroid.nagivation.view.NagivationFragment
import com.study.wanandroid.project.view.ProjectFragment
import com.study.wanandroid.search.view.SearchActivity
import com.study.wanandroid.system.view.SystemFragment
import com.study.wanandroid.wechat.view.WeChatFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.System.exit

class MainActivity : BaseActivity(), LoginSucListener {
    private lateinit var mCurrentFragment: Fragment
    private val nagivationFragment: NagivationFragment by lazy { NagivationFragment() }
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val weChatFragment: WeChatFragment by lazy { WeChatFragment() }
    private val systemFragment: SystemFragment by lazy { SystemFragment() }
    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }
    private lateinit var headerView: View
    private var mUsername: String by Preference(Constant.USERNAME_KEY, "未登录")
    private var pressTime: Long = 0


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        initToolbar()
        initDrawerLayout()
        initFabButton()
        initBottomNavigationBar()
        defauleFragment()
        initNavView()
        LoginSucState.addListener(this)
    }

    private fun initFabButton() {
        mNavigationBar.setFab(fab)
        fab.setOnClickListener {
            startActivity<SearchActivity>()
        }
    }

    private fun defauleFragment() {
        mCurrentFragment = homeFragment
        supportFragmentManager.beginTransaction().add(R.id.content, homeFragment).commit()
    }

    private fun initToolbar() {
        setToolBar(toolbar, getString(R.string.app_name))
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.app_name, R.string.app_name)
        drawerMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun initDrawerLayout() {
        headerView = nav_view.getHeaderView(0)
        headerView.tv_name.text = mUsername
        headerView.iv_logo.setOnClickListener {
            UserContext.instance.login(this)
        }

    }

    private fun initNavView() {
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_collect -> {
                    goCollectActivity()
                }
                R.id.nav_about -> {
                    goAbout()
                }
            }
            drawerMain.closeDrawers()
            true
        }
    }

    private fun goCollectActivity() {
        UserContext.instance.goCollectActivity(this)
    }

    private fun goAbout() {
        startActivity<AboutActivity>()
    }

    private fun initBottomNavigationBar() {
        mNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT)
            .setActiveColor(R.color.colorPrimaryDark)
            .addItem(
                BottomNavigationItem(
                    R.mipmap.navigation_home,
                    getString(R.string.navigation_home)
                )
            )
            .addItem(
                BottomNavigationItem(
                    R.mipmap.navigation_wechat,
                    getString(R.string.navigation_wechat)
                )
            )
            .addItem(
                BottomNavigationItem(
                    R.mipmap.navigation_system,
                    getString(R.string.navigation_system)
                )
            )
            .addItem(
                BottomNavigationItem(
                    R.mipmap.navigation_navigation,
                    getString(R.string.navigation_navigation)
                )
            )
            .addItem(
                BottomNavigationItem(
                    R.mipmap.nagivation_project,
                    getString(R.string.navigation_project)
                )
            )
            .setBarBackgroundColor(R.color.white)
            .setFirstSelectedPosition(Constant.HOME)
            .initialise()
        mNavigationBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {}

            override fun onTabSelected(position: Int) {
                switchFragment(position)
            }

        })
    }

    private fun switchFragment(position: Int) {
        when (position) {
            Constant.HOME -> {
                setToolBar(toolbar, getString(R.string.navigation_home))
                changeFragment(homeFragment)
            }

            Constant.WE_CHAT -> {
                setToolBar(toolbar, getString(R.string.navigation_wechat))
                changeFragment(weChatFragment)
            }

            Constant.NAGIVATION -> {
                setToolBar(toolbar, getString(R.string.navigation_navigation))
                changeFragment(nagivationFragment)
            }

            Constant.SYSTEM -> {
                setToolBar(toolbar, getString(R.string.navigation_system))
                changeFragment(systemFragment)
            }

            Constant.PROJECT -> {
                setToolBar(toolbar, getString(R.string.navigation_project))
                changeFragment(projectFragment)
            }

            else -> {

            }
        }
    }

    private fun changeFragment(to: Fragment) {
        if (mCurrentFragment != to) {
            val transaction = supportFragmentManager.beginTransaction()
            if (to.isAdded)
                transaction.hide(mCurrentFragment).show(to)
            else
                transaction.hide(mCurrentFragment).add(R.id.content, to)
            transaction.commit()
            mCurrentFragment = to
        }
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast(getString(R.string.exit_app))
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSucState.removeListener(this)
    }

    override fun loginSuccess(username: String, collectIds: List<Int>?) {
        mUsername = username
        headerView.tv_name.text = username
    }

    fun onShow() {
        fab.animate().translationX(0f).interpolator = AccelerateDecelerateInterpolator()
        mNavigationBar.animate().translationY(0f).interpolator = AccelerateDecelerateInterpolator()
    }

    fun onHide() {
        val layoutParams = fab.layoutParams
        val leftMargin = (layoutParams as ViewGroup.MarginLayoutParams).marginEnd
        fab.animate().translationX(fab.width.toFloat() + leftMargin).interpolator =
            AccelerateDecelerateInterpolator()

        mNavigationBar.animate().translationY(mNavigationBar.height.toFloat()).interpolator =
            AccelerateDecelerateInterpolator()
    }
}