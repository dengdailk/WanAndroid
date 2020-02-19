package com.study.wanandroid.account.view

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.study.common.base.LifecycleActivity
import com.study.wanandroid.R
import com.study.wanandroid.account.data.UserContext
import com.study.wanandroid.account.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : LifecycleActivity<AccountViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        super.initView()

        mBtnLogin.setOnClickListener {
            mViewModel.login(mTvAccount.toString().trim(),mTvPassword.toString().trim())
        }

        mBtnLogin.isEnabled = false

        mTvRegister.setOnClickListener {
            startActivity<RegisterActivity>()
            finish()
        }

        // 好烂的办法 有没有什么好办法 不使用databinding的情况下

        mTvAccount.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mBtnLogin.isEnabled = mTvPassword.toString().trim().isNotEmpty() && !s.isNullOrEmpty()
            }

        })

        mTvPassword.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mBtnLogin.isEnabled = mTvAccount.toString().trim().isNotEmpty() && !s.isNullOrEmpty()
            }

        })

        showSuccess()
    }

    override fun dataObserver() {
        mViewModel.mLoginData.observe(this, Observer { it ->
            it?.data?.let {
                UserContext.instance.loginSuccess(it.username,it.collectIds)
                finish()
            }
        })
    }


}
