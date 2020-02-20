package com.study.wanandroid.nagivation.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.study.common.base.LifecycleFragment
import com.study.wanandroid.MainActivity
import com.study.wanandroid.R
import com.study.wanandroid.WebActivity
import com.study.wanandroid.common.adapter.CategoryAdapter
import com.study.wanandroid.common.adapter.LabelAdapter
import com.study.wanandroid.common.behavior.HideScrollListener
import com.study.wanandroid.nagivation.data.LableRsp
import com.study.wanandroid.nagivation.data.NagivationCategoryRsp
import com.study.wanandroid.nagivation.viewmodel.NagivationViewModel
import kotlinx.android.synthetic.main.nagivation_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class NagivationFragment : LifecycleFragment<NagivationViewModel>() {

    private lateinit var mActivity: MainActivity
    private val mCategoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter(
            R.layout.item_category,
            null
        )
    }
    private val mLableAdapter: LabelAdapter by lazy { LabelAdapter(R.layout.item_lable, null) }
    private lateinit var mNagivationCategoryRspList: List<NagivationCategoryRsp>

    override fun getLayoutId(): Int = R.layout.nagivation_fragment


    override fun initView() {
        super.initView()
        mActivity = activity as MainActivity
        mRvCategory.layoutManager = LinearLayoutManager(activity)
        mRvCategory.adapter = mCategoryAdapter

        mRvCategory.addOnScrollListener(object : HideScrollListener() {
            override fun onHide() {
                mActivity.onHide()
            }

            override fun onShow() {
                mActivity.onShow()
            }
        })

        mCategoryAdapter.setOnItemChildClickListener { adapter, view, position ->
            setSelectCategory(position)
            setSelectLables(position)
        }

        mRvLabel.layoutManager = FlexboxLayoutManager(activity)
        mRvLabel.adapter = mLableAdapter

        mLableAdapter.setOnItemChildClickListener { adapter, view, position ->
            startActivity<WebActivity>(
                "url" to (adapter.data[position] as LableRsp).link,
                "title" to (adapter.data[position] as LableRsp).title
            )
        }

        mLableAdapter.openLoadAnimation()
    }

    override fun initData() {
        super.initData()
        mViewModel.getCategory()
    }

    override fun dataObserver() {
        mViewModel.mCategoryData.observe(this, Observer { response ->
            response?.let {
                mNagivationCategoryRspList = it.data
                mCategoryAdapter.addData(it.data)
                setSelectCategory(0)
                setSelectLables(0)
            }
        })
    }

    private fun setSelectLables(position: Int) {
        mLableAdapter.replaceData(mNagivationCategoryRspList[position].articles)
    }

    private fun setSelectCategory(position: Int) {
        mCategoryAdapter.selectPosition = position
        mCategoryAdapter.notifyDataSetChanged()
    }
}
